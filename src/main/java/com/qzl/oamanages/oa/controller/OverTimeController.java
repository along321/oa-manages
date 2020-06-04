package com.qzl.oamanages.oa.controller;

import com.github.pagehelper.PageInfo;
import com.qzl.oamanages.oa.entity.Leave;
import com.qzl.oamanages.oa.entity.OverTime;
import com.qzl.oamanages.oa.service.LeaveService;
import com.qzl.oamanages.oa.service.OverTimeService;
import com.qzl.oamanages.oa.service.TaskService;
import com.qzl.oamanages.utils.LayuiMsg;
import com.qzl.oamanages.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/over")
public class OverTimeController {

    @Autowired
    LeaveService leaveService;

    @Autowired
    TaskService taskService;

    @Autowired
    OverTimeService overTimeService;


    @RequestMapping("/repeal")
    public Msg repeal(@RequestParam("id") Integer id){
        int num = overTimeService.delTime(id);
        if(num>0){
            return Msg.success();
        }else{
            return Msg.fail();
        }
    }

    @RequestMapping("/commOver")
    public Msg commLeave(@RequestParam("id") Integer id,@RequestParam("userName") String userName,@RequestParam("uid") Integer uid){
        OverTime overTime = overTimeService.findById(id);
        int num = taskService.addTask(2,overTime.getId(),overTime.getParentuser(),userName,uid,"加班");
        if(num>0){
            num = overTimeService.updataRank(overTime.getId(),2);
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
    @RequestMapping("/yilist")
    public LayuiMsg yilist(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                             @RequestParam(required = false,defaultValue = "5")Integer pageSize){
        PageInfo<OverTime> pageInfo = overTimeService.yiOver(pageNum,pageSize);
        LayuiMsg msg = new LayuiMsg();
        msg.setCode(100);
        msg.setMsg("操作成功");
        msg.setCount(pageInfo.getTotal());
        msg.setData(pageInfo.getList());
        return msg;
    }

    @RequestMapping("/comlist")
    public LayuiMsg userList(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                             @RequestParam(required = false,defaultValue = "5")Integer pageSize){
        PageInfo<OverTime> pageInfo = overTimeService.weiOver(pageNum,pageSize,3);
        LayuiMsg msg = new LayuiMsg();
        msg.setCode(100);
        msg.setMsg("操作成功");
        msg.setCount(pageInfo.getTotal());
        msg.setData(pageInfo.getList());
        return msg;
    }
    @RequestMapping("/alrList")
    public LayuiMsg alrList(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                             @RequestParam(required = false,defaultValue = "5")Integer pageSize){
            PageInfo<OverTime> pageInfo = overTimeService.weiOver(pageNum,pageSize,2);
            LayuiMsg msg = new LayuiMsg();
            msg.setCode(100);
            msg.setMsg("操作成功");
            msg.setCount(pageInfo.getTotal());
            msg.setData(pageInfo.getList());
        return msg;
    }
    @PostMapping("/addOver")
    public Msg addLeave(OverTime overTime){
        int num = overTimeService.addOver(overTime);
        if(num>0){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
    @PostMapping("/editOver")
    public Msg editLeave(OverTime overTime){
        int num = overTimeService.editOver(overTime);
        if(num>0){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
    @RequestMapping("/notList")
    public LayuiMsg allLeave(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                             @RequestParam(required = false,defaultValue = "5")Integer pageSize){
        PageInfo<OverTime> pageInfo = overTimeService.weiOver(pageNum,pageSize,1);
        LayuiMsg msg = new LayuiMsg();
        msg.setCode(100);
        msg.setMsg("操作成功");
        msg.setCount(pageInfo.getTotal());
        msg.setData(pageInfo.getList());
        return msg;
    }
}
