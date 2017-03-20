package com.hqkj.example.dao;


import com.hqkj.example.entity.Sim;
import com.hqkj.example.util.MyMapper;

import java.util.List;
import java.util.Map;

public interface SimMapper extends MyMapper<Sim>{

    /**
     * 分页获取SIM卡信息
     * @param params ：参数集合
     * @return
     */
    List<Sim> querySimList(Map<String,Object> params);

    /**
     * 校验SIM卡是否存在
     * @param params ：参数集合
     * @return
     */
    Integer querySimExist(Map<String,Object> params);

}