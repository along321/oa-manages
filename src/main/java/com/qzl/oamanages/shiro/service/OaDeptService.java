package com.qzl.oamanages.shiro.service;

import com.qzl.oamanages.shiro.entity.OaDept;
import com.qzl.oamanages.shiro.entity.OaUser_Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OaDeptService {

    public List<OaDept> nodes();//查询所有部门-分好组

    public List<OaDept> allDepts();//查询所有部门

    public OaUser_Role oaURD(@Param("id")Integer id);//根据用户ID查询角色跟部门

    int addDept(OaDept dept);//添加部门

    OaDept deptByid(Integer id);//根据ID查找部门

    int delDept(Integer id);//删除部门
}
