package com.hqkj.example.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hqkj.example.constants.ResultConstant;
import com.hqkj.example.entity.Groups;
import com.hqkj.example.service.GroupsService;
import com.hqkj.example.util.LoggerDB;
import com.hqkj.example.util.PageSumComp;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */
@RestController
@RequestMapping("/groups")
public class GroupsController {

    //日志对象
    private Logger logger = Logger.getLogger(GroupsController.class);


    @Resource
    private GroupsService groupsService;


    /**
     * 分页获取分组信息
     * @param pageNum ：当前页码
     * @param name ：分组名称
     * @param request ：请求对象
     * @return
     */
    @RequiresPermissions("groups:queryGroupsList")
    @PostMapping(value = "/queryGroupsList", produces ="text/application;charset=UTF-8")
    public String queryGroupsList(@RequestParam(value = "pageNum",required = true) Integer pageNum,
                                  @RequestParam(value = "groupsName",required = false) String name,
                                  HttpServletRequest request){
        logger.info("进入："+ LoggerDB.resuMethod(this));
        try{
            //创建参数集合
            Map<String,Object> params = new HashMap<>();
            //判断模糊查询分组是否为空
            params.put("name",name);
            //使用分页插件进行分页
            Page<Groups> page = PageHelper.startPage(pageNum*PageSumComp.defaultCount, PageSumComp.defaultCount);
            //调用接口查询数据
            List<Groups> groupsList = groupsService.queryGroupsList(params);
            //获取总数量
            Integer total = (int)page.getTotal();
            //将数据返回
            String resuMsg = ResultConstant.convertJSON$ToString(groupsList,pageNum, total, null);
            logger.info("跳出："+ LoggerDB.resuMethod(this));
            return resuMsg;
        }catch (Exception e){
            logger.info("跳出："+ LoggerDB.resuMethod(this));
            //返回异常状态码
            return ResultConstant.resuInfo(ResultConstant.EXCEPTION_ERROR,"系统异常！");
        }

    }


    /**
     * 操作分组信息
     * @param groups ：分组对象：用于新增、修改
     * @param request ：请求对象
     * @param groupsId ：分组ID字符串，用于批量删除
     * @param excuType ：操作类型： 1 -新增 || 修改   2 - 删除
     * @return
     */
    @RequiresPermissions("groups:operationGroups")
    @PostMapping(value = "/operationGroups",produces = "text/application;charset=UTF-8")
    public String operationGroups(Groups groups,HttpServletRequest request,
                                  @RequestParam(value = "groupsId",required = false)String groupsId,
                                  @RequestParam(value = "excuType",required = true)Integer excuType){
        logger.info("进入："+ LoggerDB.resuMethod(this));
        //异常处理
        try {
            //调用接口进行操作
            String resuMsg = groupsService.addOrEditOrDelGroups(groups, groupsId, excuType);
            logger.info("跳出："+ LoggerDB.resuMethod(this));
            //将信息返回
            return resuMsg;
        }catch (Exception e){
            //返回异常状态码
            logger.info("跳出："+ LoggerDB.resuMethod(this));
            return ResultConstant.resuInfo(ResultConstant.EXCEPTION_ERROR,"系统异常！");
        }
    }


    /**
     * 根据ID获取单个分组对象
     * @param request ：请求对象
     * @param groups ：分组对象
     * @return
     */
    @RequiresPermissions("groups:queryGroup")
    @PostMapping(value = "/queryGroup",produces = "application/json;charset=UTF-8")
    public String  queryGroup(HttpServletRequest request,
                              Groups groups){
        logger.info("进入："+LoggerDB.resuMethod(this));
        //判断分组对象是否为为空
        if(groups == null){
            //参数为空的状态码
            return ResultConstant.resuInfo(ResultConstant.PARAM_NULL_ERROR,"参数为空！");
        }
        //调用接接口，获取分组对象
        Groups obj = groupsService.queryGroup(groups);
        //将对象进行封装
        String resuMsg = ResultConstant.convertJSON$ToString(groups);
        logger.info("跳出："+LoggerDB.resuMethod(this));
        //返回结果
        return resuMsg;
    }


}
