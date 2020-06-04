package com.qzl.oamanages.shiro.service;

import com.github.pagehelper.PageInfo;
import com.qzl.oamanages.shiro.entity.OaUser;
import com.qzl.oamanages.utils.UserList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OaUserService {
    public OaUser findUserByName(String userName,String passwrod);
    //根据用户名查询当前用户
    public OaUser findByName(String userName);

    public PageInfo<OaUser> allUser(Integer pageNum,Integer pageSize);//查询所有用户

    public OaUser userByid(Integer id);//根据ID查询用户信息

    public int editUser(OaUser user);//修改用户信息

    public int addUser(OaUser oaUser);//添加用户

    public int delUser(Integer id);//删除用户

    public PageInfo<OaUser> userBySreach(Integer pageNum,Integer pageSize,UserList userList);//查询

    Integer findByDeptBypost(Integer did);//根据部门ID查找部门负责人

    int addDimiUser(OaUser oaUser);
}
