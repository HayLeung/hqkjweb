package com.hqkj.example.controller;

import com.alibaba.fastjson.JSON;
import com.hqkj.example.constants.ResultConstant;
import com.hqkj.example.dto.Tree;
import com.hqkj.example.entity.Func;
import com.hqkj.example.service.FuncService;
import com.hqkj.example.util.LoggerDB;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuangXuFeng on 2017/3/9.
 */
@RestController
@RequestMapping("/func")
public class FuncController {
    @Autowired
    private FuncService funcService;
    private Logger logger=Logger.getLogger(FuncController.class);
    @RequestMapping("/selectAll")
    public String selectAll()
    {
        List<Func> funcList=funcService.selectAll();
        String resultStr= ResultConstant.convertJSON$ToString(funcList);
        return  resultStr;
    }

    /**
     * 查询角色功能树形菜单
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/treeQuery",method = RequestMethod.POST,  produces = "application/json;charset=UTF-8")
    public List<Tree>  treeQuery(Integer roleId){
        logger.info("into========== 查询角色功能树形菜单Action："+ LoggerDB.resuMethod(this));
        logger.debug("输入：...");

        List<Tree> tree=new ArrayList<>();
        tree=funcService.treeQuery(roleId);//查询角色功能树形菜单

        logger.debug("输出："+JSON.toJSONString(tree));
        logger.info("go========== 查询角色功能树形菜单Action："+ LoggerDB.resuMethod(this));
        return  tree;
    }

}
