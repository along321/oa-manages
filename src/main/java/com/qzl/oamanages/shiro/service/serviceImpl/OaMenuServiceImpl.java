package com.qzl.oamanages.shiro.service.serviceImpl;

import com.qzl.oamanages.shiro.entity.OaMenu;
import com.qzl.oamanages.shiro.mapper.OaMenuMapper;
import com.qzl.oamanages.shiro.service.OaMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OaMenuServiceImpl implements OaMenuService {

    @Autowired
    OaMenuMapper oaMenuMapper;

    @Override
    public List<OaMenu> allMenu(Integer rid) {
        return fenzu(findMenuByMid(rid));
    }

    @Override
    public List<OaMenu> findMenuByMid(Integer rid) {
        return oaMenuMapper.findMenuByMid(rid);
    }

    @Override
    public List<OaMenu> findMenuByParentId(Integer parentid) {
        return oaMenuMapper.findMenuByParentId(parentid);
    }

    @Override
    public List<OaMenu> allMenu() {
        return fenzu(oaMenuMapper.allMenu());
    }

    @Override
    public int addMenu(OaMenu menu) {
        return oaMenuMapper.addMenu(menu);
    }

    @Override
    public OaMenu menuById(Integer id) {
        return oaMenuMapper.menuById(id);
    }

    @Override
    public int editMenu(OaMenu menu) {
        return oaMenuMapper.editMenu(menu);
    }

    @Override
    public int delMenu(int id) {
        return oaMenuMapper.delMenu(id);
    }

    @Override
    public List<OaMenu> roleMenus() {
        return oaMenuMapper.allMenu();
    }

    @Override
    public List<OaMenu> menusByRole(Integer rid) {
        return oaMenuMapper.menusByRole(rid);
    }

    // 分组后的数据
    public List<OaMenu> fenzu(List<OaMenu> menus){
        List<OaMenu> nodes = new ArrayList<>();

//        Map<Integer,OaMenu> menuMap =  new HashMap<Integer,OaMenu>();
//        menus.forEach((m) -> menuMap.put(m.getId(),m));

        menus.forEach((p)->{
            // 根节点
            Integer pid = p.getParentId();
            if(pid == 0){
                nodes.add(p);
            }else {
                // 子节点
                menus.forEach((c) -> {
                    // 当前菜单ID
                    Integer id = c.getId();
                    if(id == pid){
                        c.getChildren().add(p);
                    }
                });
            }
        });
        return nodes;
    }
}
