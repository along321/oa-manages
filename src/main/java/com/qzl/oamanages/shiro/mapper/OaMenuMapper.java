package com.qzl.oamanages.shiro.mapper;

import com.qzl.oamanages.shiro.entity.OaMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OaMenuMapper {

    public List<OaMenu> findMenuByMid(@Param("rid") Integer rid);

    public List<OaMenu> findMenuByParentId(@Param("parentid") Integer parentid);

    public List<OaMenu> allMenu();//全部菜单

    int addMenu(OaMenu menu);//添加菜单

    OaMenu menuById(@Param("id") Integer id);//回显菜单

    int editMenu(OaMenu menu);//修改菜单

    int delMenu(int id);//停用用户

    List<OaMenu> menusByRole(@Param("rid") Integer rid);
}
