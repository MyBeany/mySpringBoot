package com.example.demo.service;

import com.example.demo.core.universal.Service;
import com.example.demo.model.UserInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 张瑶
 * @Description:
 * @time 2018/4/18 11:56
 */
public interface UserInfoService extends Service<UserInfo>{

    List<UserInfo> selectAlla(int pageNum, int pageSize);

    UserInfo findUserByName(String uname);

}
