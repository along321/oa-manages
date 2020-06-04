package com.qzl.oamanages;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qzl.oamanages.oa.entity.Leave;
import com.qzl.oamanages.oa.service.LeaveService;
import com.qzl.oamanages.shiro.entity.OaDept;
import com.qzl.oamanages.shiro.entity.OaPost;
import com.qzl.oamanages.shiro.entity.OaUser;
import com.qzl.oamanages.shiro.mapper.OaUserMapper;
import com.qzl.oamanages.shiro.service.OaDeptService;
import com.qzl.oamanages.shiro.service.OaMenuService;
import com.qzl.oamanages.shiro.service.OaPostService;
import com.qzl.oamanages.shiro.service.OaUserService;
import com.qzl.oamanages.utils.UserList;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OaManagesApplicationTests {

    @Autowired
    OaUserService oaUserService;

    @Autowired
    OaPostService oaPostService;

    @Autowired
    LeaveService leaveService;

//    OaDeptService oaDeptService;
//    OaMenuService oaMenuService;

    @Autowired
    OaUserMapper oaUserMapper;
    @Test
    public void contextLoads() {
        OaUser oaUser = new OaUser();
        oaUser.setRealName("1");
        oaUser.setUserName("11");
        oaUser.setEmail("sadasddsa");
        oaUser.setBirthday(new Date());
        oaUser.setSex(1);
        oaUser.setPhone("55555");
        oaUser.setAddress("1111");
        OaDept dept = new OaDept();
        dept.setId(1);
        oaUser.setOaDept(dept);
        OaPost post = new OaPost();
        post.setId(1);
        oaUser.setOaPost(post);
        int num = oaUserService.addDimiUser(oaUser);

        System.out.println(num);
//        MD5加密
//        String hashAlgorithName = "MD5";
//        String password = "1";
//        int hashIterations = 1024;//加密次数
//        ByteSource credentialsSalt = ByteSource.Util.bytes("111".toLowerCase()+"shiro");
//        Object obj = new SimpleHash(hashAlgorithName, password, credentialsSalt, hashIterations).toHex();
//        System.out.println("-------------------------------------------");
//        System.out.println(obj);


//        OaPost oaPost = oaPostService.oaPostById(2);
//        System.out.println("oaPost = " + oaPost.toString());
//        oaDeptService.nodes().forEach((x)-> System.out.println("x = " + x));
    }

}

