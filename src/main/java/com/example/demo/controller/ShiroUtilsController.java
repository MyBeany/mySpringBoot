package com.example.demo.controller;

import com.example.demo.core.ret.ServiceException;
import com.example.demo.model.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("shiroUtils")
public class ShiroUtilsController {

    @GetMapping("/noLogin")
    public void noLogin() {
        throw new ServiceException("未登录");
    }

    @GetMapping("/noAuthorize")
    public void noAuthorize() {
        throw new ServiceException("没有权限");
    }


    @PostMapping("/getNowUser")
    public UserInfo getNowUser() {
        UserInfo u = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        return u;
    }

}
