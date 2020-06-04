package com.qzl.oamanages.shiro.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qzl.oamanages.shiro.entity.OaPost;
import com.qzl.oamanages.shiro.mapper.OaPostMapper;
import com.qzl.oamanages.shiro.service.OaPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OaPostServiceImpl implements OaPostService {

    @Autowired
    OaPostMapper oaPostMapper;

    @Override
    public List<OaPost> allPost() {
        return oaPostMapper.allPost();
    }

    @Override
    public PageInfo<OaPost> pagePost(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(oaPostMapper.allPost());
    }

    @Override
    public int addPost(OaPost oaPost) {
        return oaPostMapper.addPost(oaPost);
    }

    @Override
    public int delPost(Integer id) {
        return oaPostMapper.delPost(id);
    }

    @Override
    public int editPost(OaPost oaPost) {
        return oaPostMapper.editPost(oaPost);
    }

    @Override
    public OaPost oaPostById(Integer id) {
        return oaPostMapper.oaPostById(id);
    }


}
