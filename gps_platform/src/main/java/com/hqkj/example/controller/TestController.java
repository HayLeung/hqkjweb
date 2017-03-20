package com.hqkj.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/3/7.
 */
@RestController
public class TestController {


    @RequestMapping("/")
    public  String  test(){

        return "hello  word！";
    }
}
