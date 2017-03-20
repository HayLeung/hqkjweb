package com.hqkj.example.service;

import com.hqkj.example.entity.User;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/8.
 */
public interface UserService {

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
     * 新增、修改、删除用户信息
     * @param excuType ：操作类型：1 -新增 || -修改 2 -删除
     * @param user ：用户对象
     * @param userId ：用户ID字符串
     * @return ：返回状态码
     */
    String AddOrEditOrDelUserInfo(User user,String userId,Integer excuType);


}
