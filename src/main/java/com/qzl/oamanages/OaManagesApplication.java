package com.qzl.oamanages;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan("com.qzl.oamanages.shiro.mapper")

@SpringBootApplication
public class OaManagesApplication {

    public static void main(String[] args) {
        SpringApplication.run(OaManagesApplication.class, args);
    }

}

