package com.qzl.oamanages.oa.mapper;

import com.qzl.oamanages.oa.entity.Leave;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LeaveMapper {

    List<Leave> allLeave(@Param("id") Integer id);

    int addLeave(Leave leave);//添加

    Leave findById(@Param("id") Integer id);//查询当前请假信息

    int editLeave(Leave leave);//修改请假信息

    List<Leave> commList(@Param("id") Integer id);

    int updataRank(@Param("id") Integer leaveId,@Param("rank") int rank);

    List<Leave> weiLeave(@Param("id") Integer id);

    List<Leave> yiLeave();
}
