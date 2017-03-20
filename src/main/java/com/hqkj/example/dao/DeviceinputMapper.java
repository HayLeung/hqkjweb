package com.hqkj.example.dao;


import com.hqkj.example.entity.Deviceinput;
import com.hqkj.example.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DeviceinputMapper extends MyMapper<Deviceinput>{


    /**
     * 分页获取设备信息
     * @param params ：参数集合
     * @return
     */
    List<Deviceinput> queryDeviceinputList(Map<String,Object> params);

    /**
     * 校验设备ID是否存在
     * @param params ：参数集合
     * @return
     */
    Integer queryDeviceinputExist(Map<String,Object> params);

    /**
     * 删除设备，批量删除、单个删除
     * @param id ：设备ID字符串
     * @return
     */
    Integer delDeviceinput(@Param("id")String id);

}