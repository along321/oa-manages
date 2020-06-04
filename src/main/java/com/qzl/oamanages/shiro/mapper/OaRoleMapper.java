package com.qzl.oamanages.shiro.mapper;

import com.qzl.oamanages.shiro.entity.OaMenu;
import com.qzl.oamanages.shiro.entity.OaRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.management.relation.Role;
import java.util.List;
import java.util.Set;

@Mapper
public interface OaRoleMapper {

    //根据用户id查询当前用户角色
    public Set<String> findRoleByid(@Param("uid") Integer uid);

    //根据用户id查询当前用户角色
    public OaRole findByid(@Param("uid") Integer uid);

    public List<OaRole> allRole();//所有角色

    public int editRole(@Param("uid")Integer uid,@Param("roleid") Integer roleid);

    OaRole toRole(@Param("id") Integer id);

    int update(OaRole oaRole);

    int addRole(OaRole oaRole);

    int delMenuByRole(@Param("rid") Integer rid)throws Exception;

    int addMenuByRole(@Param("mid") Integer node,@Param("rid") Integer rid)throws Exception;

    int findByRid(@Param("rid") Integer rid);
}
