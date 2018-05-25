package com.example.demo.model;

import javax.persistence.*;

@Table(name = "sys_permission_init")
public class SysPermissionInit {
    @Id
    private String id;

    private String url;

    @Column(name = "permission_init")
    private String permissionInit;

    private Integer sort;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * @return permission_init
     */
    public String getPermissionInit() {
        return permissionInit;
    }

    /**
     * @param permissionInit
     */
    public void setPermissionInit(String permissionInit) {
        this.permissionInit = permissionInit == null ? null : permissionInit.trim();
    }

    /**
     * @return sort
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}