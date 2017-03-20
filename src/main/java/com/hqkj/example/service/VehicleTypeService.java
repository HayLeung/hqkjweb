package com.hqkj.example.service;

import com.hqkj.example.entity.VehicleType;

import java.util.List;

/**
 * Created by Administrator on 2017/3/9.
 */
public interface VehicleTypeService {

    /**
     * 查询所有的车辆类型
     * @return ：返回车辆类型列表
     */
    List<VehicleType> queryVehicleTypeAll();
}
