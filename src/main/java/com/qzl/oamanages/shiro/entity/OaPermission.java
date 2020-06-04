package com.qzl.oamanages.shiro.entity;

public class OaPermission {
    private Integer id;//权限ID

    private String modelName;//权限名称

    private Integer parentId;//父级权限ID

    private String permission;//拥有权限

    @Override
    public String toString() {
        return "OaPermission{" +
                "id=" + id +
                ", modelName='" + modelName + '\'' +
                ", parentId=" + parentId +
                ", permission='" + permission + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
