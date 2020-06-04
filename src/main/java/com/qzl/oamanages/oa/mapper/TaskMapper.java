package com.qzl.oamanages.oa.mapper;

import com.qzl.oamanages.oa.entity.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaskMapper {
    int addTask(Task tk);

    List<Task> allTask(@Param("id") Integer userId);

    int delTask(@Param("id") Integer taskId);

    int updataTask(@Param("id") Integer taskId,@Param("sign") int sign);

    Task findById(Integer id);


    List<Task> postTask(@Param("sign") int sign);

    List<Task> haveTask(@Param("id") Integer id);
}
