package com.qzl.oamanages.oa.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.qzl.oamanages.shiro.entity.OaDept;
import com.qzl.oamanages.shiro.entity.OaMenu;
import com.qzl.oamanages.shiro.entity.OaRole;
import com.qzl.oamanages.shiro.entity.OaUser;
import com.qzl.oamanages.shiro.service.OaMenuService;
import com.qzl.oamanages.shiro.service.OaRoleService;
import com.qzl.oamanages.utils.LayuiMsg;
import com.qzl.oamanages.utils.Msg;
import com.qzl.oamanages.utils.ZtreeMsg;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    OaRoleService oaRoleService;

    @Autowired
    OaMenuService oaMenuService;

    @RequestMapping("/list")
    public LayuiMsg list(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                         @RequestParam(required = false,defaultValue = "5")Integer pageSize){
        PageInfo<OaRole> pageInfo = oaRoleService.roles(pageNum,pageSize);
        LayuiMsg msg = new LayuiMsg();
        msg.setCode(100);
        msg.setMsg("操作成功");
        msg.setCount(pageInfo.getTotal());
        msg.setData(pageInfo.getList());
        return msg;
    }
    @PostMapping("/editRole")
    public Msg editRole(OaRole oaRole){
        int num = oaRoleService.update(oaRole);
        if(num>0){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
    @PostMapping("/addRole")
    public Msg addRole(OaRole oaRole){
        int num = oaRoleService.addRole(oaRole);
        if(num>0){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
    @PostMapping("/menus")
    public Msg menus(@RequestParam("rid") Integer rid){
        Msg msg = new Msg();
        List<OaMenu> menus =oaMenuService.roleMenus();
        Map<String,Object> map = new HashMap<>();
        List<ZtreeMsg> data = new ArrayList<>();
        List<OaMenu> roleMenus = oaMenuService.menusByRole(rid);
        menus.forEach((d)->{
            ZtreeMsg ztreeMsg = new ZtreeMsg();
            ztreeMsg.setId(d.getId());
            ztreeMsg.setpId(d.getParentId());
            ztreeMsg.setName(d.getName());
            for (OaMenu roleMenu : roleMenus) {
                if(roleMenu.getId() == d.getId()){
                    ztreeMsg.setChecked(true);
                    break;
                }
            }
            data.add(ztreeMsg);
        });
        map.put("data", JSON.toJSON(data));
        msg.setExtend(map);
        msg.setCode(200);
        return msg;
    }
    @PostMapping("/editMenu")
    public Msg editMenu(@RequestParam("data[]")Integer[] nodes,@RequestParam("rid") Integer rid)throws Exception{
        int num = oaRoleService.editMenuByRole(nodes, rid);
        if(num>0){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
}
