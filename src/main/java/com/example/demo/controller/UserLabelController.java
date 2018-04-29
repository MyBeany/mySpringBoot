package com.example.demo.controller;

import com.example.demo.core.ret.RetResult;
import com.example.demo.core.ret.RetResponse;
import com.example.demo.core.utils.ApplicationUtils;
import com.example.demo.model.UserLabel;
import com.example.demo.service.UserLabelService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: UserLabelController类
* @author 张瑶
* @date 2018/04/29 19:09
*/
@RestController
@RequestMapping("/userLabel")
public class UserLabelController {

    @Resource
    private UserLabelService userLabelService;

    @PostMapping("/insert")
    public RetResult<Integer> insert(UserLabel userLabel) throws Exception{
		userLabel.setId(ApplicationUtils.getUUID());
    	Integer state = userLabelService.insert(userLabel);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/deleteById")
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = userLabelService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/update")
    public RetResult<Integer> update(UserLabel userLabel) throws Exception {
        Integer state = userLabelService.update(userLabel);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/selectById")
    public RetResult<UserLabel> selectById(@RequestParam String id) throws Exception {
        UserLabel userLabel = userLabelService.selectById(id);
        return RetResponse.makeOKRsp(userLabel);
    }

    /**
	* @Description: 分页查询
	* @param page 页码
	* @param size 每页条数
	* @Reutrn RetResult<PageInfo<UserLabel>>
	*/
    @PostMapping("/list")
    public RetResult<PageInfo<UserLabel>> list(@RequestParam(defaultValue = "0") Integer page,
					@RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<UserLabel> list = userLabelService.selectAll();
        PageInfo<UserLabel> pageInfo = new PageInfo<UserLabel>(list);
        return RetResponse.makeOKRsp(pageInfo);
    }
}