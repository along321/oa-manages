package com.qzl.oamanages.oa.service;

import com.github.pagehelper.PageInfo;
import com.qzl.oamanages.oa.entity.Leave;

public interface LeaveService {

    PageInfo<Leave> allLeave(Integer pageNum, Integer pageSize);

    int addLeave(Leave leave);//添加请假

    Leave findById(Integer id);

    int editLeave(Leave leave);//修改请假信息

    PageInfo<Leave> commList(Integer pageNum, Integer pageSize);

    int updataRank(Integer leaveId,int rank);//修改状态为不同意 重新申请

    PageInfo<Leave> weiLeave(Integer pageNum, Integer pageSize);//已完成的请假

    PageInfo<Leave> yiLeave(Integer pageNum, Integer pageSize);
}
