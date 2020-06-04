package com.qzl.oamanages.oa.controller;

import com.github.pagehelper.PageInfo;
import com.qzl.oamanages.oa.entity.DimiUser;
import com.qzl.oamanages.oa.entity.Dimission;
import com.qzl.oamanages.oa.service.DimiUserService;
import com.qzl.oamanages.oa.service.DimissionService;
import com.qzl.oamanages.oa.service.TaskService;
import com.qzl.oamanages.shiro.entity.OaUser;
import com.qzl.oamanages.shiro.service.OaUserService;
import com.qzl.oamanages.utils.LayuiMsg;
import com.qzl.oamanages.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/diUser")
public class DimiUserController {


    @Autowired
    DimiUserService dimiUserService;

    @RequestMapping("/comDimi")
    public LayuiMsg comDimi(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                            @RequestParam(required = false,defaultValue = "5")Integer pageSize){
        PageInfo<DimiUser> pageInfo = dimiUserService.allDimiUser(pageNum,pageSize);
        LayuiMsg msg = new LayuiMsg();
        msg.setCode(100);
        msg.setMsg("操作成功");
        msg.setCount(pageInfo.getTotal());
        msg.setData(pageInfo.getList());
        return msg;
    }
}
