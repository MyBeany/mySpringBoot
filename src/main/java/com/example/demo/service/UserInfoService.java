package com.example.demo.service;

import com.example.demo.model.UserInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 张瑶
 * @Description:
 * @time 2018/4/18 11:56
 */
public interface UserInfoService {

    UserInfo selectById(Integer id);

    PageInfo<UserInfo> selectAll(Integer page,Integer size);

}
