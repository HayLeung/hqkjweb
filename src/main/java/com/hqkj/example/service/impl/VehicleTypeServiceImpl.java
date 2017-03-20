package com.hqkj.example.service.impl;

import com.hqkj.example.dao.VehicleTypeMapper;
import com.hqkj.example.entity.VehicleType;
import com.hqkj.example.service.VehicleTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/3/9.
 */
@Service("vehicleTypeService")
public class VehicleTypeServiceImpl implements VehicleTypeService {


    @Resource
    private VehicleTypeMapper vehicleTypeMapper;   //车辆类型持久化对象

    /**
     * 获取所有的车辆类型列表
     * @return ：返回车辆类型列表
     */
    @Override
    public List<VehicleType> queryVehicleTypeAll() {

        return vehicleTypeMapper .selectAll();
    }
}
