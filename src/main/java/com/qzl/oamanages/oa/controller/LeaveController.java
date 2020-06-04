package com.qzl.oamanages.oa.controller;

import com.github.pagehelper.PageInfo;
import com.qzl.oamanages.oa.entity.Leave;
import com.qzl.oamanages.oa.service.LeaveService;
import com.qzl.oamanages.oa.service.TaskService;
import com.qzl.oamanages.shiro.entity.OaDept;
import com.qzl.oamanages.shiro.entity.OaUser;
import com.qzl.oamanages.utils.LayuiMsg;
import com.qzl.oamanages.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    LeaveService leaveService;

    @Autowired
    TaskService taskService;



    @RequestMapping("/commLeave")
    public Msg commLeave(@RequestParam("id") Integer id,@RequestParam("userName") String userName,@RequestParam("uid") Integer uid){
        Leave leave = leaveService.findById(id);
        int num = taskService.addTask(1,leave.getId(),leave.getParentuser(),userName,uid,"请假");
        if(num>0){
            num = leaveService.updataRank(leave.getId(),2);
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
    @RequestMapping("/leaverList")
    public LayuiMsg leaverList(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                             @RequestParam(required = false,defaultValue = "5")Integer pageSize){
        PageInfo<Leave> pageInfo = leaveService.yiLeave(pageNum,pageSize);
        LayuiMsg msg = new LayuiMsg();
        msg.setCode(100);
        msg.setMsg("操作成功");
        msg.setCount(pageInfo.getTotal());
        msg.setData(pageInfo.getList());
        return msg;
    }

    @RequestMapping("/list")
    public LayuiMsg userList(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                             @RequestParam(required = false,defaultValue = "5")Integer pageSize){
        PageInfo<Leave> pageInfo = leaveService.allLeave(pageNum,pageSize);
        LayuiMsg msg = new LayuiMsg();
        msg.setCode(100);
        msg.setMsg("操作成功");
        msg.setCount(pageInfo.getTotal());
        msg.setData(pageInfo.getList());
        return msg;
    }
    @RequestMapping("/commList")
    public LayuiMsg commList(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                             @RequestParam(required = false,defaultValue = "5")Integer pageSize){
        PageInfo<Leave> pageInfo = leaveService.commList(pageNum,pageSize);
        LayuiMsg msg = new LayuiMsg();
        msg.setCode(100);
        msg.setMsg("操作成功");
        msg.setCount(pageInfo.getTotal());
        msg.setData(pageInfo.getList());
        return msg;
    }
    @PostMapping("/addLeave")
    public Msg addLeave(Leave leave){
        int num = leaveService.addLeave(leave);
        if(num>0){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
    @PostMapping("/editLeave")
    public Msg editLeave(Leave leave){
        int num = leaveService.editLeave(leave);
        if(num>0){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
    @PostMapping("/repeal")
    public Msg repeal(Integer id){
        int num = leaveService.updataRank(id,1);
        if(num>0){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
    @RequestMapping("/allLeave")
    public LayuiMsg allLeave(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                             @RequestParam(required = false,defaultValue = "5")Integer pageSize){
        PageInfo<Leave> pageInfo = leaveService.weiLeave(pageNum,pageSize);
        LayuiMsg msg = new LayuiMsg();
        msg.setCode(100);
        msg.setMsg("操作成功");
        msg.setCount(pageInfo.getTotal());
        msg.setData(pageInfo.getList());
        return msg;
    }
}
