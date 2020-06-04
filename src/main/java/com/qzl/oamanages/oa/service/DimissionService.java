package com.qzl.oamanages.oa.service;

import com.github.pagehelper.PageInfo;
import com.qzl.oamanages.oa.entity.Dimission;

public interface DimissionService {
    int addDimission(Dimission dimission);

    PageInfo<Dimission> weiLeave(Integer pageNum, Integer pageSize,int rank);

    Dimission findById(Integer id);

    int editDimi(Dimission dimission);

    int delDimi(Integer id);

    int updataRank(Integer id, int i);
}
