package com.qzl.oamanages.shiro.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class OaUser implements Serializable {
    private Integer id;

    private String realName;//真实姓名

    private String userName;//登录名称

    private String password;//登录密码

    private String email;//用户邮箱
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;//生日

    private int sex;//性别:0 男 1女

    private String phone;//手机号

    private String address;//现住址

    private Integer deptId;//部门ID

    private OaPost oaPost;//岗位

    private OaDept oaDept;//部门

    private int principal;//是否负责人

    private Integer postId;//岗位ID

    private Integer roleid;//角色ID

    private OaRole oaRole;//角色

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public OaPost getOaPost() {
        return oaPost;
    }

    public void setOaPost(OaPost oaPost) {
        this.oaPost = oaPost;
    }

    public OaDept getOaDept() {
        return oaDept;
    }

    public void setOaDept(OaDept oaDept) {
        this.oaDept = oaDept;
    }

    public int getPrincipal() {
        return principal;
    }

    public void setPrincipal(int principal) {
        this.principal = principal;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public OaRole getOaRole() {
        return oaRole;
    }

    public void setOaRole(OaRole oaRole) {
        this.oaRole = oaRole;
    }
}
