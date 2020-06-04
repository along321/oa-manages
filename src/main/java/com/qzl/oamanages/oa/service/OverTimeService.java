package com.qzl.oamanages.oa.service;

import com.github.pagehelper.PageInfo;
import com.qzl.oamanages.oa.entity.Leave;
import com.qzl.oamanages.oa.entity.OverTime;

public interface OverTimeService {

    int addOver(OverTime overTime);

    PageInfo<OverTime> weiOver(Integer pageNum, Integer pageSize,int rank);

    OverTime findById(Integer id);

    int updataRank(Integer id, int i);

    int editOver(OverTime overTime);

    int delTime(Integer id);

    PageInfo<OverTime> yiOver(Integer pageNum, Integer pageSize);
}
