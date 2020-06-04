package com.qzl.oamanages.shiro.mapper;

import com.qzl.oamanages.shiro.entity.OaDept;
import com.qzl.oamanages.shiro.entity.OaPost;
import com.qzl.oamanages.shiro.entity.OaUser_Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OaPostMapper {

    public List<OaPost> allPost();//查询所有岗位

    int addPost(OaPost oaPost);//添加岗位

    int delPost(@Param("id")Integer id);

    int editPost(OaPost oaPost);

    OaPost oaPostById(@Param("id") Integer id);
}
