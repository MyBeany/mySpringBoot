package com.example.demo.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_label")
public class UserLabel {
    @Id
    private String id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 标签id
     */
    @Column(name = "label_id")
    private String labelId;

    @Column(name = "create_time")
    private Date createTime;

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
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取标签id
     *
     * @return label_id - 标签id
     */
    public String getLabelId() {
        return labelId;
    }

    /**
     * 设置标签id
     *
     * @param labelId 标签id
     */
    public void setLabelId(String labelId) {
        this.labelId = labelId == null ? null : labelId.trim();
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
}