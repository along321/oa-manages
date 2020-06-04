package com.qzl.oamanages.shiro.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OaDept  implements Serializable {
    private Integer id;//部门编号

    private String name;//部门名称

    private Integer parentId;//父级部门ID

    private Integer rank;//部门级别

    private String tel;//部门电话

    private OaDept parentDept;//父级对象

    private List<OaDept> children = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public OaDept getParentDept() {
        return parentDept;
    }

    public void setParentDept(OaDept parentDept) {
        this.parentDept = parentDept;
    }

    public List<OaDept> getChildren() {
        return children;
    }

    public void setChildren(List<OaDept> children) {
        this.children = children;
    }
}
