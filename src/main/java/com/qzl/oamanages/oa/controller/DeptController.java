package com.qzl.oamanages.oa.controller;

import com.alibaba.fastjson.JSON;
import com.qzl.oamanages.shiro.entity.OaDept;
import com.qzl.oamanages.shiro.entity.OaMenu;
import com.qzl.oamanages.shiro.service.OaDeptService;
import com.qzl.oamanages.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    OaDeptService oaDeptService;


    @PostMapping("/list")
    public Msg list(){
        Msg msg = new Msg();
        List<OaDept> depts = oaDeptService.nodes();
        Map<String,Object> map = new HashMap<>();
//        System.out.println(JSON.toJSON(depts));
        map.put("data",JSON.toJSON(depts));
        msg.setCode(200);
        msg.setExtend(map);
        return msg;
    }
    @PostMapping("/addDept")
    public Msg addDept(OaDept dept){
        int num = oaDeptService.addDept(dept);
        if(num>0){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
    @PostMapping("/delDept")
    public Msg delDept(Integer id){
        int num = oaDeptService.delDept(id);
        if(num>0){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
}
