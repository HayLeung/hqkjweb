package com.hqkj.example.dao;


import com.hqkj.example.entity.User;
import com.hqkj.example.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper extends MyMapper<User>{

    /**
     * 根据用户名获取用户信息，如果传入ID，则进行校验是否存在当前用户名
     * @param user ：用户对象
     * @return ：返回用户对象
     */
    User queryUserByName(User user);

    /**
     * 分页获取用户信息
     * @param params ：参数集合
     * @return ：返回用户列表
     */
    List<User> queryUserList(Map<String,Object> params);

    /**
     * 根据ID获取单个用户对象
     * @param userId ：用户ID
     * @return ：返回用户对象
     */
    User queryUserInfo(@Param("userId")Integer userId);

    /**
     * 修改用户密码
     * @param user ：用户对象
     * @return ：返回用户对象
     */
    Integer modifyPass(User user);

    /**
     * 删除用户
     * @param userId：用户ID字符串
     * @return
     */
    Integer delUser(@Param("userId")String userId);

}