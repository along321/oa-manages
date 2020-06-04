package com.qzl.oamanages.shiro.entity;

import java.util.ArrayList;
import java.util.List;

public class OaMenu {
    private Integer id;//菜单ID

    private String name;//菜单名称

    private Integer parentId;//父级菜单ID

    private String icon;//菜单图标

    private String url;//菜单路径

    private String permission;//拥有的权限

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    private List<OaMenu> children = new ArrayList<>();//子菜单

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<OaMenu> getChildren() {
        return children;
    }

    public void setChildren(List<OaMenu> children) {
        this.children = children;
    }
}
