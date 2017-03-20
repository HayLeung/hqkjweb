package com.hqkj.example.service;

import com.hqkj.example.entity.Devicetype;

import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */
public interface DevicetypeService {

    /**
     * 分页获取设备类型
     * @return
     */
    public List<Devicetype> queryDevicetypeList();


    /**
     * 设备类型：新增、修改、删除
     * @param devicetype ：设备类型对象
     * @param devicetypeId ：设备类型ID字符串
     * @param excuType ：操作类型：1 -新增|| 修改  2 -删除
     * @return
     */
    public String operationDevicetype(Devicetype devicetype,String devicetypeId,Integer excuType);


    /**
     * 获取单个设备类型对象
     * @return
     */
    public Devicetype queryDevicetype(Devicetype devicetype);


}
