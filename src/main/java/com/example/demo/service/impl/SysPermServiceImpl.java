package com.example.demo.service.impl;

import com.example.demo.dao.db1.SysPermMapper;
import com.example.demo.model.SysPerm;
import com.example.demo.service.SysPermService;
import com.example.demo.core.universal.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @Description: SysPermService接口实现类
* @author 张瑶
* @date 2018/05/26 00:30
*/
@Service
public class SysPermServiceImpl extends AbstractService<SysPerm> implements SysPermService {

    @Resource
    private SysPermMapper sysPermMapper;

}