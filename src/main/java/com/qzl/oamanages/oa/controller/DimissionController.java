package com.qzl.oamanages.oa.controller;

import com.github.pagehelper.PageInfo;
import com.qzl.oamanages.oa.entity.Dimission;
import com.qzl.oamanages.oa.entity.Leave;
import com.qzl.oamanages.oa.entity.OverTime;
import com.qzl.oamanages.oa.entity.Task;
import com.qzl.oamanages.oa.service.DimissionService;
import com.qzl.oamanages.oa.service.LeaveService;
import com.qzl.oamanages.oa.service.OverTimeService;
import com.qzl.oamanages.oa.service.TaskService;
import com.qzl.oamanages.shiro.entity.OaUser;
import com.qzl.oamanages.shiro.service.OaUserService;
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
@RequestMapping("/dimi")
public class DimissionController {

    @Autowired
    DimissionService dimissionService;

    @Autowired
    TaskService taskService;

    @Autowired
    OaUserService oaUserService;

    @RequestMapping("remUser")
    public Msg remUser(Integer userId,Integer taskId){
        OaUser oaUser = oaUserService.userByid(userId);
        int num = oaUserService.addDimiUser(oaUser);
        if(num>0){
            num = oaUserService.delUser(oaUser.getId());
            num =taskService.updataTask(taskId,3);
            return Msg.success();
        }else{
            return Msg.fail();
        }
    }

    @PostMapping("/addDimission")
    public Msg addLeave(Dimission dimission){
        int num = dimissionService.addDimission(dimission);
        if(num>0){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
    @RequestMapping("/comDimi")
    public LayuiMsg comDimi(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                            @RequestParam(required = false,defaultValue = "5")Integer pageSize){
        PageInfo<Dimission> pageInfo = dimissionService.weiLeave(pageNum,pageSize,4);
        LayuiMsg msg = new LayuiMsg();
        msg.setCode(100);
        msg.setMsg("操作成功");
        msg.setCount(pageInfo.getTotal());
        msg.setData(pageInfo.getList());
        return msg;
    }
    @RequestMapping("/alrDimi")
    public LayuiMsg alrDimi(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                            @RequestParam(required = false,defaultValue = "5")Integer pageSize){
        PageInfo<Dimission> pageInfo = dimissionService.weiLeave(pageNum,pageSize,2);
        LayuiMsg msg = new LayuiMsg();
        msg.setCode(100);
        msg.setMsg("操作成功");
        msg.setCount(pageInfo.getTotal());
        msg.setData(pageInfo.getList());
        return msg;
    }
    @RequestMapping("/weiDimi")
    public LayuiMsg weiDimi(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                             @RequestParam(required = false,defaultValue = "5")Integer pageSize){
        PageInfo<Dimission> pageInfo = dimissionService.weiLeave(pageNum,pageSize,1);
        LayuiMsg msg = new LayuiMsg();
        msg.setCode(100);
        msg.setMsg("操作成功");
        msg.setCount(pageInfo.getTotal());
        msg.setData(pageInfo.getList());
        return msg;
    }
    @PostMapping("/editDimission")
    public Msg editDimission(Dimission dimission){
        int num = dimissionService.editDimi(dimission);
        if(num>0){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
    @PostMapping("/delDimi")
    public Msg delDimi(@RequestParam("id") Integer id){
        int num = dimissionService.delDimi(id);
        if(num>0){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
    @RequestMapping("/commDimi")
    public Msg commDimi(@RequestParam("id") Integer id,@RequestParam("userName") String userName,@RequestParam("uid") Integer uid){
         Dimission dimission =  dimissionService.findById(id);
        int num = taskService.addTask(3,dimission.getId(),dimission.getParentUser(),userName,uid,"离职");
        if(num>0){
            num = dimissionService.updataRank(dimission.getId(),2);
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
}
