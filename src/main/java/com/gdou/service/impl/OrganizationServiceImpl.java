package com.gdou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdou.exception.CustomException;
import com.gdou.mapper.OrganizationMapper;
import com.gdou.pojo.entity.Organization;
import com.gdou.pojo.vo.org.AddOrgVo;
import com.gdou.pojo.vo.org.Org1Vo;
import com.gdou.pojo.vo.org.Org2Vo;
import com.gdou.pojo.vo.org.Org3Vo;
import com.gdou.service.OrganizationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zzhave
 * @description 针对表【organization(机构表)】的数据库操作Service实现
 * @createDate 2024-11-26 15:58:47
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization>
        implements OrganizationService {
    public void addOrganization(AddOrgVo addOrgVo) {
        // 根据添加的机构级别进行不同的处理
        if (addOrgVo.getLevel() == 1) {
            // 1. 查询一级机构是否重复
            LambdaQueryWrapper<Organization> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Organization::getLevel, 1);
            wrapper.eq(Organization::getName, addOrgVo.getName());
            Organization organization = baseMapper.selectOne(wrapper);
            if (organization != null) {
                throw new CustomException("一级机构已存在");
            }

            // 2. 查询一级机构最大的code
            LambdaQueryWrapper<Organization> wrapper1 = new LambdaQueryWrapper<>();
            wrapper1.eq(Organization::getLevel, 1);
            wrapper1.orderByDesc(Organization::getCode);
            wrapper1.last("limit 1");
            Organization maxCodeOrg = baseMapper.selectOne(wrapper1);
            int code = maxCodeOrg == null ? 1 : maxCodeOrg.getCode() + 1;

            // 3. 插入一级机构
            Organization org = new Organization();
            org.setLevel(1);
            org.setName(addOrgVo.getName());
            org.setCode(code);
            baseMapper.insert(org);
        } else if (addOrgVo.getLevel() == 2 && addOrgVo.getFirstCode() != null) {
            // 1. 查询一级机构是否存在
            LambdaQueryWrapper<Organization> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Organization::getLevel, 1);
            wrapper.eq(Organization::getCode, addOrgVo.getFirstCode());
            Organization firstOrg = baseMapper.selectOne(wrapper);
            if (firstOrg == null) {
                throw new CustomException("一级机构不存在");
            }

            // 2. 查询二级机构是否重复
            LambdaQueryWrapper<Organization> wrapper1 = new LambdaQueryWrapper<>();
            wrapper1.eq(Organization::getLevel, 2);
            wrapper1.eq(Organization::getName, addOrgVo.getName());
            wrapper1.eq(Organization::getParentId, firstOrg.getId());
            Organization organization = baseMapper.selectOne(wrapper1);
            if (organization != null) {
                throw new CustomException("二级机构已存在");
            }

            // 3. 查询二级机构最大的code
            LambdaQueryWrapper<Organization> wrapper2 = new LambdaQueryWrapper<>();
            wrapper2.eq(Organization::getLevel, 2);
            wrapper2.eq(Organization::getParentId, firstOrg.getId());
            wrapper2.orderByDesc(Organization::getCode);
            wrapper2.last("limit 1");
            Organization maxCodeOrg = baseMapper.selectOne(wrapper2);
            int code = maxCodeOrg == null ? 1 : maxCodeOrg.getCode() + 1;

            // 4. 插入二级机构
            Organization org = new Organization();
            org.setLevel(2);
            org.setName(addOrgVo.getName());
            org.setCode(code);
            org.setParentId(firstOrg.getId());
            baseMapper.insert(org);
        } else if (addOrgVo.getLevel() == 3 && addOrgVo.getFirstCode() != null && addOrgVo.getSecondCode() != null) {
            // 1. 查询一级机构是否存在
            LambdaQueryWrapper<Organization> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Organization::getLevel, 1);
            wrapper.eq(Organization::getCode, addOrgVo.getFirstCode());
            Organization firstOrg = baseMapper.selectOne(wrapper);
            if (firstOrg == null) {
                throw new CustomException("一级机构不存在");
            }

            // 2. 查询二级机构是否存在
            LambdaQueryWrapper<Organization> wrapper1 = new LambdaQueryWrapper<>();
            wrapper1.eq(Organization::getLevel, 2);
            wrapper1.eq(Organization::getCode, addOrgVo.getSecondCode());
            wrapper1.eq(Organization::getParentId, firstOrg.getId());
            Organization secondOrg = baseMapper.selectOne(wrapper1);
            if (secondOrg == null) {
                throw new CustomException("二级机构不存在");
            }

            // 3. 查询三级机构是否重复
            LambdaQueryWrapper<Organization> wrapper2 = new LambdaQueryWrapper<>();
            wrapper2.eq(Organization::getLevel, 3);
            wrapper2.eq(Organization::getName, addOrgVo.getName());
            wrapper2.eq(Organization::getParentId, secondOrg.getId());
            Organization organization = baseMapper.selectOne(wrapper2);
            if (organization != null) {
                throw new CustomException("三级机构已存在");
            }

            // 4. 查询三级机构最大的code
            LambdaQueryWrapper<Organization> wrapper3 = new LambdaQueryWrapper<>();
            wrapper3.eq(Organization::getLevel, 3);
            wrapper3.eq(Organization::getParentId, secondOrg.getId());
            wrapper3.orderByDesc(Organization::getCode);
            wrapper3.last("limit 1");
            Organization maxCodeOrg = baseMapper.selectOne(wrapper3);
            int code = maxCodeOrg == null ? 1 : maxCodeOrg.getCode() + 1;

            // 5. 插入三级机构
            Organization org = new Organization();
            org.setLevel(3);
            org.setName(addOrgVo.getName());
            org.setCode(code);
            org.setParentId(secondOrg.getId());
            baseMapper.insert(org);
        } else {
            throw new CustomException("参数错误");
        }
    }

    public List<Org1Vo> getOrgList() {
        // 1. 查询所有数据
        List<Organization> allOrgs = baseMapper.selectAllOrdered();

        // 2. 按level分组
        Map<Integer, List<Organization>> levelMap = allOrgs.stream()
                .collect(Collectors.groupingBy(Organization::getLevel));

        // 3. 转换各级机构为VO
        List<Org1Vo> org1List = new ArrayList<>();

        // 一级机构
        List<Organization> org1s = levelMap.get(1);
        if (org1s != null) {
            for (Organization org1 : org1s) {
                Org1Vo org1Vo = new Org1Vo();
                org1Vo.setLevel(org1.getLevel());
                org1Vo.setName(org1.getName());
                org1Vo.setCode(org1.getCode());
                org1List.add(org1Vo);

                // 二级机构
                List<Organization> org2s = levelMap.get(2);
                if (org2s != null) {
                    List<Org2Vo> org2VoList = new ArrayList<>();
                    for (Organization org2 : org2s) {
                        if (org2.getParentId().equals(org1.getId())) {
                            Org2Vo org2Vo = new Org2Vo();
                            org2Vo.setLevel(org2.getLevel());
                            org2Vo.setName(org2.getName());
                            org2Vo.setCode(org2.getCode());
                            org2Vo.setParentCode(org1.getCode());
                            org2VoList.add(org2Vo);

                            // 三级机构
                            List<Organization> org3s = levelMap.get(3);
                            if (org3s != null) {
                                List<Org3Vo> org3VoList = new ArrayList<>();
                                for (Organization org3 : org3s) {
                                    if (org3.getParentId().equals(org2.getId())) {
                                        Org3Vo org3Vo = new Org3Vo();
                                        org3Vo.setLevel(org3.getLevel());
                                        org3Vo.setName(org3.getName());
                                        org3Vo.setCode(org3.getCode());
                                        org3Vo.setParentCode(org2.getCode());
                                        org3VoList.add(org3Vo);
                                    }
                                }
                                org2Vo.setChildrenOrg3(org3VoList);
                            }
                        }
                    }
                    org1Vo.setChildrenOrg2(org2VoList);
                }
            }
        }
        return org1List;
    }

    /**
     * 根据机构代码查询机构id
     *
     * @return
     */
    public Integer getOrgByCode(Integer code1, Integer code2, Integer code3) {
        // code1为一级机构的code,code2为二级机构的code,code3为三级机构的code,
        // 根据code1,code2,code3得到对应的机构id
        return baseMapper.getOrgByCode(code1, code2, code3);
    }


}