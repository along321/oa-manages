package com.qzl.oamanages.oa.mapper;

import com.qzl.oamanages.oa.entity.Leave;
import com.qzl.oamanages.oa.entity.OverTime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OverTimeMapper {

    int addOver(OverTime overTime);//添加

    List<OverTime> overTimes(@Param("rank") int rank,@Param("id") Integer id);

    OverTime findById(@Param("id") Integer id);

    int updataRank(@Param("id") Integer id,@Param("rank") int i);

    int editOver(OverTime overTime);

    int delOver(@Param("id") Integer id);

    List<OverTime> yiOver();
}
