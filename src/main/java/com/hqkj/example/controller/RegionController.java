package com.hqkj.example.controller;

import com.alibaba.fastjson.JSON;
import com.hqkj.example.constants.ResultConstant;
import com.hqkj.example.dto.ResultData;
import com.hqkj.example.entity.Region;
import com.hqkj.example.service.RegionService;
import com.hqkj.example.util.LoggerDB;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by HuangXuFeng on 2017/3/9.
 */
@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService  regionService;

    private Logger logger =Logger.getLogger(RegionController.class);
    @ResponseBody
    @RequestMapping(value = "/selectSubordinate",  produces = "application/json;charset=UTF-8")
    public ResultData selectSubordinate(String regionId)
    {
        logger.info("into========== 查询子级城市Action："+ LoggerDB.resuMethod(this));
        logger.debug("输入：regionId："+regionId);

        ResultData resultData=new ResultData();

        List<Region> regionList=new ArrayList<>();
        Region region=new Region(); //条件查询对象
        //非空验证
        if (!StringUtils.isNotBlank(regionId))
        {
            region.setParentId("0");//默认查询全部省
        }

        //根据父级ID查询
        regionList=regionService.select(region);

        //封装返回结果
        resultData.setData(regionList);

        logger.debug("输出："+ JSON.toJSONString(resultData));
        logger.info("go========== 查询子级城市Action："+ LoggerDB.resuMethod(this));
        return  resultData;
    }
    @ResponseBody
    @RequestMapping(value = "/selectParent",params = {"regionId"}, produces = "application/json;charset=UTF-8")
    public ResultData selectParent(String regionId)
    {
        logger.info("into========== 查询父级城市Action："+ LoggerDB.resuMethod(this));
        logger.debug("输入：regionId："+regionId);

        ResultData resultData=new ResultData();
        Region region=new Region();

        //非空验证
        if (StringUtils.isNotBlank(regionId))
        {
            region=regionService.selectParent(regionId);
        }else
        {
            resultData.setResuleCode(ResultConstant.PARAM_NULL_ERROR);
        }

        //封装返回结果
        resultData.setData(region);

        logger.debug("输出："+JSON.toJSONString(resultData));
        logger.info("go========== 查询父级城市Action："+ LoggerDB.resuMethod(this));
        return  resultData;
    }
    @ResponseBody
    @RequestMapping(value = "/selectRegion",params = {"regionId"},  produces = "application/json;charset=UTF-8")
    public ResultData selectRegion(String regionId)
    {
        logger.info("into========== ID查询城市Action："+ LoggerDB.resuMethod(this));
        logger.debug("输入：regionId："+regionId);

        ResultData resultData=new ResultData();
        Region region=new Region();

        //非空验证
        if (StringUtils.isNotBlank(regionId))
        {
            region=regionService.selectByPrimaryKey(regionId);
        }else
        {
            resultData.setResuleCode(ResultConstant.PARAM_NULL_ERROR);
        }

        //封装返回结果
        resultData.setData(region);

        logger.debug("输出："+JSON.toJSONString(resultData));
        logger.info("go========== ID查询城市Action："+ LoggerDB.resuMethod(this));
        return  resultData;
    }

}
