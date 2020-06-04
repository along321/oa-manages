package com.qzl.oamanages.shiro.controller;

import com.qzl.oamanages.shiro.entity.OaMenu;
import com.qzl.oamanages.shiro.entity.OaRole;
import com.qzl.oamanages.shiro.entity.OaUser;
import com.qzl.oamanages.shiro.service.OaMenuService;
import com.qzl.oamanages.shiro.service.OaRoleService;
import com.qzl.oamanages.shiro.service.OaUserService;
import com.qzl.oamanages.utils.Msg;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {


    @Autowired
    OaUserService oaUserService;

    @Autowired
    OaRoleService oaRoleService;

    @Autowired
    OaMenuService oaMenuService;

    /**
     * 用户登录
     * @param request
     * @param username
     * @param password
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Msg loginUser(HttpServletRequest request, String username, String password, Model model, HttpSession session,boolean rememberMe,String captcha) {


        //校验验证码
        //session中的验证码
        String sessionCaptcha = (String) SecurityUtils.getSubject().getSession().getAttribute(CaptchaController.KEY_CAPTCHA);
        Msg msg = new Msg();
        try {
        if ("" == captcha || !captcha.equalsIgnoreCase(sessionCaptcha)) {
            throw new IncorrectCredentialsException("验证码错误");
        }

        //对密码进行加密
        password=new SimpleHash("md5", password, ByteSource.Util.bytes(username.toLowerCase() + "shiro"),1024).toHex();
        //如果有点击  记住我
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password,rememberMe);
//        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();


            //登录操作
            subject.login(usernamePasswordToken);
            OaUser user=(OaUser) subject.getPrincipal();
            //更新用户登录时间，也可以在ShiroRealm里面做
            msg.setCode(200);
            msg.setMsg("登录成功");
        } catch(Exception e) {
            //登录失败从request中获取shiro处理的异常信息 shiroLoginFailure:就是shiro异常类的全类名
            String exception = (String) request.getAttribute("shiroLoginFailure");
            model.addAttribute("msg",e.getMessage());
            //返回登录页面
            msg.setCode(100);
            msg.setMsg(e.getMessage());
        }
        return msg;
    }
    @PostMapping("/index")
    public Msg main(){
        Msg msg = new Msg();
        Subject subject = SecurityUtils.getSubject();
        OaUser user=(OaUser) subject.getPrincipal();
        Map<String,Object> map = new HashMap<>();
        map.put("user",user);//当前用户
        OaRole role = oaRoleService.findByid(user.getId());
        map.put("roleName",role);//当前角色
        List<OaMenu> menus = oaMenuService.allMenu(user.getId());
        map.put("data",menus);

        msg.setCode(200);
        msg.setMsg("ok");
        msg.setExtend(map);
        return msg;
    }
}
