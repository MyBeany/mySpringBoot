package com.example.demo.service.impl;

import com.example.demo.dao.db1.UserRoleMapper;
import com.example.demo.model.UserRole;
import com.example.demo.service.UserRoleService;
import com.example.demo.core.universal.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: UserRoleService接口实现类
* @author 张瑶
* @date 2018/05/25 23:27
*/
@Service
public class UserRoleServiceImpl extends AbstractService<UserRole> implements UserRoleService {

    @Resource
    private UserRoleMapper userRoleMapper;

    public List<String> getRolesByUserId(String userId){
        return userRoleMapper.getRolesByUserId(userId);
    }

}