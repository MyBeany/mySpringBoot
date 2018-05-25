package com.example.demo.service;

import com.example.demo.model.SysPermissionInit;
import com.example.demo.core.universal.Service;

import java.util.List;

/**
* @Description: SysPermissionInitService接口
* @author 张瑶
* @date 2018/05/18 14:37
*/
public interface SysPermissionInitService extends Service<SysPermissionInit> {

    List<SysPermissionInit> selectAllOrderBySort();

}