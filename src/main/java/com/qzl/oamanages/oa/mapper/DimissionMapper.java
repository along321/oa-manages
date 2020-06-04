package com.qzl.oamanages.oa.mapper;

import com.qzl.oamanages.oa.entity.Dimission;
import com.qzl.oamanages.oa.entity.Leave;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DimissionMapper {

    int addDimission(Dimission dimission);

    List<Dimission> weiLeave(@Param("uid") Integer id,@Param("rank") int rank);

    Dimission findById(@Param("id") Integer id);

    int editDimi(Dimission dimission);

    int delDimi(@Param("id") Integer id);

    int updataRank(@Param("id") Integer id,@Param("i") int i);
}
