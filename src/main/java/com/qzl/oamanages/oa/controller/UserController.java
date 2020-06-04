package com.qzl.oamanages.oa.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.qzl.oamanages.shiro.entity.OaDept;
import com.qzl.oamanages.shiro.entity.OaUser;
import com.qzl.oamanages.shiro.service.OaDeptService;
import com.qzl.oamanages.shiro.service.OaPostService;
import com.qzl.oamanages.shiro.service.OaUserService;
import com.qzl.oamanages.utils.LayuiMsg;
import com.qzl.oamanages.utils.Msg;
import com.qzl.oamanages.utils.UserList;
import com.qzl.oamanages.utils.ZtreeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    OaUserService oaUserService;

    @Autowired
    OaDeptService oaDeptService;

    @Autowired
    OaPostService oaPostService;

    @RequestMapping("/list")
    public LayuiMsg userList(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                        @RequestParam(required = false,defaultValue = "5")Integer pageSize){
        PageInfo<OaUser> pageInfo = oaUserService.allUser(pageNum,pageSize);
        LayuiMsg msg = new LayuiMsg();
        msg.setCode(100);
        msg.setMsg("操作成功");
        msg.setCount(pageInfo.getTotal());
        msg.setData(pageInfo.getList());
        return msg;
    }

    @PostMapping("/edituser")
    public Msg editUser(OaUser user){
        Msg msg = new Msg();
        int num = oaUserService.editUser(user);
        if(num>0){
            msg.setCode(200);
        }else {
            msg.setCode(100);
        }
        return msg;
    }
    @PostMapping("/depts")
    public Msg depts(){
        Msg msg = new Msg();
        List<OaDept> depts = oaDeptService.allDepts();
        Map<String,Object> map = new HashMap<>();
        List<ZtreeMsg> data = new ArrayList<>();
        depts.forEach((d)->{
            ZtreeMsg ztreeMsg = new ZtreeMsg();
            ztreeMsg.setId(d.getId());
            ztreeMsg.setpId(d.getParentId());
            ztreeMsg.setName(d.getName());
            data.add(ztreeMsg);
        });
        map.put("data", JSON.toJSON(data));
        msg.setExtend(map);
        msg.setCode(200);
        return msg;
    }

    @PostMapping("/adduser")
    public Msg addUser(OaUser oaUser){
        Msg msg = new Msg();
        int num = oaUserService.addUser(oaUser);
        if(num>0){
            msg.setCode(200);
            msg.setMsg("添加成功");
        }else{
            msg.setCode(100);
            msg.setMsg("添加失败");
        }
        return msg;
    }
    @PostMapping("/del")
    public Msg delUser(@RequestParam("id") Integer id){
        Msg msg = new Msg();
        int num = oaUserService.delUser(id);
        if(num>0){
            msg.setCode(200);
            msg.setMsg("删除成功");
        }else{
            msg.setCode(100);
            msg.setMsg("删除失败");
        }
        return msg;
    }
    @PostMapping("/loadPost")
    public Msg loadPost(){
        return Msg.success().add("data",oaPostService.allPost());
    }
    @RequestMapping("/sreach")
    public LayuiMsg sreach(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                             @RequestParam(required = false,defaultValue = "5")Integer pageSize,
                           UserList userList){
        PageInfo<OaUser> pageInfo = oaUserService.userBySreach(pageNum,pageSize,userList);
        LayuiMsg msg = new LayuiMsg();
        msg.setCode(100);
        msg.setMsg("操作成功");
        msg.setCount(pageInfo.getTotal());
        msg.setData(pageInfo.getList());
        return msg;
    }
}
