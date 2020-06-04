package com.qzl.oamanages.oa.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qzl.oamanages.oa.entity.DimiUser;
import com.qzl.oamanages.oa.mapper.DimiUserMapper;
import com.qzl.oamanages.oa.service.DimiUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DimiUserServiceImpl implements DimiUserService {

    @Autowired
    DimiUserMapper dimiUserMapper;
    @Override
    public PageInfo<DimiUser> allDimiUser(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(dimiUserMapper.allDimiUser());
    }
}
