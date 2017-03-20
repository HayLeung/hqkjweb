package com.hqkj.example.util;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 龚海量 on 2017/1/18 0018.
 * Create type ：用于将参数的名称进行反射，添加到对象中
 */
public class HttpParam {


    /**
     * 将参数名进行获取，反射至对象中
     * @param request
     * @param className
     * @return
     */
    public static Object acticeLoad(HttpServletRequest request,String className){
        //获取请求对象中的所有参数
        Enumeration rnames=request.getParameterNames();
        //遍历请求中的参数
        for (Enumeration e = rnames ; e.hasMoreElements() ;) {
            //获取请求中的参数名
            String thisName=e.nextElement().toString();
            //根据参数名获取参数值
            String thisValue=request.getParameter(thisName);

            System.out.println(thisName+"-------"+thisValue);
        }

        //根据字符串，获取类类型
        try {
            //异常处理
            Class c = Class.forName(className);
            Method[] methods = c.getMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                System.out.println("方法名称:" + methodName);
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (Class<?> clas : parameterTypes) {
                    String parameterName = clas.getName();
                    System.out.println("参数名称:" + parameterName);
                }
                System.out.println("*****************************");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return null;
    }





    /**
     * 取Bean的属性和值对应关系的MAP
     *
     * @param bean
     * @return Map
     */
    public static Map<String, String> getFieldValueMap(Object bean) {
        Class<?> cls = bean.getClass();
        Map<String, String> valueMap = new HashMap<String, String>();
        Method[] methods = cls.getDeclaredMethods();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            try {
                String fieldType = field.getType().getSimpleName();
                String fieldGetName = parGetName(field.getName());
                if (!checkGetMet(methods, fieldGetName)) {
                    continue;
                }
                Method fieldGetMet = cls.getMethod(fieldGetName, new Class[] {});
                Object fieldVal = fieldGetMet.invoke(bean, new Object[] {});
                String result = null;
                if ("Date".equals(fieldType)) {
                    result = fmtDate((Date) fieldVal);
                } else {
                    if (null != fieldVal) {
                        result = String.valueOf(fieldVal);
                    }
                }
//              String fieldKeyName = parKeyName(field.getName());
                valueMap.put(field.getName(), result);
            } catch (Exception e) {
                continue;
            }
        }
        return valueMap;
    }

    /**
     * set属性的值到Bean
     *
     * @param bean ：进行反射的对象
     * @param request ：请求对象
     */
    public static Object setFieldValue(Object bean, HttpServletRequest request) {
        Class<?> cls = bean.getClass();
        // 取出bean里的所有方法
        Method[] methods = cls.getDeclaredMethods();  //获取所有的函数
        Field[] fields = cls.getDeclaredFields();   //获取所有的字段

        for (Field field : fields) {   //遍历字段
            try {
                String fieldSetName = parSetName(field.getName());   //拼接Set函数
                if (!checkSetMet(methods, fieldSetName)) {   //校验当前的属性，是否存在Set函数
                    continue;   //不存在则跳出，进行下一次循环
                }
                //获取函数
                Method fieldSetMet = cls.getMethod(fieldSetName, field.getType());
                //获取参数字段名称
                String  fieldKeyName = field.getName();
                //获取字段名，从请求中获取数据
                String value = request.getParameter(fieldKeyName);
                //判断值是否为空
                if (null != value && !"".equals(value)) {
                    //获取字段的类型
                    String fieldType = field.getType().getSimpleName();
                    //为字符串
                    if ("String".equals(fieldType)) {  //字符串类型
                        fieldSetMet.invoke(bean, value);
                    } else if ("Date".equals(fieldType)) {   //时间类型
                        Date temp = parseDate(value);  //进行时间转换
                        fieldSetMet.invoke(bean, temp);
                    } else if ("Integer".equals(fieldType) || "int".equals(fieldType)) { //整数值类型
                        Integer intval = Integer.parseInt(value);  //进行强转
                        fieldSetMet.invoke(bean, intval);
                    } else if ("Long".equalsIgnoreCase(fieldType)) {   //Long类型
                        Long temp = Long.parseLong(value);   //进行强转
                        fieldSetMet.invoke(bean, temp);
                    } else if ("Double".equalsIgnoreCase(fieldType)) {   //双精度数值类型
                        Double temp = Double.parseDouble(value);   //进行强转
                        fieldSetMet.invoke(bean, temp);
                    } else if ("Boolean".equalsIgnoreCase(fieldType)) {    //布尔值类型
                        Boolean temp = Boolean.parseBoolean(value);   //进行强转
                        fieldSetMet.invoke(bean, temp);
                    } else {   //没有找到类型匹配
                        System.out.println("not supper type" + fieldType);
                    }
                }
            } catch (Exception e) {
                continue;
            }
        }
        return bean;
    }



    /**
     * 格式化string为Date
     *
     * @param datestr
     * @return date
     */
    public static Date parseDate(String datestr) {
        if (null == datestr || "".equals(datestr)) {
            return null;
        }
        try {
            String fmtstr = null;
            if (datestr.indexOf(':') > 0) {
                fmtstr = "yyyy-MM-dd HH:mm:ss";
            } else {
                fmtstr = "yyyy-MM-dd";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(fmtstr, Locale.UK);
            return sdf.parse(datestr);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 日期转化为String
     *
     * @param date
     * @return date string
     */
    public static String fmtDate(Date date) {
        if (null == date) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                    Locale.US);
            return sdf.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断是否存在某属性的 set方法
     *
     * @param methods
     * @param fieldSetMet
     * @return boolean
     */
    public static boolean checkSetMet(Method[] methods, String fieldSetMet) {
        for (Method met : methods) {
            if (fieldSetMet.equals(met.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否存在某属性的 get方法
     *
     * @param methods
     * @param fieldGetMet
     * @return boolean
     */
    public static boolean checkGetMet(Method[] methods, String fieldGetMet) {
        for (Method met : methods) {
            if (fieldGetMet.equals(met.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 拼接某属性的 get方法
     *
     * @param fieldName
     * @return String
     */
    public static String parGetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        int startIndex = 0;
        if (fieldName.charAt(0) == '_')
            startIndex = 1;
        return "get"
                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
                + fieldName.substring(startIndex + 1);
    }

    /**
     * 拼接在某属性的 set方法
     *
     * @param fieldName
     * @return String
     */
    public static String parSetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        int startIndex = 0;
        if (fieldName.charAt(0) == '_')
            startIndex = 1;
        return "set"
                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
                + fieldName.substring(startIndex + 1);
    }

    /**
     * 获取存储的键名称（调用parGetName）
     *
     * @param fieldName
     * @return 去掉开头的get
     */
    public static String parKeyName(String fieldName) {
        String fieldGetName = parGetName(fieldName);
        if (fieldGetName != null && fieldGetName.trim() != ""
                && fieldGetName.length() > 3) {
            return fieldGetName.substring(3);
        }
        return fieldGetName;
    }

}
