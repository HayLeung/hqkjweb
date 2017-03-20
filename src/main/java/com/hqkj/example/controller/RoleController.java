package com.hqkj.example.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.hqkj.example.constants.ResultConstant;
import com.hqkj.example.dto.ResultData;
import com.hqkj.example.dto.Tree;
import com.hqkj.example.entity.Func;
import com.hqkj.example.entity.Role;
import com.hqkj.example.entity.RoleFunc;
import com.hqkj.example.service.RoleService;
import com.hqkj.example.util.DateForToStr;
import com.hqkj.example.util.LoggerDB;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by HuangXuFeng on 2017/3/9.
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    private  Logger logger=Logger.getLogger(RoleController.class);

    /**
     * 新增角色
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/roleAdd",method = RequestMethod.POST,  produces = "application/json;charset=UTF-8")
    public ResultData roleAdd(Role role)
    {
        logger.info("into========== 添加角色Action："+ LoggerDB.resuMethod(this));
        logger.debug("输入：role："+ JSON.toJSONString(role));

        ResultData resultData=new ResultData();
        //非空验证
        if (role!=null&&StringUtils.isNotBlank(role.getRoleName()))
        {
            try{
                //添加到数据库
                role.setStatus((byte)0); //默认状态是正常的0=正常
                int num=roleService.roleAdd(role);

                //是否添加失败
                if (num<1)
                {
                    resultData.setResuleCode(ResultConstant.ERROR);
                }
            }catch (Exception e)
            {
                resultData.setResuleCode(ResultConstant.EXCEPTION_ERROR);
                logger.error("系统异常，操作失败，错误码："+ResultConstant.EXCEPTION_ERROR);
            }

        }else
        {
            resultData.setResuleCode(ResultConstant.EXCEPTION_ERROR);
        }

        logger.debug("输出：resultCode"+JSON.toJSONString(resultData));
        logger.info("go========== 添加角色Action："+ LoggerDB.resuMethod(this));
        return  resultData;
    }

    /**
     * 根据ID删除角色
     * @param roleId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/roleDel",method = RequestMethod.POST,  produces = "application/json;charset=UTF-8")
    public ResultData roleDel(Integer roleId)
    {
        logger.info("into========== 根据ID删除角色Action："+ LoggerDB.resuMethod(this));
        logger.debug("输入：roleId："+ JSON.toJSONString(roleId));

        ResultData resultData=new ResultData();
        try{
            //根据ID删除数据
            int num=roleService.roleDel(roleId);

            //是否失败
            if (num<1)
            {
                resultData.setResuleCode(ResultConstant.ERROR);
            }
        }catch (Exception e)
        {
            resultData.setResuleCode(ResultConstant.EXCEPTION_ERROR);
            logger.error("系统异常，操作失败，错误码："+ResultConstant.EXCEPTION_ERROR);
        }

        logger.debug("输出：resultCode:"+JSON.toJSONString(resultData));
        logger.info("go========== 根据ID删除角色Action："+ LoggerDB.resuMethod(this));
        return  resultData;
    }

    /**
     * 批量删除角色
     * @param delArray
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/roleDelAll",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResultData roleDelAll(Integer[] delArray )
    {
        logger.info("into========== 根据ID批量删除角色Action："+ LoggerDB.resuMethod(this));
        logger.debug("输入：delArray："+ JSON.toJSONString(delArray));

        ResultData resultData=new ResultData();
        try{
            //根据ID删除数据
            int num=roleService.roleDelAll(delArray);
            //是否失败
            if (num<1)
            {
                resultData.setResuleCode(ResultConstant.ERROR);
            }
        }catch (Exception e)
        {
            resultData.setResuleCode(ResultConstant.EXCEPTION_ERROR);
            logger.error("系统异常，操作失败，错误码："+ResultConstant.EXCEPTION_ERROR);
            e.printStackTrace();
        }

        logger.debug("输出："+JSON.toJSONString(resultData));
        logger.info("go========== 根据ID批量删除角色Action："+ LoggerDB.resuMethod(this));
        return  resultData;
    }

    /**
     * 分页查询角色列表
     * @param role
     * @param pageIndex
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryPage",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResultData queryPage(Role role,Integer pageIndex,Integer limit)
    {
        logger.info("into========== 分页查询角色列表Action："+ LoggerDB.resuMethod(this));
        logger.debug("输入：role："+ JSON.toJSONString(role)+",pageNum:"+pageIndex+",pageSize:"+limit);

        ResultData resultData=new ResultData();
        List<Role> roleList=new ArrayList<>();

        //分页查询角色列表
        roleList=roleService.queryPage(role,pageIndex,limit);
        PageInfo page = new PageInfo(roleList);

        //封装返回数据
        resultData.setData(roleList);
        resultData.setResuleCode(ResultConstant.SUCCESS);
        resultData.setTotal(page.getTotal());

        logger.debug("输出："+JSON.toJSONString(resultData));
        logger.info("go========== 分页查询角色列表Action："+ LoggerDB.resuMethod(this));
        return  resultData;
    }

    /**
     * 根据角色ID修改角色信息
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/roleUpdate",method = RequestMethod.POST,   produces = "application/json;charset=UTF-8")
    public ResultData roleUpdate(Role role,String roleFuncArray)
    {
        logger.info("into========== 根据角色ID修改角色信息Action："+ LoggerDB.resuMethod(this));
        logger.debug("输入：role："+ JSON.toJSONString(role));

        ResultData resultData=new ResultData();
        try{
            List<RoleFunc>  roleFuncList= getTreeLst(roleFuncArray,role.getRoleId());
            //根据ID修改数据
            int num=roleService.roleUpdate(role,roleFuncList);

            //是否失败
            if (num<1)
            {
                resultData.setResuleCode(ResultConstant.ERROR);
            }
        }catch (Exception e)
        {
            resultData.setResuleCode(ResultConstant.EXCEPTION_ERROR);
            logger.error("系统异常，操作失败，错误码："+ResultConstant.EXCEPTION_ERROR);
        }

        logger.debug("输出："+JSON.toJSONString(resultData));
        logger.info("go========== 根据角色ID修改角色信息Action："+ LoggerDB.resuMethod(this));
        return  resultData;
    }

    @ResponseBody
    @RequestMapping(value = "/queryKey",method = RequestMethod.POST,  produces = "application/json;charset=UTF-8")
    public ResultData queryKey(Role role)
    {
        logger.info("into========== 分页查询角色列表Action："+ LoggerDB.resuMethod(this));
        logger.debug("输入：role："+ JSON.toJSONString(role));

        ResultData resultData=new ResultData();
        Role roles=new Role();
        roles=roleService.queryKey(role);

        //封装返回数据
        resultData.setData(roles);
        resultData.setResuleCode(ResultConstant.SUCCESS);

        logger.debug("输出："+JSON.toJSONString(resultData));
        logger.info("go========== 分页查询角色列表Action："+ LoggerDB.resuMethod(this));
        return  resultData;
    }

    public List<RoleFunc> getTreeLst(String str,Integer roleId)
    {
        String[] strs= str.split(",");
        List<RoleFunc> roleFuncList=new ArrayList<>();
        for (String s:strs) {
            if (StringUtils.isNotBlank(s))
            {
                RoleFunc roleFunc=new RoleFunc();
                roleFunc.setRoleId(roleId);
                roleFunc.setFuncId(s);
            }
        }
        return  roleFuncList;
    }

}
