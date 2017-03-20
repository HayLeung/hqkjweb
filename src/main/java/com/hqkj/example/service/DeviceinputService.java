package com.hqkj.example.service;

import com.hqkj.example.entity.Deviceinput;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/17.
 */
public interface DeviceinputService {


    /**
     * 分页获取设备信息
     * @param params ：参数集合
     * @return
     */
    List<Deviceinput> queryDeviceinputList(Map<String,Object> params);


    /**
     * 用于做新增、修改、删除设备信息
     * @param deviceinput ：设备对象
     * @param deviceId ：设备ID字符串，以逗号隔开
     * @param excuType ：操作类型 1 - 新增|修改  2 - 删除
     * @return
     */
    String addOrEditOrDelDeviceinput(Deviceinput deviceinput,String deviceId,Integer excuType);


    /**
     * 根据ID获取单个设备对象
     * @param deviceinput ：设备对象
     * @return
     */
    Deviceinput queryDeviceinput(Deviceinput deviceinput);


}
