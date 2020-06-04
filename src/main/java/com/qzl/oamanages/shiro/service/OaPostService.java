package com.qzl.oamanages.shiro.service;

import com.github.pagehelper.PageInfo;
import com.qzl.oamanages.shiro.entity.OaPost;

import java.util.List;

public interface OaPostService {

    public List<OaPost> allPost();//查询所有岗位

    public PageInfo<OaPost> pagePost(Integer pageNum,Integer pageSize);//分页查询岗位

    int addPost(OaPost oaPost);//添加岗位

    int delPost(Integer id);

    int editPost(OaPost oaPost);

    OaPost oaPostById(Integer id);
}
