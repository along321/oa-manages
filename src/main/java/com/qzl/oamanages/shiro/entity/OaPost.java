package com.qzl.oamanages.shiro.entity;

import java.io.Serializable;

public class OaPost  implements Serializable {

    private Integer id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
