package com.hqkj.example.service.impl;

import com.hqkj.example.constants.ResultConstant;
import com.hqkj.example.dao.DevicetypeMapper;
import com.hqkj.example.entity.Devicetype;
import com.hqkj.example.service.DevicetypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/17.
 */
@Service
public class DevicetypeServiceImpl implements DevicetypeService {


    @Resource
    private DevicetypeMapper devicetypeMapper;  //设备类型的持久化对象


    /**
     * 分页获取设备类型
     * @return
     */
    @Override
    public List<Devicetype> queryDevicetypeList() {

        return devicetypeMapper.selectAll();
    }

    /**
     * 设备类型：新增、修改、删除
     * @param devicetype ：设备类型对象
     * @param devicetypeId ：设备类型ID字符串
     * @param excuType ：操作类型：1 -新增|| 修改  2 -删除
     * @return
     */
    @Override
    public String operationDevicetype(Devicetype devicetype, String devicetypeId, Integer excuType) {
        try {
            //使用分支，判断如何操作
            switch (excuType){
                case 1:   //新增 | 修改
                    //判断对象是否为空
                    if(devicetype == null){
                        //返回参数为空状态码
                        return ResultConstant.resuInfo(ResultConstant.PARAM_NULL_ERROR,"参数为空！");
                    }
                    //创建参数集合
                    Map<String,Object> params = new HashMap<>();
                    params.put("deviceType",devicetype.getDeviceType());
                    params.put("id",devicetype.getId());
                    //校验是否存在
                    Integer existCount = devicetypeMapper.queryDevicetypeExist(params);
                    //判断是否已存在
                    if(existCount > 0){
                        //返回已存在状态码
                        return ResultConstant.resuInfo(ResultConstant.EXIST_ERROR,"该设备类型已存在！");
                    }
                    //创建变量接收结果
                    Integer count = 0;
                    //判断是新增还是修改
                    if(devicetype.getId() != null && devicetype.getId() > 0){   //修改
                        //调用修改接口
                        count = devicetypeMapper.updateByPrimaryKey(devicetype);
                    }else{  //新增
                        //调用新增接口
                        count = devicetypeMapper.insertSelective(devicetype);
                    }
                    //判断是否操作成功
                    if(count <= 0){   //失败
                        return ResultConstant.resuInfo(ResultConstant.ERROR,"操作设备类型失败！");
                    }
                    break;
                case  2:   //删除
                    //判断参数是否为空
                    if(devicetypeId == null || devicetypeId.equals("")){
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
            return ResultConstant.resuInfo(ResultConstant.ERROR,"系统异常！");
        }
        return ResultConstant.resuInfo(ResultConstant.SUCCESS,"成功操作设备类型！");
    }

    /**
     * 获取单个设备类型对象
     * @return
     */
    @Override
    public Devicetype queryDevicetype(Devicetype devicetype) {

        return devicetypeMapper.selectOne(devicetype);
    }
}
