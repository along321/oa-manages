package com.qzl.oamanages.oa.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qzl.oamanages.oa.entity.OverTime;
import com.qzl.oamanages.oa.mapper.OverTimeMapper;
import com.qzl.oamanages.oa.service.OverTimeService;
import com.qzl.oamanages.shiro.entity.OaUser;
import com.qzl.oamanages.shiro.service.OaUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverTimeServiceImpl implements OverTimeService {

    @Autowired
    OverTimeMapper overTimeMapper;
    @Autowired
    OaUserService oaUserService;

    @Override
    public int addOver(OverTime overTime) {
        overTime = update(overTime);
        return overTimeMapper.addOver(overTime);
    }

    public List<OverTime> overTimes(int rank){
        Subject subject = SecurityUtils.getSubject();
        OaUser user=(OaUser) subject.getPrincipal();
        return overTimeMapper.overTimes(rank,user.getId());
    }

    @Override
    public PageInfo<OverTime> weiOver(Integer pageNum, Integer pageSize,int rank) {
        PageHelper.startPage(pageNum,pageSize);
        List<OverTime> overTimeList = overTimes(rank);
        return new PageInfo<>(overTimeList);
    }

    @Override
    public OverTime findById(Integer id) {
        return overTimeMapper.findById(id);
    }

    @Override
    public int updataRank(Integer id, int i) {
        return overTimeMapper.updataRank(id,i);
    }

    @Override
    public int editOver(OverTime overTime) {
        overTime = update(overTime);
        return overTimeMapper.editOver(overTime);
    }

    @Override
    public int delTime(Integer id) {
        return overTimeMapper.delOver(id);
    }

    @Override
    public PageInfo<OverTime> yiOver(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<OverTime>(overTimeMapper.yiOver());
    }

    /*
    修改时间
     */
    public OverTime update(OverTime overTime){
        long m = overTime.getEndDate().getTime() - overTime.getStartDate().getTime();

        long days = m / (1000 * 60 * 60 * 24);

        long hours = (m-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
        long minutes = (m-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
        overTime.setHour(""+days+"天"+hours+"小时"+minutes+"分");
        overTime.setMs(m);
        overTime.setRank(1);
        int i = oaUserService.findByDeptBypost(overTime.getDid());
        overTime.setParentuser(i);;
        return overTime;
    }
}
