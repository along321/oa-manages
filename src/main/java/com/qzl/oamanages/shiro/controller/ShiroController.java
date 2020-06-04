package com.qzl.oamanages.shiro.controller;

import com.qzl.oamanages.shiro.entity.OaUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ShiroController {

    /**
     * 访问项目根路径
     * @return
     */
    @RequestMapping("/")
    public String toindex(){
        Subject subject = SecurityUtils.getSubject();
        OaUser user=(OaUser) subject.getPrincipal();
        if (user == null){
            return "login";
        }else{
            return "page/login/index";
        }
    }
    /**
     * 访问项目根路径
     * @return
     */
    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String toMain(){
        return "page/login/index";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

}
