package com.hqkj.example.dao;


import com.hqkj.example.entity.Role;
import com.hqkj.example.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends MyMapper<Role>{
    /**
     * 动态条件查询
     * @param role
     * @return
     */
    List<Role> query(Role role);

    /**
     * 批量删除
     */
    int deleteAll(@Param(value = "roleIdList") Integer[] roleIdList);
}