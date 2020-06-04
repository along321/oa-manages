package com.qzl.oamanages.oa.entity;

import com.qzl.oamanages.shiro.entity.OaDept;
import com.qzl.oamanages.shiro.entity.OaUser;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Leave {

    private Integer id;

    private Integer uid;

    private Integer did;

    private String leatype;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applyDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private int numDays;

    private String conent;

    private int rank;

    private Integer parentuser;

    private String realName;

    private String deptName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getLeatype() {
        return leatype;
    }

    public void setLeatype(String leatype) {
        this.leatype = leatype;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getNumDays() {
        return numDays;
    }

    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }

    public String getConent() {
        return conent;
    }

    public void setConent(String conent) {
        this.conent = conent;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Integer getParentuser() {
        return parentuser;
    }

    public void setParentuser(Integer parentuser) {
        this.parentuser = parentuser;
    }

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
}
