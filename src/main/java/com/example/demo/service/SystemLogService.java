package com.example.demo.service;

import com.example.demo.model.SystemLog;
import com.example.demo.core.universal.Service;

import java.util.List;

/**
* @Description: SystemLogService接口
* @author 张瑶
* @date 2018/04/25 15:52
*/
public interface SystemLogService extends Service<SystemLog> {

    Integer insertByBatch(List<SystemLog> list);

}