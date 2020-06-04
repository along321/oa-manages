package com.qzl.oamanages.shiro.service;

import com.github.pagehelper.PageInfo;
import com.qzl.oamanages.shiro.entity.OaMenu;
import com.qzl.oamanages.shiro.entity.OaRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface OaRoleService {
    //根据用户id查询当前用户角色
    public Set<String> findRoleByid(Integer uid);

    //根据用户id查询当前用户角色
    public OaRole findByid(Integer uid);

    public List<OaRole> allRole();//所有角色

    public int editRole(Integer uid,Integer roleid);

    public PageInfo<OaRole> roles(Integer pageNum, Integer pageSize);

    OaRole toRole(Integer id);//跳转到编辑

    int update(OaRole oaRole);

    int addRole(OaRole oaRole);//添加角色

    int editMenuByRole(Integer[] nodes, Integer rid) throws Exception;//修改用户拥有的菜单
}
