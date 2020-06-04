package com.qzl.oamanages.oa.controller;

import com.github.pagehelper.PageInfo;
import com.qzl.oamanages.shiro.entity.OaPost;
import com.qzl.oamanages.shiro.service.OaPostService;
import com.qzl.oamanages.utils.LayuiMsg;
import com.qzl.oamanages.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    OaPostService oaPostService;
    @RequestMapping("/list")
    public LayuiMsg list(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                         @RequestParam(required = false,defaultValue = "5")Integer pageSize){
        PageInfo<OaPost> pageInfo = oaPostService.pagePost(pageNum,pageSize);
        LayuiMsg msg = new LayuiMsg();
        msg.setCode(100);
        msg.setMsg("操作成功");
        msg.setCount(pageInfo.getTotal());
        msg.setData(pageInfo.getList());
        return msg;
    }
    @PostMapping("/addPost")
    public Msg addPost(OaPost oaPost){
        int num = oaPostService.addPost(oaPost);
        if(num>0){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
    @PostMapping("/delPost")
    public Msg delPost(@RequestParam("id") Integer id){
        int num = oaPostService.delPost(id);
        if(num>0){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
    @PostMapping("/editPost")
    public Msg editPost(OaPost oaPost){
        int num = oaPostService.editPost(oaPost);
        if(num>0){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
}
