package com.qzl.oamanages.oa.controller;

import com.github.pagehelper.PageInfo;
import com.qzl.oamanages.oa.entity.Dimission;
import com.qzl.oamanages.oa.entity.Leave;
import com.qzl.oamanages.oa.entity.Task;
import com.qzl.oamanages.oa.service.DimissionService;
import com.qzl.oamanages.oa.service.LeaveService;
import com.qzl.oamanages.oa.service.OverTimeService;
import com.qzl.oamanages.oa.service.TaskService;
import com.qzl.oamanages.shiro.entity.OaUser;
import com.qzl.oamanages.utils.LayuiMsg;
import com.qzl.oamanages.utils.Msg;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    LeaveService leaveService;

    @Autowired
    OverTimeService overTimeService;

    @Autowired
    DimissionService dimissionService;


    @RequestMapping("/list")
    public LayuiMsg userList(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                             @RequestParam(required = false,defaultValue = "5")Integer pageSize){
        Subject subject = SecurityUtils.getSubject();
        OaUser user=(OaUser) subject.getPrincipal();
        PageInfo<Task> pageInfo = taskService.allTask(pageNum,pageSize,user);
        LayuiMsg msg = new LayuiMsg();
        msg.setCode(100);
        msg.setMsg("操作成功");
        msg.setCount(pageInfo.getTotal());
        msg.setData(pageInfo.getList());
        return msg;
    }
    @RequestMapping("/havelist")
    public LayuiMsg havelist(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                             @RequestParam(required = false,defaultValue = "5")Integer pageSize){
        Subject subject = SecurityUtils.getSubject();
        OaUser user=(OaUser) subject.getPrincipal();
        PageInfo<Task> pageInfo = taskService.haveTask(pageNum,pageSize,user);
        LayuiMsg msg = new LayuiMsg();
        msg.setCode(100);
        msg.setMsg("操作成功");
        msg.setCount(pageInfo.getTotal());
        msg.setData(pageInfo.getList());
        return msg;
    }
    @PostMapping("/leaveAudit")
    public Msg leaveAudit(Integer taskId,Integer leaveId,int istrue){
        int num = 0;
        if(istrue>0){
            num = taskService.delTask(taskId);
            if(num>0){
                num = leaveService.updataRank(leaveId,1);
                return Msg.success();
            }else {
                return Msg.fail();
            }
        }else {
            Task task = taskService.findById(taskId);
            if(task.getSign()==1){
                num = taskService.updataTask(taskId,2);
                if(num>0){
                    num = leaveService.updataRank(leaveId,3);
                    return Msg.success();
                }else{
                    return Msg.fail();
                }

            }else {
                num = taskService.updataTask(taskId,3);
                if (num > 0) {
                    num = leaveService.updataRank(leaveId,4);
                    return Msg.success();
                } else {
                    return Msg.fail();
                }
            }
        }

    }
    @PostMapping("/overAudit")
    public Msg overAudit(Integer taskId,Integer overId,int istrue){
        int num = 0;
        if(istrue>0){
            num = taskService.delTask(taskId);
            if(num>0){
                num = overTimeService.updataRank(overId,1);
                return Msg.success();
            }else {
                return Msg.fail();
            }
        }else {
//            Task task = taskService.findById(taskId);
                num = taskService.updataTask(taskId,3);
                if(num>0){
                    num = overTimeService.updataRank(overId,3);
                    return Msg.success();
                }else{
                    return Msg.fail();
                }
        }

    }
    @PostMapping("/dimiAudit")
    public Msg dimiAudit(Integer taskId,Integer dimiId,int istrue){
        Subject subject = SecurityUtils.getSubject();
        OaUser user=(OaUser) subject.getPrincipal();
        int num = 0;
        if(istrue>0){
            num = taskService.delTask(taskId);
            if(num>0){
                num = dimissionService.updataRank(dimiId,1);
                return Msg.success();
            }else {
                return Msg.fail();
            }
        }else {
            Task task = taskService.findById(taskId);
            if(task.getSign()==1){
            num = taskService.updataTask(taskId,2);
            if(num>0){
                num = dimissionService.updataRank(dimiId,3);
                return Msg.success();
            }else{
                return Msg.fail();
            }
            }else {
                num = taskService.updataTask(taskId,3);
                if (num > 0) {
                    num = dimissionService.updataRank(dimiId,4);
                    Dimission dimission = dimissionService.findById(dimiId);
                    num = taskService.addTask(4,dimission.getUid(),7,user.getRealName(),user.getId(),"销号");
                    return Msg.success();
                } else {
                    return Msg.fail();
                }
            }
        }

    }

}
