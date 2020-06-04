package com.qzl.oamanages.oa.controller;

import com.qzl.oamanages.oa.entity.Dimission;
import com.qzl.oamanages.oa.entity.Leave;
import com.qzl.oamanages.oa.entity.OverTime;
import com.qzl.oamanages.oa.entity.Task;
import com.qzl.oamanages.oa.service.DimissionService;
import com.qzl.oamanages.oa.service.LeaveService;
import com.qzl.oamanages.oa.service.OverTimeService;
import com.qzl.oamanages.oa.service.TaskService;
import com.qzl.oamanages.shiro.entity.*;
import com.qzl.oamanages.shiro.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ToController {

    @Autowired
    OaUserService oaUserService;

    @Autowired
    OaRoleService oaRoleService;

    @Autowired
    OaPostService oaPostService;

    @Autowired
    OaMenuService oaMenuService;

    @Autowired
    OaDeptService oaDeptService;

    @Autowired
    LeaveService leaveService;

    @Autowired
    TaskService taskService;

    @Autowired
    OverTimeService overTimeService;

    @Autowired
    DimissionService dimissionService;

    @RequestMapping("/userlist")
    public String toUserList(){
        return "page/main/Userlist";
    }

    @RequestMapping("/useredit/{id}")
    public  String toUserEdit(@PathVariable("id") Integer id, Model model){
        OaUser user = oaUserService.userByid(id);
        List<OaRole> roles = oaRoleService.allRole();
        model.addAttribute("user",user);
        model.addAttribute("roles",roles);
        model.addAttribute("role",oaRoleService.findByid(user.getId()));
        model.addAttribute("posts",oaPostService.allPost());
        return "page/main/admin-edit";
    }
    @RequestMapping("/adduser")
    public  String toUseradd(Model model){
        List<OaRole> roles = oaRoleService.allRole();
        model.addAttribute("roles",roles);
        model.addAttribute("posts",oaPostService.allPost());
        return "page/open/addUser";
    }
//    *********************************************************************
    //跳转到部门
    @RequestMapping("/dept")
    public String toDept(){
        return "page/open/depts";
    }
//    *********************************************************************
    //跳转到菜单
    @RequestMapping("/menu")
    public String toMenu(){
        return "page/main/menuList";
    }
    @RequestMapping("/addMenu/{parentId}")
    public String addMenu(@PathVariable("parentId")Integer parentId,Model model){
        model.addAttribute("parentId",parentId);
        return "page/open/addMenu";
    }
    @RequestMapping("/editMenu/{id}")
    public String editMenu(@PathVariable("id")Integer id,Model model){
        OaMenu oaMenu = oaMenuService.menuById(id);
        model.addAttribute("menu",oaMenu);
        return "page/open/editMenu";
    }
    //    *********************************************************************
    //跳转到角色
    @RequestMapping("/role")
    public String toRole(){
        return "page/main/roleList";
    }
    //修改角色
    @RequestMapping("/roleEdit/{id}")
    public String roleEdit(@PathVariable("id")Integer id,Model model){
        OaRole oaRole = oaRoleService.toRole(id);
        model.addAttribute("role",oaRole);
        return "page/open/editRole";
    }
    //添加角色
    @RequestMapping("/addRole")
    public String addRole(){
        return "page/open/addRole";
    }
    @RequestMapping("/roleMenus/{id}")
    public String roleMenus(@PathVariable("id") Integer rid,Model model){
        model.addAttribute("rid",rid);
        return "page/open/menus";
    }

//    *********************************************************************
    //跳转到所有部门
    @RequestMapping("/depts")
    public String toDepts(){
        return "page/main/deptList";
    }
    @RequestMapping("/addDept/{id}")
    public String addDept(@PathVariable("id")Integer id,Model model){
        model.addAttribute("id",id);
        return "page/open/addDept";
    }
    @RequestMapping("/editDept/{id}")
    public String editDept(@PathVariable("id")Integer id,Model model){
        OaDept oaDept = oaDeptService.deptByid(id);
        oaDept.setParentDept(oaDeptService.deptByid(oaDept.getParentId()));
        model.addAttribute("dept",oaDept);
        return "page/open/editDept";
    }
    //    *********************************************************************
    //跳转到岗位
    @RequestMapping("/post")
    public String toPost(){
        return "page/main/postList";
    }

    @RequestMapping("/addPost")
    public String addPost(){
        return "page/open/addPost";
    }
    @RequestMapping("/editPost/{id}")
    public String editPost(@PathVariable("id")Integer id,Model model){
        OaPost oaPost = oaPostService.oaPostById(id);
        model.addAttribute("post",oaPost);
        return "page/open/editPost";
    }

    //跳转到请假
    @RequestMapping("/leave")
    public String leave(){
        return "page/leave/leaveList";
    }
    //跳转到请假
    @RequestMapping("/leaveComm")
    public String leaveComm(){
        return "page/leave/leaveComm";
    }
    //跳转到请假
    @RequestMapping("/addLeave")
    public String leave(Model model){
        Subject subject = SecurityUtils.getSubject();
        OaUser user=(OaUser) subject.getPrincipal();
        model.addAttribute("user",user);
        return "page/leave/addLeave";
    }
    @RequestMapping("/editLeave/{id}")
    public String editLeave(@PathVariable("id")Integer id,Model model){
        Leave leave =  leaveService.findById(id);
        model.addAttribute("leave",leave);
        Subject subject = SecurityUtils.getSubject();
        OaUser user=(OaUser) subject.getPrincipal();
        model.addAttribute("user",user);
        return "page/leave/editLeave";
    }
    //跳转到已完成的请假
    @RequestMapping("/allLeave")
    public String allLeave(){
        return "page/leave/allLeave";
    }
    //跳转到所有的请假
    @RequestMapping("/yiLeave")
    public String yiLeave(){
        return "page/personnel/leaveList";
    }
    //跳转到待办任务
    @RequestMapping("/task")
    public String task(){
        return "page/task/taskList";
    }
    //跳转到审核窗口
    @RequestMapping("/auditTask/{id}")
    public String audit(@PathVariable("id") Integer id,Model model){
        String fan = "";
        Task task = taskService.findById(id);
        if(task.getTask()==1){
            Leave leave = leaveService.findById(task.getTaskId());
            model.addAttribute("leave",leave);
            fan = "page/task/leaveAudit";
        }else if(task.getTask()==2){
            OverTime overTime = overTimeService.findById(task.getTaskId());
            model.addAttribute("over",overTime);
            fan = "page/task/overAudit";
        }else if(task.getTask()==3){
            Dimission dimission = dimissionService.findById(task.getTaskId());
            model.addAttribute("dimi",dimission);
            fan = "page/task/dimiAudit";
        }else if(task.getTask()==4){
            OaUser oaUser = oaUserService.userByid(task.getTaskId());
            model.addAttribute("user",oaUser);
            fan = "page/task/remAudit";
        }
        model.addAttribute("task",task);
        return fan;
    }
    //跳转到已办任务
    @RequestMapping("/havetask")
    public String havetask(){
        return "page/task/haveTask";
    }
    //跳转到加班申请
    @RequestMapping("/addOverTime")
    public String toOverTime(Model model){
        Subject subject = SecurityUtils.getSubject();
        OaUser user=(OaUser) subject.getPrincipal();
        model.addAttribute("user",user);
        return "page/overTime/addOverTime";
    }
    //跳转到已办任务
    @RequestMapping("/editOver/{id}")
    public String editOver(@PathVariable("id") Integer id,Model model){
        OverTime overTime = overTimeService.findById(id);
        model.addAttribute("over",overTime);
        return "page/overTime/editOver";
    }
    //跳转到未申报加班
    @RequestMapping("/notOver")
    public String notOver(){
        return "page/overTime/notOver";
    }
    //跳转到已申报加班
    @RequestMapping("/alrList")
    public String alrList(){
        return "page/overTime/alrList";
    }
    //跳转到完成加班
    @RequestMapping("/comList")
    public String comList(){
        return "page/overTime/comList";
    }
    //跳转到所有加班
    @RequestMapping("/yiList")
    public String yiList(){
        return "page/personnel/comList";
    }
    //跳转到离职申请
    @RequestMapping("/addDimi")
    public String addDimi(Model model){
        Subject subject = SecurityUtils.getSubject();
        OaUser user=(OaUser) subject.getPrincipal();
        model.addAttribute("user",user);
        return "page/dimisseion/addDimission";
    }
    //跳转到离职未提交
    @RequestMapping("/weiDimi")
    public String weiDimi(){
        return "page/dimisseion/notDimission.html";
    }
    //跳转到离职已提交
    @RequestMapping("/alrDimi")
    public String alrDimi(){
        return "page/dimisseion/alrDimission.html";
    }
    //跳转到离职已提交
    @RequestMapping("/comDimi")
    public String comDimi(){
        return "page/dimisseion/comDimission.html";
    }
    //跳转到修改离职
    @RequestMapping("/editDimi/{id}")
    public String editDimi(@PathVariable("id")Integer id, Model model){
        Subject subject = SecurityUtils.getSubject();
        OaUser user=(OaUser) subject.getPrincipal();
        Dimission dimission = dimissionService.findById(id);
        model.addAttribute("dimi",dimission);
        model.addAttribute("user",user);
        return "page/dimisseion/editDimission";
    }
    @RequestMapping("/dimiUser")
    public String dimiUser(){
        return "page/dimiUser/dimiUser";
    }
}
