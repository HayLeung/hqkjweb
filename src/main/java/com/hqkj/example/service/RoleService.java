package com.hqkj.example.service;

import com.hqkj.example.entity.Role;
import com.hqkj.example.entity.RoleFunc;

import java.util.List;
import java.util.Map;
/**
 * Created by HuangXuFeng on 2017/3/9.
 */
public interface RoleService {
    /***
     * 新增角色
     * @param role
     * @return
     */
    public int  roleAdd(Role role);


    /**
     * 批量删除
     */
    public int roleDelAll(Integer[] roleIdList);


    /**
     * 根据ID删除
     */
    public int roleDel(Integer roleId);

    /**
     * 分页查询
     * @param role
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<Role> queryPage(Role role,Integer pageNum,Integer pageSize);

    /**
     * 根据id修改数据
     */

    public int roleUpdate(Role role,List<RoleFunc>  roleFuncList);

    /**
     * 根据id查询角色
     * @param role
     * @return
     */
    public Role queryKey(Role role);

}
