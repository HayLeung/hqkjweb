package com.hqkj.example.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hqkj.example.constants.ResultConstant;
import com.hqkj.example.entity.Deviceinput;
import com.hqkj.example.service.DeviceinputService;
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
 * Created by HayLeung on 2017/3/17.
 */
@RestController
@RequestMapping("/deviceinput")
public class DeviceinputController {

    //日志对象
    private Logger logger = Logger.getLogger(DeviceinputController.class);
    @Resource
    private DeviceinputService deviceinputService;  //设备实现对象


    /**
     * 分页获取设备信息
     * @param request ：请求对象
     * @param pageNum ：当前页码
     * @param deviceidType ：设备型号
     * @param status ：状态
     * @param startTime ：开始时间
     * @param stopTime ：结束时间
     * @param searchContent ：设备ID或代理商
     * @return
     */
    @RequiresPermissions("deviceinput:queryDeviceinputList")
    @PostMapping(value = "/queryDeviceinputList",produces = "application/json;charset=UTf-8")
    public String queryDeviceinputList(HttpServletRequest request,
                                       @RequestParam(value = "pageNum",required = true)Integer pageNum,
                                       @RequestParam(value = "deviceidType",required = false)Integer deviceidType,
                                       @RequestParam(value = "status",required = false)Integer status,
                                       @RequestParam(value = "startTime",required = false)String startTime,
                                       @RequestParam(value = "stopTime",required = false)String stopTime,
                                       @RequestParam(value = "searchContent",required = false)String searchContent){
        logger.info("进入："+ LoggerDB.resuMethod(this));
        //创建参数集合
        Map<String,Object> params = new HashMap<>();
        params.put("deviceidType",deviceidType);
        params.put("status",status);
        params.put("startTime",startTime);
        params.put("stopTime",stopTime);
        params.put("searchContent",searchContent);
        //调用分页插件
        Page<Deviceinput> page =  PageHelper.startPage((pageNum* PageSumComp.defaultCount),PageSumComp.defaultCount);
        //调用接口查询设备信息
        List<Deviceinput> list = deviceinputService.queryDeviceinputList(params);
        //获取总数量
        Integer total = (int)page.getTotal();
        //封装
        String resuMsg = ResultConstant.convertJSON$ToString(list,pageNum, total, null);
        logger.info("跳出："+ LoggerDB.resuMethod(this));
        //返回结果
        return resuMsg;
    }


    /**
     *根据ID获取设备对象
     * @param request ：请求对象
     * @param deviceinput ：设备对象
     * @return
     */
    @RequiresPermissions("deviceinput:queryDeviceinput")
    @PostMapping(value = "/queryDeviceinput",produces = "application/json;charset=UTF-8")
    public String queryDeviceinput(HttpServletRequest request,Deviceinput deviceinput){
        logger.info("进入："+ LoggerDB.resuMethod(this));
        //判断对象是否为空
        if(deviceinput == null){
            //返回参数为空状态码
            return ResultConstant.resuInfo(ResultConstant.PARAM_NULL_ERROR,"参数为空！");
        }
        //调用查询接口
        Deviceinput dev = deviceinputService.queryDeviceinput(deviceinput);
        //封装
        String resuMsg = ResultConstant.convertJSON$ToString(dev);
        logger.info("跳出："+ LoggerDB.resuMethod(this));
        //返回设备信息
        return resuMsg;
    }





    /**
     * 新增、修改、删除设备
     * @param request ：请求对象
     * @param deviceinput ：设备对象
     * @param deviceId ：设备ID字符串
     * @param excuType ：操作类型 1-新增 || 修改  2 -删除
     * @return
     */
    @PostMapping(value = "/operationDeviceinput",produces = "application/json;charset=UTF-8")
    public String addOrEditOrDelDeviceinput(HttpServletRequest request,Deviceinput deviceinput,
                                            @RequestParam(value = "deviceId",required = false) String deviceId,
                                            @RequestParam(value = "excuType",required = true)Integer excuType){
        logger.info("进入："+ LoggerDB.resuMethod(this));
        //调用接口操作
        String resuMsg = deviceinputService.addOrEditOrDelDeviceinput(deviceinput, deviceId, excuType);
        logger.info("跳出："+ LoggerDB.resuMethod(this));
        return resuMsg;
    }


}
