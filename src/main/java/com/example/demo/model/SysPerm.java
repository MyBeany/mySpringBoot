package com.example.demo.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_perm")
public class SysPerm {
    @Id
    private String id;

    /**
     * 权限名称
     */
    @Column(name = "perm_name")
    private String permName;

    /**
     * 权限描述
     */
    @Column(name = "perm_desc")
    private String permDesc;

    /**
     * 权限值
     */
    @Column(name = "perm_value")
    private String permValue;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 是否禁用
     */
    @Column(name = "is_disable")
    private Integer isDisable;

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
     * 获取权限名称
     *
     * @return perm_name - 权限名称
     */
    public String getPermName() {
        return permName;
    }

    /**
     * 设置权限名称
     *
     * @param permName 权限名称
     */
    public void setPermName(String permName) {
        this.permName = permName == null ? null : permName.trim();
    }

    /**
     * 获取权限描述
     *
     * @return perm_desc - 权限描述
     */
    public String getPermDesc() {
        return permDesc;
    }

    /**
     * 设置权限描述
     *
     * @param permDesc 权限描述
     */
    public void setPermDesc(String permDesc) {
        this.permDesc = permDesc == null ? null : permDesc.trim();
    }

    /**
     * 获取权限值
     *
     * @return perm_value - 权限值
     */
    public String getPermValue() {
        return permValue;
    }

    /**
     * 设置权限值
     *
     * @param permValue 权限值
     */
    public void setPermValue(String permValue) {
        this.permValue = permValue == null ? null : permValue.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取是否禁用
     *
     * @return is_disable - 是否禁用
     */
    public Integer getIsDisable() {
        return isDisable;
    }

    /**
     * 设置是否禁用
     *
     * @param isDisable 是否禁用
     */
    public void setIsDisable(Integer isDisable) {
        this.isDisable = isDisable;
    }
}