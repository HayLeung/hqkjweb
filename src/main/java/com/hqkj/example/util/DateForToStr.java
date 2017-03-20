package com.hqkj.example.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateForToStr {

	private static SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");   //时间格式对象
	private static SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   //时间格式对象
	
	public static final Integer dayCount = 7;    //配置各种信息显示的时间期限
	
	
	
	/**
	 * 时间转换字符函数
	 * @return
	 */
	public static String dateToStr(Date da){
		if(da==null){
			return "-";
		}
		String str = fmt.format(da);
		return str;
	}
	
	/**
	 * 字符转换时间函数
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String str) throws ParseException{
		Date date = fmt.parse(str);
		return date;
	} 
	
	
    /**** 
     * 传入具体日期 ，返回具体日期减一个月。 
     *  
     * @param date 
     *            日期(2014-04-20) 
     * @return 2014-03-20 
     * @throws ParseException 
     */  
    public static String subMonth(String date) throws ParseException {  
        Date dt = fmt.parse(date);  
        Calendar rightNow = Calendar.getInstance();  
        rightNow.setTime(dt);  
        rightNow.add(Calendar.MONTH, 4);  
        Date dt1 = rightNow.getTime();  
        String reStr = fmt.format(dt1);  
        return reStr;  
    }
	
	
	
	/**
	 * 校验字符串格式，是否为时间类型
	 * @param str
	 * @return
	 */
	public static boolean validationStr(String str){
		try {
			fmt.parse(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 校验字符串是否为数字
	 * @return
	 */
	public static boolean validationNum(String str){
		Pattern pattern = Pattern.compile("[0-9]*"); 
		Matcher isNum = pattern.matcher(str);
		 if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
	}
	
	/**
	 * 添加天数
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static Date addDate(long day) throws ParseException {
		 long time = new Date().getTime(); // 得到指定日期的毫秒数
		 day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
		 time+=day; // 相加得到新的毫秒数
		 return new Date(time); // 将毫秒数转换成日期
	}
	
	
	
	
	
	
	
	
	

	/**
	 * 字符转换时间函数
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate1(String str) throws ParseException{
		//判断是否存在HH:mm:ss
		if(str.indexOf(":")==-1){
			str += " 00:00:00";
		}
		Date date = fmt1.parse(str);
		return date;
	} 
	
	
	
	/**
	 * 时间转换字符函数
	 * @return
	 */
	public static String dateToStr1(Date da){
		if(da==null){
			return "-";
		}
		String str = fmt1.format(da);
		return str;
	}
	
	
	public static List<Integer> splitNumArray(String str, String splitStr)
	{
		List<Integer>  numArray=new ArrayList<>();
		String[] strs= str.split(splitStr);
		for (String s :strs ) {
			if (s!=null&&s.trim()!="")
			{
				numArray.add(Integer.valueOf(s));
			}
		}
		return  numArray;
	}
	
	
	
	
	
	
	
	
	
	
	
}
