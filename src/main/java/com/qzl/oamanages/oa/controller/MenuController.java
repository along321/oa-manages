package com.qzl.oamanages.oa.controller;

import com.alibaba.fastjson.JSON;
import com.qzl.oamanages.shiro.entity.OaMenu;
import com.qzl.oamanages.shiro.service.OaMenuService;
import com.qzl.oamanages.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    OaMenuService oaMenuService;

    @PostMapping("/list")
    public Msg list(){
        Msg msg = new Msg();
        List<OaMenu> menus = oaMenuService.allMenu();
        Map<String,Object> map = new HashMap<>();
//        System.out.println(JSON.toJSON(menus));
        map.put("data",JSON.toJSON(menus));
        msg.setCode(200);
        msg.setExtend(map);
        return msg;
    }
    @PostMapping("/addMenu")
    public Msg addMenu(OaMenu menu){
        int num = oaMenuService.addMenu(menu);
        if(num>0){
            return Msg.success();
        }else{
            return Msg.fail();
        }
    }
    @PostMapping("/editMenu")
    public Msg editMenu(OaMenu menu){
        int num = oaMenuService.editMenu(menu);
        if(num>0){
            return Msg.success();
        }else{
            return Msg.fail();
        }
    }
    @PostMapping("/delMenu")
    public Msg delMenu(@RequestParam("id")int id){
        int num = oaMenuService.delMenu(id);
        if(num>0){
            return Msg.success();
        }else{
            return Msg.fail();
        }
    }
}
