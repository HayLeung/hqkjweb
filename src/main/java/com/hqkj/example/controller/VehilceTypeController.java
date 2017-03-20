package com.hqkj.example.controller;

import com.hqkj.example.constants.ResultConstant;
import com.hqkj.example.dao.VehicleTypeMapper;
import com.hqkj.example.entity.VehicleType;
import com.hqkj.example.service.VehicleTypeService;
import com.hqkj.example.util.LoggerDB;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/3/9.
 */
@RestController
@RequestMapping("/vehicleType")
public class VehilceTypeController {

    //日志对象
    private Logger logger = Logger.getLogger(VehilceTypeController.class);

    @Resource
    private VehicleTypeService vehicleTypeService;  //车辆类型实现对象


    /**
     * 获取所有的车辆类型
     * @param request ：请求对象
     * @return ：返回车辆类型列表
     */
    @PostMapping(value = "queryVehicleTypeAll",produces = "text/application;charset=UTF-8")
    public String queryVehicleTypeAll(HttpServletRequest request){
        logger.info("进入："+ LoggerDB.resuMethod(this));
        //调用接口，获取车辆类型列表
        List<VehicleType> typeList = vehicleTypeService.queryVehicleTypeAll();
        //将列表进行封装
        String resuMsg = ResultConstant.convertJSON$ToString(typeList);
        logger.info("跳出："+ LoggerDB.resuMethod(this));
        return  resuMsg;
    }


}
