package com.hqkj.example.dto;

import com.hqkj.example.constants.ResultConstant;

/**
 * Created by HuangXuFeng on 2017/3/10.
 */
public class ResultData {
    private  Object data;//返回数据
    private  long total;//总数
    private  String resMsg;//返回提示信息
    private  boolean isError=false;//是否错误
    private  String resuleCode=  ResultConstant.SUCCESS;//状态码
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getResuleCode() {
        return resuleCode;
    }

    public void setResuleCode(String resuleCode) {
        this.resuleCode = resuleCode;
    }




}
