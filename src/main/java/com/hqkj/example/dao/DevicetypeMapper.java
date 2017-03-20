package com.hqkj.example.dao;


import com.hqkj.example.entity.Devicetype;
import com.hqkj.example.util.MyMapper;

import java.util.Map;

public interface DevicetypeMapper extends MyMapper<Devicetype>{

    /**
     * 校验是否存在给设备型号
     * @param params ：参数集合
     * @return
     */
    Integer queryDevicetypeExist(Map<String,Object> params);




}