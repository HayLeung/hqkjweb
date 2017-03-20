package com.hqkj.example.util;

import java.util.Map;

/**
 * Created by Administrator on 2016/12/21 0021.
 */
public class Validations {

    /**
     * 校验分页起始位置
     * @param params：Map集合
     * @return ：  Map集合
     */
    public static Map<String,Object> validationObject(Map<String,Object> params){
        //获取集合中的PageNum
        Object obj = params.get("pageNum");
        //判断是否为空
        if (obj != null){
            //将对象转换成Integer
            Integer frist = (Integer.valueOf(obj.toString()) - 1) * (int)params.get("showNum");   //计算起始位置
            //将起始位置存入集合
            params.put("pageNum",frist);
        }
        //返回集合
        return params;
    }
}
