package com.example.demo.model;

import javax.persistence.*;

@Table(name = "role_perm")
public class RolePerm {
    @Id
    private String id;

    /**
     * 权限id
     */
    @Column(name = "perm_id")
    private String permId;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private String roleId;

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
     * 获取权限id
     *
     * @return perm_id - 权限id
     */
    public String getPermId() {
        return permId;
    }

    /**
     * 设置权限id
     *
     * @param permId 权限id
     */
    public void setPermId(String permId) {
        this.permId = permId == null ? null : permId.trim();
    }

    /**
     * 获取角色id
     *
     * @return role_id - 角色id
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     *
     * @param roleId 角色id
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }
}