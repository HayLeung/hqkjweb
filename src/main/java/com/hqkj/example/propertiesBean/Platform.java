package com.hqkj.example.propertiesBean;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Administrator on 2017/3/10.
 */
@ConfigurationProperties(locations = "classpath:platform.properties",prefix = "platform")
public class Platform {

    private Integer cookieDay;  //cookie保存用户名密码的天数


    public Integer getCookieDay() {
        return cookieDay;
    }

    public void setCookieDay(Integer cookieDay) {
        this.cookieDay = cookieDay;
    }
}
