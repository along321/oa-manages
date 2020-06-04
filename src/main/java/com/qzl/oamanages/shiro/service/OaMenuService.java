package com.qzl.oamanages.shiro.service;

import com.qzl.oamanages.shiro.entity.OaMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OaMenuService {
//    查询当前用户的所有菜单
    public List<OaMenu> allMenu(Integer rid);

//    查询当前角色下的一级菜单
    public List<OaMenu> findMenuByMid(Integer rid);
//    查询当前菜单下的子菜单
    public List<OaMenu> findMenuByParentId(@Param("parentid") Integer parentid);

    public List<OaMenu> allMenu();//全部菜单


    int addMenu(OaMenu menu);//添加菜单

    OaMenu menuById(Integer id);//回显菜单

    int editMenu(OaMenu menu);//修改菜单

    int delMenu(int id);//停用菜单

    List<OaMenu> roleMenus();//所有菜单

    List<OaMenu> menusByRole(Integer rid);//当前用户的所有菜单
}
