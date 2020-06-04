package com.qzl.oamanages.shiro.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qzl.oamanages.shiro.entity.OaRole;
import com.qzl.oamanages.shiro.entity.OaUser;
import com.qzl.oamanages.shiro.mapper.OaUserMapper;
import com.qzl.oamanages.shiro.service.OaRoleService;
import com.qzl.oamanages.shiro.service.OaUserService;
import com.qzl.oamanages.utils.UserList;
import jdk.nashorn.internal.ir.ReturnNode;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OaUserServiceImpl implements OaUserService {

    @Autowired
    OaUserMapper oaUserMapper;

    @Autowired
    OaRoleService oaRoleService;

    @Override
    public OaUser findUserByName(String userName, String passwrod) {
        return oaUserMapper.findUserByName(userName,passwrod);
    }

    @Override
    public OaUser findByName(String userName) {
        return oaUserMapper.findByName(userName);
    }

    @Override
    public PageInfo<OaUser> allUser(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return  new PageInfo<>(oaUserMapper.allUser()) ;
    }

    @Override
    public OaUser userByid(Integer id) {
        return oaUserMapper.userByid(id);
    }

    @Override
    public int editUser(OaUser user) {
        int num = 0;
        num = oaUserMapper.editUser(user);
        if(num>0){
            num = oaRoleService.editRole(user.getId(),user.getRoleid());
        }
        return num;
    }


    @Override
    public int addUser(OaUser oaUser) {
        oaUser.setPassword(Md5(oaUser.getUserName(),oaUser.getPassword()).toString());
        int num = oaUserMapper.addUser(oaUser);
        if(num>0){
            num = oaUserMapper.addUserRole(oaUser.getId(),oaUser.getRoleid());
        }
        return num;
    }

    @Override
    @Transactional
    public int delUser(Integer id) {
        int num = oaUserMapper.delUser(id);
        if(num>0){
            num = oaUserMapper.delUserRole(id);
        }
        return num;
    }

    @Override
    public PageInfo<OaUser> userBySreach(Integer pageNum,Integer pageSize,UserList userList) {
        PageHelper.startPage(pageNum,pageSize);
        if(userList.getPrincipal()!=null && !userList.getPrincipal().equals("")){
            userList.setIs(1);
        }else{
            userList.setIs(0);
        }
        List<OaUser> userList1 = oaUserMapper.userBySreach(userList);
        return new PageInfo<>(userList1);
    }

    @Override
    public Integer findByDeptBypost(Integer did) {
        return oaUserMapper.findByDeptBypost(did);
    }

    @Override
    public int addDimiUser(OaUser oaUser) {
        return oaUserMapper.addDimiUser(oaUser);
    }

    public Object Md5(String userName,String password){
        String hashAlgorithName = "MD5";
        int hashIterations = 1024;//加密次数
        ByteSource credentialsSalt = ByteSource.Util.bytes(userName.toLowerCase()+"shiro");
        Object obj = new SimpleHash(hashAlgorithName, password, credentialsSalt, hashIterations).toHex();
        return obj;
    }
}
