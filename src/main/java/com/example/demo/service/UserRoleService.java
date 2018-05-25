package com.example.demo.service;

import com.example.demo.model.UserRole;
import com.example.demo.core.universal.Service;

import java.util.List;

/**
* @Description: UserRoleService接口
* @author 张瑶
* @date 2018/05/25 23:27
*/
public interface UserRoleService extends Service<UserRole> {

    List<String> getRolesByUserId(String userId);

}