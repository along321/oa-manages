package com.qzl.oamanages.oa.entity;

import com.qzl.oamanages.shiro.entity.OaDept;
import com.qzl.oamanages.shiro.entity.OaPost;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DimiUser {

    private Integer id;

    private String realName;//真实姓名

    private String userName;//登录名称

    private String emali;//用户邮箱
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;//生日

    private int sex;//性别:0 男 1女

    private String phone;//手机号

    private String address;//现住址

    private Integer deptId;//部门ID

    private OaPost oaPost;//岗位

    private OaDept oaDept;//部门

    private Integer postId;//岗位ID

    private Date dimiDate;

    public Date getDimiDate() {
        return dimiDate;
    }

    public void setDimiDate(Date dimiDate) {
        this.dimiDate = dimiDate;
    }

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

    public String getEmali() {
        return emali;
    }

    public void setEmali(String emali) {
        this.emali = emali;
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

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }
}
