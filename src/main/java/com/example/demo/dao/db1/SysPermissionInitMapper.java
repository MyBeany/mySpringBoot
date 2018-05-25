package com.example.demo.dao.db1;

import com.example.demo.core.universal.Mapper;
import com.example.demo.model.SysPermissionInit;

import java.util.List;

public interface SysPermissionInitMapper extends Mapper<SysPermissionInit> {

    List<SysPermissionInit> selectAllOrderBySort();
}