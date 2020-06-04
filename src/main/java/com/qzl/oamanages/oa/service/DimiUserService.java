package com.qzl.oamanages.oa.service;

import com.github.pagehelper.PageInfo;
import com.qzl.oamanages.oa.entity.DimiUser;
import com.qzl.oamanages.oa.entity.Dimission;

import java.util.List;

public interface DimiUserService {
    PageInfo<DimiUser> allDimiUser(Integer pageNum,Integer pageSize);

}
