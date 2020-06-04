package com.qzl.oamanages.oa.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Dimission {

    private Integer id;

    private String title;

    private String content;

    private double money;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dimissionDate;

    private int rank;

    private Integer parentUser;

    private Integer uid;

    private Integer did;

    private String realName;

    private String deptName;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getParentUser() {
        return parentUser;
    }

    public void setParentUser(Integer parentUser) {
        this.parentUser = parentUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Date getDimissionDate() {
        return dimissionDate;
    }

    public void setDimissionDate(Date dimissionDate) {
        this.dimissionDate = dimissionDate;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
