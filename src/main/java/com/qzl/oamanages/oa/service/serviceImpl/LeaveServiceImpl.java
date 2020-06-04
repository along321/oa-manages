package com.qzl.oamanages.oa.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qzl.oamanages.oa.entity.Leave;
import com.qzl.oamanages.oa.mapper.LeaveMapper;
import com.qzl.oamanages.oa.service.LeaveService;
import com.qzl.oamanages.shiro.entity.OaUser;
import com.qzl.oamanages.shiro.service.OaUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Level;

@Service
public class LeaveServiceImpl implements LeaveService{

    @Autowired
    LeaveMapper leaveMapper;

    @Autowired
    OaUserService oaUserService;

    @Override
    public PageInfo<Leave> allLeave(Integer pageNum, Integer pageSize) {
        Subject subject = SecurityUtils.getSubject();
        OaUser user=(OaUser) subject.getPrincipal();
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<Leave>(leaveMapper.allLeave(user.getId()));
    }

    @Override
    public int addLeave(Leave leave) {
        leave.setParentuser(oaUserService.findByDeptBypost(leave.getDid()));
        long m = leave.getEndDate().getTime() - leave.getStartDate().getTime();
        leave.setNumDays((int)m / (1000 * 60 * 60 * 24));
        return leaveMapper.addLeave(leave);
    }

    @Override
    public Leave findById(Integer id) {
        return leaveMapper.findById(id);
    }

    @Override
    public int editLeave(Leave leave) {
        long m = leave.getEndDate().getTime() - leave.getStartDate().getTime();
        leave.setNumDays((int)m / (1000 * 60 * 60 * 24));
        return leaveMapper.editLeave(leave);
    }

    @Override
    public PageInfo<Leave> commList(Integer pageNum, Integer pageSize) {
        Subject subject = SecurityUtils.getSubject();
        OaUser user=(OaUser) subject.getPrincipal();
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<Leave>(leaveMapper.commList(user.getId()));
    }

    @Override
    public int updataRank(Integer leaveId,int rank) {
        return leaveMapper.updataRank(leaveId,rank);
    }

    @Override
    public PageInfo<Leave> weiLeave(Integer pageNum, Integer pageSize) {
        Subject subject = SecurityUtils.getSubject();
        OaUser user=(OaUser) subject.getPrincipal();
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<Leave>(leaveMapper.weiLeave(user.getId()));
    }

    @Override
    public PageInfo<Leave> yiLeave(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        return new PageInfo<Leave>(leaveMapper.yiLeave());
    }
}
