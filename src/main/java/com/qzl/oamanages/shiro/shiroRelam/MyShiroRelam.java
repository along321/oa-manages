package com.qzl.oamanages.shiro.shiroRelam;

import com.qzl.oamanages.shiro.entity.OaUser;
import com.qzl.oamanages.shiro.service.OaRoleService;
import com.qzl.oamanages.shiro.service.OaUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class MyShiroRelam extends AuthorizingRealm {

    @Autowired
    private OaUserService oaUserService;

    @Autowired
    private OaRoleService oaRoleService;


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("验证用户登录信息");
        //获取用户名密码 第一种方式
//    	String username = (String) token.getPrincipal();
//    	String password = new String((char[]) token.getCredentials());
        //获取用户名 密码 第二种方式
        // 1.可以把AuthenticationToken强转为UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
//        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());
        String userName = (String)token.getPrincipal();
        System.out.println("用户名"+userName);
        System.out.println(token.getCredentials());
        //从数据库查询出User信息及用户关联的角色，权限信息，以备权限分配时使用
        OaUser user = oaUserService.findByName(userName);

        if(user == null) throw new UnknownAccountException("用户名错误");
        if(!password.equals(user.getPassword())) throw new IncorrectCredentialsException("密码错误");
        System.out.println("username"+user.getUserName()+"密码"+user.getPassword());
        // 4.根据用户情况，构建AuthenticationInfo并返回
        Object principal = user;// 认证的实体信息，可以是username，也可是user（用户名）
        Object credentials = user.getPassword();// 凭证（密码）
        String realmName = super.getName();// 当前realm对象的name ， 调用父类的getName即可
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUserName());// 盐值
//        principal,credentials,credentialsSalt,realmName
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal,credentials,realmName);
        return authenticationInfo;
    }
    /**
     * 授权用户权限
     * 授权的方法是在碰到<shiro:hasPermission name=''></shiro:hasPermission>标签的时候调用的
     * 它会去检测shiro框架中的权限(这里的permissions)是否包含有该标签的name值,如果有,里面的内容显示
     * 如果没有,里面的内容不予显示(这就完成了对于权限的认证.)
     *
     * shiro的权限授权是通过继承AuthorizingRealm抽象类，重载doGetAuthorizationInfo();
     * 当访问到页面的时候，链接配置了相应的权限或者shiro标签才会执行此方法否则不会执行
     * 所以如果只是简单的身份认证没有权限的控制的话，那么这个方法可以不进行实现，直接返回null即可。
     *
     * 在这个方法中主要是使用类：SimpleAuthorizationInfo 进行角色的添加和权限的添加。
     * authorizationInfo.addRole(role.getRole()); authorizationInfo.addStringPermission(p.getPermission());
     *
     * 当然也可以添加set集合：roles是从数据库查询的当前用户的角色，stringPermissions是从数据库查询的当前用户对应的权限
     * authorizationInfo.setRoles(roles); authorizationInfo.setStringPermissions(stringPermissions);
     *
     * 就是说如果在shiro配置文件中添加了filterChainDefinitionMap.put("/add", "perms[权限添加]");
     * 就说明访问/add这个链接必须要有“权限添加”这个权限才可以访问
     *
     * 如果在shiro配置文件中添加了filterChainDefinitionMap.put("/add", "roles[100002]，perms[权限添加]");
     * 就说明访问/add这个链接必须要有 "权限添加" 这个权限和具有 "100002" 这个角色才可以访问
     * @param
     * @return
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
        System.out.println("用户权限配置");
        String username =((OaUser) principals.getPrimaryPrincipal()).getUserName();
        OaUser user = oaUserService.findByName(username);
        // 权限字符串
        Set<String> permissions = new HashSet<String>();
        // 角色字符串
        Set<String> roles = oaRoleService.findRoleByid(user.getId());

        SimpleAuthorizationInfo info  = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);
        info.addRoles(roles);
        return info;
    }



}
