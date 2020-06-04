package com.qzl.oamanages.shiro.mapper;

import com.qzl.oamanages.shiro.entity.OaUser;
import com.qzl.oamanages.utils.UserList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OaUserMapper {

    //查询当前用户
    public OaUser findUserByName(@Param("username") String userName, @Param("password") String passwrod);
    //根据用户名查询当前用户
    public OaUser findByName(@Param("username") String userName);

    public List<OaUser> allUser();//查询所有用户

    public OaUser userByid(@Param("id") Integer id);//根据ID查询用户信息

    public int editUser(OaUser user);//修改用户信息

    public int addUser(OaUser oaUser);//添加用户

    public int addUserRole(@Param("uid") Integer uid,@Param("rid") Integer rid);

    public int delUser(@Param("id") Integer id);//删除用户

    public int delUserRole(@Param("id") Integer id);//删除用户跟角色的对应关系

    public List<OaUser> userBySreach(UserList userList);//查询

    Integer findByDeptBypost(@Param("did") Integer did);//根据部门ID查找负责人

    int addDimiUser(OaUser oaUser);//添加离职人员信息
}
