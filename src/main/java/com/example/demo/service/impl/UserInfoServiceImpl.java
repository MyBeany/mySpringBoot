package com.example.demo.service.impl;

import com.example.demo.dao.UserInfoMapper;
import com.example.demo.model.UserInfo;
import com.example.demo.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 张瑶
 * @Description:
 * @time 2018/4/18 11:56
 */
@Service
public class UserInfoServiceImpl implements UserInfoService{

    @Resource
    private UserInfoMapper userInfoMapper;

    public UserInfo selectById(Integer id){
        return userInfoMapper.selectById(id);
    }
}
