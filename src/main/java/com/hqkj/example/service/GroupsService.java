package com.hqkj.example.service;

import com.hqkj.example.entity.Groups;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */
public interface GroupsService {

    /**
     * 分页获取 分组信息
     * @param params ： 参数集合
     * @return
     */
    public List<Groups>  queryGroupsList(Map<String,Object> params);

    /**
     * 新增||修改||删除
     * @param groups ：分组对象：用于新增、修改
     * @param groupsId ：分组ID字符串，用于批量、单个删除分组
     * @param excuType ：操作类型：1 -新增||修改  2 -删除
     * @return
     */
    public String addOrEditOrDelGroups(Groups groups,String groupsId,Integer excuType);


    /**
     * 根据ID获取单个分组对象
     * @param groups ：分组对象
     * @return
     */
    public Groups queryGroup(Groups groups);
}
