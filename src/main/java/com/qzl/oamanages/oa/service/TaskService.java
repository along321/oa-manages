package com.qzl.oamanages.oa.service;

import com.github.pagehelper.PageInfo;
import com.qzl.oamanages.oa.entity.Leave;
import com.qzl.oamanages.oa.entity.Task;
import com.qzl.oamanages.shiro.entity.OaUser;

public interface TaskService {

    int addTask(int task,  Integer id ,Integer parentuser, String userName,Integer uid,String title);

    PageInfo<Task> allTask(Integer pageNum, Integer pageSize, OaUser user);

    int delTask(Integer taskId);

    int updataTask(Integer taskId,int sign);

    Task findById(Integer id);

    PageInfo<Task> haveTask(Integer pageNum, Integer pageSize, OaUser user);
}
