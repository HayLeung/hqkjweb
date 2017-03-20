package com.hqkj.example.util;


/**
 * 日志管理类
 * @author Administrator
 *
 */
public class LoggerDB {

	public  static final String  runClass = "类名：";
	public  static final String  runMethod = "函数：";
	public  static final String  inputParam = "输入参数：";
	public  static final String  outParam = "输出参数：";
	public  static final String  errorStr ="提示信息：";
	
	
	/**
	 * 获取进入的类，执行的函数
	 * @param obj
	 * @return
	 */
	public static String resuMethod(Object obj){
		String classStr = obj.getClass().getName();   //获取类名
		 StackTraceElement[] stacks = new Throwable().getStackTrace();     //运行数组
		String methodStr = stacks[1].getMethodName();  //获取运行的方法名
		//返回拼接字符串
		return runClass+classStr+runMethod +methodStr;
	}
	
}
