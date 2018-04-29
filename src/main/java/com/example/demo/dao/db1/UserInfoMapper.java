package com.example.demo.dao.db1;

import com.example.demo.core.universal.Mapper;
import com.example.demo.model.UserInfo;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 张瑶
 * @Description:
 * @time 2018/4/18 11:54
 */
public interface UserInfoMapper extends Mapper<UserInfo>{

    List<UserInfo> selectAll(@Param("pageNumKey") int pageNum,
                                  @Param("pageSizeKey") int pageSize);
}
