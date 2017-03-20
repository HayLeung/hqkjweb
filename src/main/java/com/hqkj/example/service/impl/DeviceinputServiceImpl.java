package com.hqkj.example.service.impl;

import com.hqkj.example.constants.ResultConstant;
import com.hqkj.example.dao.DeviceinputMapper;
import com.hqkj.example.entity.Deviceinput;
import com.hqkj.example.service.DeviceinputService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/17.
 */
@Service
public class DeviceinputServiceImpl implements DeviceinputService {


    private DeviceinputMapper deviceinputMapper;

    /**
     * 分页获取设备信息
     * @param params ：参数集合
     * @return
     */
    @Override
    public List<Deviceinput> queryDeviceinputList(Map<String, Object> params) {
        return deviceinputMapper.queryDeviceinputList(params);
    }

    /**
     * 用于做新增、修改、删除设备信息
     * @param deviceinput ：设备对象
     * @param deviceId ：设备ID字符串，以逗号隔开
     * @param excuType ：操作类型 1 - 新增|修改  2 - 删除
     * @return
     */
    @Override
    public String addOrEditOrDelDeviceinput(Deviceinput deviceinput, String deviceId, Integer excuType) {
        try {
            //使用分支，判断如何操作
            switch (excuType){
                case 1:   //新增 | 修改
                    //判断对象是否为空
                    if(deviceinput == null){
                        //返回参数为空状态码
                        return ResultConstant.resuInfo(ResultConstant.PARAM_NULL_ERROR,"参数为空！");
                    }
                    //创建参数集合
                    Map<String,Object> params = new HashMap<>();
                    params.put("deviceid",deviceinput.getDeviceid());
                    params.put("id",deviceinput.getId());
                    //校验是否存在
                    Integer existCount = deviceinputMapper.queryDeviceinputExist(params);
                    //判断是否已存在
                    if(existCount > 0){
                        //返回已存在状态码
                        return ResultConstant.resuInfo(ResultConstant.EXIST_ERROR,"已存在该设备！");
                    }
                    //创建变量接收结果
                    Integer count = 0;
                    //判断是新增还是修改
                    if(deviceinput.getId() != null && deviceinput.getId() > 0){   //修改
                        //调用修改接口
                        count = deviceinputMapper.updateByPrimaryKey(deviceinput);
                    }else{  //新增
                        //调用新增接口
                        count = deviceinputMapper.insertSelective(deviceinput);
                    }
                    //判断是否操作成功
                    if(count <= 0){   //失败
                        return ResultConstant.resuInfo(ResultConstant.ERROR,"操作设备失败！");
                    }
                    break;
                case  2:   //删除
                    //判断参数是否为空
                    if(deviceId == null || deviceId.equals("")){
                        //参数值为空
                        return ResultConstant.resuInfo(ResultConstant.PARAM_NULL_ERROR,"参数为空！");
                    }
                    //调用删除接口
                    Integer delCount = deviceinputMapper.delDeviceinput(deviceId);
                    //判断是是否删除成功
                    if(delCount <= 0){
                        //删除失败
                        return ResultConstant.resuInfo(ResultConstant.ERROR,"删除失败！");
                    }
                    break;
            }
        }catch (Exception e){
            //返回异常状态码
            return ResultConstant.resuInfo(ResultConstant.EXCEPTION_ERROR,"系统异常！");
        }
        return ResultConstant.resuInfo(ResultConstant.SUCCESS,"成功操作设备！");
    }


    /**
     * 根据ID获取单个设备对象
     * @param deviceinput ：设备对象
     * @return
     */
    @Override
    public Deviceinput queryDeviceinput(Deviceinput deviceinput) {
        return deviceinputMapper.selectOne(deviceinput);
    }
}
