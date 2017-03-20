package com.hqkj.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.hqkj.example.dao.RoleMapper;
import com.hqkj.example.entity.Role;
import com.hqkj.example.entity.RoleFunc;
import com.hqkj.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by HuangXuFeng on 2017/3/9.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int roleAdd(Role role) {
        return roleMapper.insertSelective(role);
    }

    @Override
    public int roleDelAll(Integer[] roleIdList) {
        return roleMapper.deleteAll(roleIdList);
    }

    @Override
    public int roleDel(Integer roleId) {
        Role role=new Role();
        role.setRoleId(roleId);
        return roleMapper.delete(role);
    }

    @Override
    public List<Role> queryPage(Role role, Integer pageNum, Integer pageSize) {
        if (pageNum!=null&&pageSize!=null)
        PageHelper.startPage(pageNum,pageSize);
        return roleMapper.query(role);
    }

    @Override
    public int roleUpdate(Role role,List<RoleFunc>  roleFuncList) {
        int num=0;
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public Role queryKey(Role role) {
        return roleMapper.selectOne(role);
    }
}
