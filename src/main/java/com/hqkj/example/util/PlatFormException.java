package com.hqkj.example.util;

import java.io.PrintStream;

/**
 * Created by Administrator on 2016/12/21 0021.
 */
public class  PlatFormException extends  Exception {

    //无参构造
    public PlatFormException(){

    }

    public PlatFormException(String str){
        super(str);
    }


    @Override
    public void printStackTrace() {
        System.out.println("出现异常，回滚数据....");
    }

    @Override
    public void printStackTrace(PrintStream s) {
        System.out.println("自定义异常类型......");
    }

    @Override
    public String getMessage() {
        StackTraceElement stackTraceElement= this.getStackTrace()[0];// 得到异常棧的首个元素
        //返回异常出现位置
        return "异常出现在：ClassName："+stackTraceElement.getFileName()+"\n"+
                "LineNumber："+stackTraceElement.getLineNumber()+"\n"+
                "Method："+stackTraceElement.getMethodName();
    }
}
