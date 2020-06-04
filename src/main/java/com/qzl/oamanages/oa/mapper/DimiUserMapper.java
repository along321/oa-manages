package com.qzl.oamanages.oa.mapper;

import com.qzl.oamanages.oa.entity.DimiUser;
import com.qzl.oamanages.oa.entity.Dimission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DimiUserMapper {

    List<DimiUser> allDimiUser();
}
