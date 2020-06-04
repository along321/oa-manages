package com.qzl.oamanages.oa.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qzl.oamanages.oa.entity.Leave;
import com.qzl.oamanages.oa.entity.Task;
import com.qzl.oamanages.oa.mapper.TaskMapper;
import com.qzl.oamanages.oa.service.LeaveService;
import com.qzl.oamanages.oa.service.TaskService;
import com.qzl.oamanages.shiro.entity.OaDept;
import com.qzl.oamanages.shiro.entity.OaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    LeaveService leaveService;
    @Override
    public int addTask(int task, Integer id ,Integer parentuser, String userName,Integer uid,String title) {
        Task tk = new Task();
        tk.setApptime(new Date());
        tk.setProposer(userName);
        tk.setTask(task);
        tk.setTaskId(id);
        tk.setTitle(title);
        tk.setUid(parentuser);
        tk.setAppId(uid);
        int num = taskMapper.addTask(tk);
        return num;
    }

    @Override
    public PageInfo<Task> allTask(Integer pageNum, Integer pageSize, OaUser user) {
        PageHelper.startPage(pageNum,pageSize);
        List<Task> tasks;
        if(user.getOaRole().getId()==2){
            tasks =  taskMapper.postTask(2);
        }else {
            tasks =  taskMapper.allTask(user.getId());
        }
        return new PageInfo<Task>(tasks);
    }

    @Override
    public int delTask(Integer taskId) {
        return taskMapper.delTask(taskId);
    }

    @Override
    public int updataTask(Integer taskId,int sign) {
        return taskMapper.updataTask(taskId,sign);
    }

    @Override
    public Task findById(Integer id) {
        return taskMapper.findById(id);
    }


    @Override
    public PageInfo<Task> haveTask(Integer pageNum, Integer pageSize, OaUser user) {
        PageHelper.startPage(pageNum,pageSize);
        List<Task> tasks;
        if(user.getOaRole().getId()==2){
            tasks =  taskMapper.postTask(3);
        }else {
            tasks =  taskMapper.haveTask(user.getId());
        }
        return new PageInfo<Task>(tasks);
    }
}
