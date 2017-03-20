package com.hqkj.example.service;

import com.hqkj.example.entity.Sim;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/18.
 */
public interface SimService {

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

    /**
     *  新增、修改、删除SIM卡信息
     * @param sim ：SIM对象 用于新增、修改
     * @param sinId ：ID字符串 用于单个、批量删除
     * @param excuType ：操作类型 1 - 新增、修改 2 - 删除
     * @return
     */
    String operationSim(Sim sim,String sinId,Integer excuType);

    /**
     * 获取单个对象
     * @param sim ：sim对象
     * @return
     */
    Sim querySim(Sim sim);

}
