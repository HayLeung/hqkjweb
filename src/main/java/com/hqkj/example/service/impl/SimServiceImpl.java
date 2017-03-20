package com.hqkj.example.service.impl;

import com.hqkj.example.constants.ResultConstant;
import com.hqkj.example.dao.SimMapper;
import com.hqkj.example.entity.Sim;
import com.hqkj.example.service.SimService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HayLeung on 2017/3/18.
 */
@Service
public class SimServiceImpl implements SimService {

    @Resource
    private SimMapper simMapper;   //sim持久化对象

    /**
     * 分页获取SIM卡信息
     * @param params ：参数集合
     * @return
     */
    @Override
    public List<Sim> querySimList(Map<String, Object> params) {

        return simMapper.querySimList(params);
    }

    /**
     * 校验SIM卡是否存在
     * @param params ：参数集合
     * @return
     */
    @Override
    public Integer querySimExist(Map<String, Object> params) {
        return simMapper.querySimExist(params);
    }


    /**
     *  新增、修改、删除SIM卡信息
     * @param sim ：SIM对象 用于新增、修改
     * @param sinId ：ID字符串 用于单个、批量删除
     * @param excuType ：操作类型 1 - 新增、修改 2 - 删除
     * @return
     */
    @Override
    public String operationSim(Sim sim, String sinId, Integer excuType) {
        //创建变量接收状态码
        String resuMsg = ResultConstant.SUCCESS;
        try {
            //使用分支，判断如何操作
            switch (excuType){
                case 1:   //新增 | 修改
                    //判断对象是否为空
                    if(sim == null){
                        //返回参数为空状态码
                        return ResultConstant.resuInfo(ResultConstant.PARAM_NULL_ERROR,"参数为空！");
                    }
                    //创建参数集合
                    Map<String,Object> params = new HashMap<>();
                    params.put("simNo",sim.getSimNo());
                    params.put("id",sim.getId());
                    //校验是否存在
                    Integer existCount = simMapper.querySimExist(params);
                    //判断是否已存在
                    if(existCount > 0){
                        //返回已存在状态码
                        return ResultConstant.resuInfo(ResultConstant.EXIST_ERROR,"该SIM卡已存在！");
                    }
                    //创建变量接收结果
                    Integer count = 0;
                    //判断是新增还是修改
                    if(sim.getId() != null && sim.getId() > 0){   //修改
                        //调用修改接口
                        count = simMapper.updateByPrimaryKey(sim);
                    }else{  //新增
                        //调用新增接口
                        count = simMapper.insertSelective(sim);
                    }
                    //判断是否操作成功
                    if(count <= 0){   //失败
                        return ResultConstant.resuInfo(ResultConstant.ERROR,"操作失败！");
                    }
                    break;
                case  2:   //删除
                    //判断参数是否为空
                    if(sinId == null || sinId.equals("")){
                        //参数值为空
                        return ResultConstant.resuInfo(ResultConstant.PARAM_NULL_ERROR,"参数为空！");
                    }
                    //调用删除接口
//                    Integer delCount = deviceinputMapper.delDeviceinput(deviceId);
//                    //判断是是否删除成功
//                    if(delCount <= 0){
//                        //删除失败
//                        return ResultConstant.ERROR;
//                    }
                    break;
            }
        }catch (Exception e){
            //返回异常状态码
            return ResultConstant.resuInfo(ResultConstant.EXCEPTION_ERROR,"系统异常！");
        }
        return resuMsg;
    }

    /**
     * 获取单个对象
     * @param sim ：sim对象
     * @return
     */
    @Override
    public Sim querySim(Sim sim) {
        return simMapper.selectOne(sim);
    }
}
