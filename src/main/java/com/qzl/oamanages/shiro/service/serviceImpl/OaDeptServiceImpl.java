package com.qzl.oamanages.shiro.service.serviceImpl;

import com.qzl.oamanages.shiro.entity.OaDept;
import com.qzl.oamanages.shiro.entity.OaUser_Role;
import com.qzl.oamanages.shiro.mapper.OaDeptMapper;
import com.qzl.oamanages.shiro.service.OaDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OaDeptServiceImpl implements OaDeptService {

    @Autowired
    OaDeptMapper oaDeptMapper;

    @Override
    public List<OaDept> nodes() {
        List<OaDept> depts = oaDeptMapper.allDept();
//        //带分组的部门
        List<OaDept> nodes = new ArrayList<>();

        depts.forEach((d)->{
            Integer did = d.getParentId();
            if(did==0){
                nodes.add(d);
            }else{
                // 子节点
                depts.forEach((c) -> {
                    // 当前菜单ID
                    Integer id = c.getId();
                    if(id == did){
                        c.getChildren().add(d);
                    }
                });
            }
        });
        return nodes;
    }

    @Override
    public List<OaDept> allDepts() {
        return oaDeptMapper.allDept();
    }

    @Override
    public OaUser_Role oaURD(Integer id) {
        return null;
    }

    @Override
    public int addDept(OaDept dept) {
        return oaDeptMapper.addDept(dept);
    }

    @Override
    public OaDept deptByid(Integer id) {
        return oaDeptMapper.deptByid(id);
    }

    @Override
    public int delDept(Integer id) {
        return oaDeptMapper.delDept(id);
    }
}
