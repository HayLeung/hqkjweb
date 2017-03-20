package com.hqkj.example.dao;


import com.hqkj.example.entity.Groups;
import com.hqkj.example.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GroupsMapper extends MyMapper<Groups>{

    /**
     * 分页获取分组信息
     * @param params ：参数集合
     * @return
     */
    List<Groups> queryGroupsList(Map<String,Object> params);

    /**
     * 判断分组名是否已经存在
     * @param params ：参数集合
     * @return
     */
    Integer queryNameExist(Map<String,Object> params);


    /**
     * '根据要删除的分组ID获取下面的子分组
     * @param groupsId ：分组ID字符串
     * @return
     */
    List<Integer> queryChirdenGroups(@Param("groupsId") String groupsId);

    /**
     *根据ID删除分组信息
     * @param groupsId ：分组ID字符串
     * @return
     */
    Integer delGroups(@Param("groupsId") String groupsId);


}