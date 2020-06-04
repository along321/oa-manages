package com.qzl.oamanages.shiro.entity;

import java.io.Serializable;
import java.util.List;

public class OaRole  implements Serializable {
    private Integer id;//角色ID

    private String name;//角色名

    private String title;//角色标题

    private List<OaMenu> menuList;//角色对应的菜单

    public List<OaMenu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<OaMenu> menuList) {
        this.menuList = menuList;
    }

    @Override
    public String toString() {
        return "OaRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
