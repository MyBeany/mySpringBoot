package com.example.demo.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "system_log")
public class SystemLog {
    @Id
    private String id;

    /**
     * 日志信息描述
     */
    private String description;

    /**
     * 方法名称
     */
    private String method;

    /**
     * 日志类型 0是正常，1是异常
     */
    @Column(name = "log_type")
    private String logType;

    /**
     * 请求的ip
     */
    @Column(name = "request_ip")
    private String requestIp;

    /**
     * 异常错误码
     */
    @Column(name = "exception_code")
    private String exceptionCode;

    /**
     * 异常详情
     */
    @Column(name = "exception_detail")
    private String exceptionDetail;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 请求的用户id
     */
    @Column(name = "user_id")
    private String userId;

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
     * 获取日志信息描述
     *
     * @return description - 日志信息描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置日志信息描述
     *
     * @param description 日志信息描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取方法名称
     *
     * @return method - 方法名称
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设置方法名称
     *
     * @param method 方法名称
     */
    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    /**
     * 获取日志类型 0是正常，1是异常
     *
     * @return log_type - 日志类型 0是正常，1是异常
     */
    public String getLogType() {
        return logType;
    }

    /**
     * 设置日志类型 0是正常，1是异常
     *
     * @param logType 日志类型 0是正常，1是异常
     */
    public void setLogType(String logType) {
        this.logType = logType == null ? null : logType.trim();
    }

    /**
     * 获取请求的ip
     *
     * @return request_ip - 请求的ip
     */
    public String getRequestIp() {
        return requestIp;
    }

    /**
     * 设置请求的ip
     *
     * @param requestIp 请求的ip
     */
    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp == null ? null : requestIp.trim();
    }

    /**
     * 获取异常错误码
     *
     * @return exception_code - 异常错误码
     */
    public String getExceptionCode() {
        return exceptionCode;
    }

    /**
     * 设置异常错误码
     *
     * @param exceptionCode 异常错误码
     */
    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode == null ? null : exceptionCode.trim();
    }

    /**
     * 获取异常详情
     *
     * @return exception_detail - 异常详情
     */
    public String getExceptionDetail() {
        return exceptionDetail;
    }

    /**
     * 设置异常详情
     *
     * @param exceptionDetail 异常详情
     */
    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail == null ? null : exceptionDetail.trim();
    }

    /**
     * 获取请求参数
     *
     * @return params - 请求参数
     */
    public String getParams() {
        return params;
    }

    /**
     * 设置请求参数
     *
     * @param params 请求参数
     */
    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    /**
     * 获取请求的用户id
     *
     * @return user_id - 请求的用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置请求的用户id
     *
     * @param userId 请求的用户id
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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