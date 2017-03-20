package com.hqkj.example.service.impl;

import com.hqkj.example.constants.ResultConstant;
import com.hqkj.example.dao.UserMapper;
import com.hqkj.example.entity.User;
import com.hqkj.example.service.UserService;
import com.hqkj.example.util.LoggerDB;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/8.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;


    /**
     * 根据用户名获取用户信息，如果传入ID，则进行校验是否存在当前用户名
     * @param user ：用户对象
     * @return
     */
    @Override
    public User queryUserByName(User user) {
        return userMapper.queryUserByName(user);
    }

    /**
     * 分页获取用户信息
     * @param params ：参数集合
     * @return
     */
    @Override
    public List<User> queryUserList(Map<String, Object> params) {
        return userMapper.queryUserList(params);
    }

    /**
     * 根据ID获取单个用户对象
     * @param userId ：用户ID
     * @return
     */
    @Override
    public User queryUserInfo(@Param("userId") Integer userId) {
        return userMapper.queryUserInfo(userId);
    }

    /**
     * 修改用户密码
     * @param user ：用户对象
     * @return
     */
    @Override
    public Integer modifyPass(User user) {
        return userMapper.modifyPass(user);
    }

    /**
     * 新增、修改、删除用户信息
     * @param excuType ：操作类型：1 -新增 || -修改 2 -删除
     * @param user ：用户对象
     * @param userId ：用户ID字符串
     * @return ：返回状态码
     */
    @Override
    public String AddOrEditOrDelUserInfo(User user,String userId, Integer excuType) {
        logger.info("进入："+ LoggerDB.resuMethod(this));
        //使用分支，进行各个类型操作
        switch (excuType){
            case 1:     //新增  || 修改
                //判断用户是否为空
                if(user == null){
                    return ResultConstant.resuInfo(ResultConstant.PARAM_NULL_ERROR,"参数为空！");
                }
                //获取用户
                User obj = userMapper.queryUserByName(user);
                //校验用户名是否存在
                if(obj != null){
                    return ResultConstant.resuInfo(ResultConstant.EXIST_ERROR,"该用户已存在！");
                }
                //创建变量接受返回结果
                Integer operationCount = 0;
                //判断ID是否存在：存在则修改，反之更新
                if(user.getUserId() != null && user.getUserId() > 0){  //修改
                    operationCount = userMapper.updateByPrimaryKeySelective(user);
                }else{  //新增
                    operationCount = userMapper.insert(user);
                }
                //判断是否新增||修改成功
                if(operationCount <= 0){
                    return ResultConstant.resuInfo(ResultConstant.ERROR,"操作用户失败！");
                }
                break;
            case 2:     //删除
                //判断用户ID字符串
                if(userId == null || userId.equals("")){
                    return ResultConstant.resuInfo(ResultConstant.PARAM_NULL_ERROR,"参数为空！");
                }
                //调用删除接口
                Integer count = userMapper.delUser(userId);
                //判断是否删除成功
                if(count <= 0){
                    return ResultConstant.resuInfo(ResultConstant.ERROR,"删除用户失败！");
                }
                break;
        }
        logger.info("跳出："+LoggerDB.resuMethod(this));
        return ResultConstant.resuInfo(ResultConstant.SUCCESS,"成功操作用户！");
    }
}
