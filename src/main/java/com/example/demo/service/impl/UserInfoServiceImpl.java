package com.example.demo.service.impl;

import com.example.demo.core.ret.ServiceException;
import com.example.demo.core.universal.AbstractService;
import com.example.demo.dao.db1.UserInfoMapper;
import com.example.demo.model.UserInfo;
import com.example.demo.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 张瑶
 * @Description:
 * @time 2018/4/18 11:56
 */
@Service
public class UserInfoServiceImpl extends AbstractService<UserInfo> implements UserInfoService{

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo selectById(String id){
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
        if(userInfo == null){
            throw new ServiceException("暂无该用户");
        }
        return userInfo;
    }

    @Override
    public List<UserInfo> selectAlla(int pageNum, int pageSize) {
        return userInfoMapper.selectAll(pageNum,pageSize);
    }

    /**
     * 模拟查询返回用户信息
     * @param uname
     * @return
     */
    @Override
    public UserInfo findUserByName(String uname){
        UserInfo u = new UserInfo();
        u.setUserName(uname);
        UserInfo userInfo = userInfoMapper.selectOne(u);
        return userInfo;
    }
}
