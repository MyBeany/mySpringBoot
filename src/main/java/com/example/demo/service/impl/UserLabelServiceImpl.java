package com.example.demo.service.impl;

import com.example.demo.dao.db2.UserLabelMapper;
import com.example.demo.model.UserLabel;
import com.example.demo.service.UserLabelService;
import com.example.demo.core.universal.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @Description: UserLabelService接口实现类
* @author 张瑶
* @date 2018/04/29 19:09
*/
@Service
public class UserLabelServiceImpl extends AbstractService<UserLabel> implements UserLabelService {

    @Resource
    private UserLabelMapper userLabelMapper;

}