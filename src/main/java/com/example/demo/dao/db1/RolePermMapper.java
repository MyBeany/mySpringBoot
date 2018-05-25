package com.example.demo.dao.db1;

import com.example.demo.core.universal.Mapper;
import com.example.demo.model.RolePerm;

import java.util.List;

public interface RolePermMapper extends Mapper<RolePerm> {

    List<String> getPermsByUserId(String userId);
}