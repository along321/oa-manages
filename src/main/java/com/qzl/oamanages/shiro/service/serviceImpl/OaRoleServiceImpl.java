package com.qzl.oamanages.shiro.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qzl.oamanages.shiro.entity.OaMenu;
import com.qzl.oamanages.shiro.entity.OaRole;
import com.qzl.oamanages.shiro.mapper.OaRoleMapper;
import com.qzl.oamanages.shiro.service.OaRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class OaRoleServiceImpl implements OaRoleService {

    @Autowired
    OaRoleMapper oaRoleMapper;


    @Override
    public Set<String> findRoleByid(Integer uid) {
        return oaRoleMapper.findRoleByid(uid);
    }

    @Override
    public OaRole findByid(Integer uid) {
        return oaRoleMapper.findByid(uid);
    }

    @Override
    public List<OaRole> allRole() {
        return  oaRoleMapper.allRole() ;
    }

    @Override
    public int editRole(Integer uid, Integer roleid) {
        return oaRoleMapper.editRole(uid,roleid);
    }

    //分页查询所有角色
    @Override
    public PageInfo<OaRole> roles(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return  new PageInfo<>(oaRoleMapper.allRole()) ;
    }

    @Override
    public OaRole toRole(Integer id) {
        return oaRoleMapper.toRole(id);
    }

    @Override
    public int update(OaRole oaRole) {
        return oaRoleMapper.update(oaRole);
    }

    @Override
    public int addRole(OaRole oaRole) {
        return oaRoleMapper.addRole(oaRole);
    }

    @Override
    @Transactional
    public int editMenuByRole(Integer[] nodes, Integer rid)throws Exception {
        int num = oaRoleMapper.findByRid(rid);
        if(num>0){
            num = oaRoleMapper.delMenuByRole(rid);
        }
            for (int i = 0; i < nodes.length; i++) {
                num = oaRoleMapper.addMenuByRole(nodes[i],rid);
            }
        return num;
    }


}
