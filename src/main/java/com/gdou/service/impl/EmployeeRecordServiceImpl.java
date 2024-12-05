package com.gdou.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdou.common.FileContentType;
import com.gdou.exception.CustomException;
import com.gdou.mapper.EmployeeRecordMapper;
import com.gdou.mapping.EmployeeRecordMapping;
import com.gdou.pojo.entity.EmployeeRecord;
import com.gdou.pojo.vo.employee.AddEmployeeRecordVo;
import com.gdou.pojo.vo.employee.ConditionalQueriesEmployeeVo;
import com.gdou.pojo.vo.employee.UpdateEmployeeRecordVoFromHrmVo;
import com.gdou.service.EmployeeRecordService;
import com.gdou.service.OrganizationService;
import com.gdou.support.MinioHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

/**
 * @author zzhave
 */
@Service
@RequiredArgsConstructor
public class EmployeeRecordServiceImpl extends ServiceImpl<EmployeeRecordMapper, EmployeeRecord> implements EmployeeRecordService {
    private final EmployeeRecordMapping employeeRecordMapping;
    private final OrganizationService organizationService;
    private final MinioHelper minio;

    public EmployeeRecord addEmployeeRecord(AddEmployeeRecordVo addEmployeeRecordVo) {
        // 0. 将addEmployeeRecordVo转换为EmployeeRecord对象
        EmployeeRecord employeeRecord = employeeRecordMapping.addEmployeeRecordVoToEmployeeRecord(addEmployeeRecordVo);

        // 1. 将三个机构code转换为最终机构id
        Integer code1 = addEmployeeRecordVo.getOrganizationCode1();
        Integer code2 = addEmployeeRecordVo.getOrganizationCode2();
        Integer code3 = addEmployeeRecordVo.getOrganizationCode3();
        if (code1 == null || code2 == null || code3 == null) {
            throw new CustomException("机构代码不能为空");
        }
        if (code1 < 0 || code2 < 0 || code3 < 0 || code1 > 99 || code2 > 99 || code3 > 99) {
            throw new CustomException("机构代码错误");
        }
        Integer orgId = organizationService.getOrgByCode(code1, code2, code3);
        if (orgId == null) {
            throw new CustomException("机构代码错误");
        }
        employeeRecord.setOrganizationId(orgId);

        // 2. 根据规则生成档案编号
        // 创建StringBuilder对象
        StringBuilder sb = new StringBuilder();

        // 2024
        sb.append(String.format("%04d", LocalDate.now().getYear()));
        // 依次添加每个机构code，并确保它们都是两位数
        sb.append(String.format("%02d", code1));
        sb.append(String.format("%02d", code2));
        sb.append(String.format("%02d", code3));
        // 转换成最终的字符串
        String formattedCode = sb.toString();
        // 查询数据库中最大的档案编号
        List<String> maxRecordNum = baseMapper.getMaxRecordNum(formattedCode);
        int max = 0;
        for (String s : maxRecordNum) {
            max = Math.max(max, Integer.parseInt(s.substring(10)));
        }
        // 生成新的档案编号
        sb.append(String.format("%02d", max + 1));
        employeeRecord.setRecordNumber(sb.toString());

        // 3. 设置登记人和登记时间
        employeeRecord.setRegisterId(StpUtil.getLoginIdAsInt());
        employeeRecord.setRegisterTime(Calendar.getInstance().getTime());

        // 4. 插入员工档案
        baseMapper.insert(employeeRecord);

        return employeeRecord;
    }

    public void deleteEmployeeRecord(String recordNumber) {
        // 1. 查询员工档案是否存在
        EmployeeRecord employeeRecord = baseMapper.selectByRecordNumber(recordNumber);
        if (employeeRecord == null) {
            throw new CustomException("员工档案不存在");
        }

        // 2. 删除员工档案
        employeeRecord.setStatus("已删除");
        baseMapper.updateById(employeeRecord);
    }

    public List<EmployeeRecord> getList() {
        // 查询所有员工档案，不包括 status 为 '已删除' 的档案
        return lambdaQuery().ne(EmployeeRecord::getStatus, "已删除").list();
    }

    public void updateEmployeeRecordFromHrs(EmployeeRecord employeeRecord) {
        // 1. 查询档案是否存在
        EmployeeRecord dbEmp = baseMapper.selectById(employeeRecord.getRecordNumber());

        // 1.1 档案的状态是否为'待复核'或'已删除'时，不可以修改
        if (dbEmp == null) throw new CustomException("档案不存在");
        if (dbEmp.getStatus().equals("待复核") || dbEmp.getStatus().equals("已删除")) {
            throw new CustomException("档案状态为:" + dbEmp.getStatus() + "，不可修改");
        }
        // 1.2 档案的状态是否为'正常'时，可以修改，并把档案状态设置为'待复核'
        employeeRecord.setStatus("待复核");
        baseMapper.updateById(employeeRecord);
    }

