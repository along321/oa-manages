package com.qzl.oamanages.oa.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qzl.oamanages.oa.entity.Dimission;
import com.qzl.oamanages.oa.entity.Leave;
import com.qzl.oamanages.oa.mapper.DimissionMapper;
import com.qzl.oamanages.oa.mapper.TaskMapper;
import com.qzl.oamanages.oa.service.DimissionService;
import com.qzl.oamanages.oa.service.TaskService;
import com.qzl.oamanages.shiro.entity.OaUser;
import com.qzl.oamanages.shiro.mapper.OaUserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DimissionServiceImpl implements DimissionService{

    @Autowired
    DimissionMapper dimissionMapper;

    @Autowired
    OaUserMapper oaUserMapper;

    @Override
    public int addDimission(Dimission dimission) {
        dimission.setParentUser(oaUserMapper.findByDeptBypost(dimission.getDid()));
        int num = dimissionMapper.addDimission(dimission);
        return num;
    }

    @Override
    public PageInfo<Dimission> weiLeave(Integer pageNum, Integer pageSize,int rank) {
        Subject subject = SecurityUtils.getSubject();
        OaUser user=(OaUser) subject.getPrincipal();
        PageHelper.startPage(pageNum,pageSize);
        List<Dimission> dimissions = dimissionMapper.weiLeave(user.getId(),rank);
        return new PageInfo<Dimission>(dimissions);
    }

    @Override
    public Dimission findById(Integer id) {
        return dimissionMapper.findById(id);
    }

    @Override
    public int editDimi(Dimission dimission) {
        return dimissionMapper.editDimi(dimission);
    }

    @Override
    public int delDimi(Integer id) {
        return dimissionMapper.delDimi(id);
    }

    @Override
    public int updataRank(Integer id, int i) {
        return dimissionMapper.updataRank(id,i);
    }


}
