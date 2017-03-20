package com.hqkj.example.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hqkj.example.constants.ResultConstant;
import com.hqkj.example.entity.Sim;
import com.hqkj.example.service.SimService;
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
 * Created by Administrator on 2017/3/18.
 */
@RestController
@RequestMapping("/sim")
public class SimController {

    @Resource
    private SimService simService;  //sim实现对象

    //日志对象
    private Logger logger = Logger.getLogger(SimController.class);


    /**
     * 分页多条件查询SIM信息
     * @param request ：请求对象
     * @param startTime ：开始时间
     * @param stopTime ：结束时间
     * @param simType ：SIM类型
     * @param carrier ：运营商
     * @param status ：状态
     * @param searchContent ：搜索内容
     * @param pageNum ：当前页
     * @return
     */
    @RequiresPermissions("sim:querySimList")
    @PostMapping(value = "/querySimList",produces = "application/json;charset=UTF-8")
    public String querySimList(HttpServletRequest request,
                               @RequestParam(value = "startTime",required = false)String startTime,
                               @RequestParam(value = "stopTime",required = false)String stopTime,
                               @RequestParam(value = "simType",required = false)Integer simType,
                               @RequestParam(value = "carrier",required = false)Integer carrier,
                               @RequestParam(value = "status",required = false)Integer status,
                               @RequestParam(value = "searchContent",required = false)String searchContent,
                               @RequestParam(value = "pageNum",required = true)Integer pageNum){
        logger.info("进入："+ LoggerDB.resuMethod(this));
        //创建参数集合
        Map<String,Object> params = new HashMap<>();
        params.put("startTime",startTime);
        params.put("stopTime",stopTime);
        params.put("simType",simType);
        params.put("carrier",carrier);
        params.put("status",status);
        params.put("searchContent",searchContent);
        //调用分页插件
        Page<Sim> page = PageHelper.startPage(pageNum*PageSumComp.defaultCount, PageSumComp.defaultCount);
        //调用分页接口
        List<Sim> simList = simService.querySimList(params);
        //获取总数量
        Integer sum = (int)page.getTotal();
        //封装返回数据
        String resuMsg = ResultConstant.convertJSON$ToString(simList,pageNum, sum, null);
        logger.info("跳出："+ LoggerDB.resuMethod(this));
        //返回数据
        return resuMsg;
    }

    /**
     * 根据ID获取单个sim对象
     * @param request ：请求对象
     * @param sim ：SIM卡对象
     * @return
     */
    @PostMapping(value = "/querySim",produces = "application/json;charset=UTF-8")
    public String querySim(HttpServletRequest request,Sim sim){
        logger.info("进入："+ LoggerDB.resuMethod(this));
        //判断对象是否为空
        if(sim == null){
            //返回参数为空
            return ResultConstant.resuInfo(ResultConstant.PARAM_NULL_ERROR,"参数为空！");
        }
        Sim obj = simService.querySim(sim);
        //封装返回数据
        String resuMsg = ResultConstant.convertJSON$ToString(obj);
        logger.info("跳出："+ LoggerDB.resuMethod(this));
        //返回数据
        return resuMsg;
    }


    /**
     * 新增、修改、删除SIM信息
     * @param request ：请求对象
     * @param sim ：SIM对象
     * @param simId ：Sim ID字符串
     * @param excuType ：操作类型 1 - 新增||修改 2 - 删除
     * @return
     */
    @PostMapping(value = "/operationSim",produces = "application/json;charset=UTF-8")
    public String operationSim(HttpServletRequest request, Sim sim,
                               @RequestParam(value = "simId",required = false)String simId,
                               @RequestParam(value = "excuType",required = true)Integer excuType){
        logger.info("进入："+ LoggerDB.resuMethod(this));
        //判断操作值是否为空
        if(excuType == null || excuType <= 0){
            //返回参数为空状态码
            return ResultConstant.resuInfo(ResultConstant.PARAM_NULL_ERROR,"参数为空！");
        }
        //调用操作接口
        String resuMsg = simService.operationSim(sim, simId, excuType);
        logger.info("跳出："+ LoggerDB.resuMethod(this));
        //返回字符串
        return resuMsg;
    }

}