    public void updateEmployeeRecordFromHrm(UpdateEmployeeRecordVoFromHrmVo updateEmployeeRecordVoFromHrmVo) {
        EmployeeRecord employeeRecord = employeeRecordMapping.updateEmployeeRecordVoFromHrmVoToEmployeeRecord(updateEmployeeRecordVoFromHrmVo);
        // 1. 查询档案是否存在
        EmployeeRecord dbEmp = baseMapper.selectById(employeeRecord.getRecordNumber());

        if (dbEmp == null) throw new CustomException("档案不存在");
        if (dbEmp.getStatus().equals("已删除")) {
            throw new CustomException("档案状态为:" + dbEmp.getStatus() + "，不可修改");
        }

        // 设置复核意见 复核人 复核时间
        employeeRecord.setReviewOpinions(updateEmployeeRecordVoFromHrmVo.getReviewOpinions());
        employeeRecord.setReviewId(StpUtil.getLoginIdAsInt());
        employeeRecord.setReviewTime(Calendar.getInstance().getTime());
        employeeRecord.setStatus("正常");
        baseMapper.updateById(employeeRecord);
    }

    public EmployeeRecord getEmployeeRecord(String recordNumber) {
        LambdaQueryWrapper<EmployeeRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.ne(EmployeeRecord::getStatus, "已删除");
        wrapper.eq(EmployeeRecord::getRecordNumber, recordNumber);
        return baseMapper.selectOne(wrapper);
    }

    public void recoverEmployeeRecord(String recordNumber) {
        EmployeeRecord employeeRecord = baseMapper.selectById(recordNumber);
        if (employeeRecord == null) {
            throw new CustomException("档案不存在");
        }

        employeeRecord.setStatus("正常");

        baseMapper.updateById(employeeRecord);
    }

    public List<EmployeeRecord> getListDeleted() {
        return lambdaQuery().eq(EmployeeRecord::getStatus, "已删除").list();
    }

    public String uploadAvatar(String recordNumber, MultipartFile file) {
        // 1. 查询员工档案是否存在
        EmployeeRecord employeeRecord = baseMapper.selectById(recordNumber);
        if (employeeRecord == null) {
            throw new CustomException("员工档案不存在");
        }

        // 2. 上传头像
        if (file != null) {
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith(FileContentType.IMAGE_TYPE)) {
                throw new CustomException("文件类型不合法");
            }
            String filename = recordNumber + "." + contentType.split("/")[1];
            employeeRecord.setPhotoUrl(minio.uploadFile(file, filename));
        }

        // 3. 更新头像地址
        baseMapper.updateById(employeeRecord);

        // 4. 返回头像地址
        return employeeRecord.getPhotoUrl();
    }

    public List<EmployeeRecord> conditionSearch(ConditionalQueriesEmployeeVo conditionalQueriesEmployeeVo) {
        // 1. 查询所有员工档案，不包括 status 为 '已删除' 的档案，包括该时间段内的档案
        LambdaQueryWrapper<EmployeeRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.ne(EmployeeRecord::getStatus, "已删除");
        // 1.1 如果开始日期不为空
        if (conditionalQueriesEmployeeVo.getStartTime() != null && !conditionalQueriesEmployeeVo.getStartTime().isBlank()) {
            wrapper.ge(EmployeeRecord::getRegisterTime, conditionalQueriesEmployeeVo.getStartTime());
        }
        // 1.2 如果结束日期不为空
        if (conditionalQueriesEmployeeVo.getEndTime() != null && !conditionalQueriesEmployeeVo.getEndTime().isBlank()) {
            wrapper.le(EmployeeRecord::getRegisterTime, conditionalQueriesEmployeeVo.getEndTime());
        }
        // 1.3 如果职位id不为空
        if (conditionalQueriesEmployeeVo.getPositionId() != null && !conditionalQueriesEmployeeVo.getPositionId().isBlank()) {
            wrapper.eq(EmployeeRecord::getPositionId, conditionalQueriesEmployeeVo.getPositionId());
        }
        List<EmployeeRecord> list = baseMapper.selectList(wrapper);

        // 2. 根据机构code的条件查询
        if (conditionalQueriesEmployeeVo.getFirstCode() != null && !conditionalQueriesEmployeeVo.getFirstCode().isBlank()) {
            int value = Integer.parseInt(conditionalQueriesEmployeeVo.getFirstCode());
            if (value < 1 || value > 99) {
                throw new CustomException("一级机构代码错误");
            }
            list.removeIf(emp -> !emp.getRecordNumber().substring(4, 6).equals(String.format("%02d", value)));
        }

        if (conditionalQueriesEmployeeVo.getSecondCode() != null && !conditionalQueriesEmployeeVo.getSecondCode().isBlank()) {
            int value = Integer.parseInt(conditionalQueriesEmployeeVo.getSecondCode());
            if (value < 1 || value > 99) {
                throw new CustomException("二级机构代码错误");
            }
            list.removeIf(emp -> !emp.getRecordNumber().substring(6, 8).equals(String.format("%02d", value)));
        }

        if (conditionalQueriesEmployeeVo.getThirdCode() != null && !conditionalQueriesEmployeeVo.getThirdCode().isBlank()) {
            int value = Integer.parseInt(conditionalQueriesEmployeeVo.getThirdCode());
            if (value < 1 || value > 99) {
                throw new CustomException("三级机构代码错误");
            }
            list.removeIf(emp -> !emp.getRecordNumber().substring(8, 10).equals(String.format("%02d", value)));
        }

        return list;
    }

    public List<Integer> queryIdByKeyWord(String keyWord) {
        return baseMapper.queryIdByKeyWord(keyWord);
    }
}
