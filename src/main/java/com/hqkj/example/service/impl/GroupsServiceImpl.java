package com.hqkj.example.service.impl;

import com.hqkj.example.constants.ResultConstant;
import com.hqkj.example.dao.GroupsMapper;
import com.hqkj.example.entity.Groups;
import com.hqkj.example.service.GroupsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */
@Service
public class GroupsServiceImpl  implements GroupsService {

    @Resource
    private GroupsMapper groupsMapper;


    /**
     * 分页获取 分组信息
     * @param params ： 参数集合
     * @return
     */
    @Override
    public List<Groups> queryGroupsList(Map<String, Object> params) {
        return groupsMapper.queryGroupsList(params);
    }

    /**
     * 新增||修改||删除
     * @param groups ：分组对象：用于新增、修改
     * @param groupsId ：分组ID字符串，用于批量、单个删除分组
     * @param excuType ：操作类型：1 -新增||修改  2 -删除
     * @return
     */
    @Override
    public String addOrEditOrDelGroups(Groups groups, String groupsId, Integer excuType) {
        //使用分支，判断操作类型
        switch(excuType){
            case 1:   //新增 || 修改
                //判断对象是否为空
                if(groups != null){
                    //创建参数集合
                    Map<String,Object> params = new HashMap<>();
                    params.put("name",groups.getName());
                    //判断是新增还是修改
                    if(groups.getGroupid() != null && groups.getGroupid() > 0){  //修改
                        //修改则将ID存入集合
                        params.put("groupsId",groups.getGroupid());
                        //调用接口进行校验
                        Integer editExist = groupsMapper.queryNameExist(params);
                        //判断是否存在
                        if(editExist > 0){
                            //返回存在状态码
                            return ResultConstant.resuInfo(ResultConstant.EXIST_ERROR,"该分组已存在！");
                        }
                        //调用修改接口
                        Integer editCount = groupsMapper.updateByPrimaryKey(groups);
                        //判断是否修改成功
                        if(editCount <= 0){
                            return ResultConstant.resuInfo(ResultConstant.ERROR,"修改分组失败！");
                        }
                    }else{   //新增
                        //调用接口进行校验
                        Integer addExist = groupsMapper.queryNameExist(params);
                        //判断是否存在
                        if(addExist > 0){
                            //返回存在状态码
                            return ResultConstant.resuInfo(ResultConstant.EXIST_ERROR,"该分组已存在！");
                        }
                        //调用修改接口
                        Integer addCount = groupsMapper.insert(groups);
                        //判断是否新增成功
                        if(addCount <= 0){
                            return ResultConstant.resuInfo(ResultConstant.ERROR,"新增分组失败！");
                        }
                    }
                }else{   //参数为空
                    return ResultConstant.resuInfo(ResultConstant.PARAM_NULL_ERROR,"参数为空！");
                }
                break;
            case 2:   //删除
                //判断是否参数为空
                if(groupsId == null || groupsId.equals("")){
                    //参数为空
                    return ResultConstant.resuInfo(ResultConstant.PARAM_NULL_ERROR,"参数为空！");
                }
                //获取分组下的子分组，就将其删除
                List<Integer> numList = groupsMapper.queryChirdenGroups(groupsId);
                //判断是否为空
                if(numList != null && numList.size() > 0){
                    //遍历集合，进行拼接
                    for(Integer groupId : numList){
                        //将ID进行拼接
                        groupsId += ","+groupId;
                    }
                }
                //调用删除接口
                Integer delCount = groupsMapper.delGroups(groupsId);
                //判断是否删除成功
                if(delCount <= 0){
                    //失败状态码
                    return ResultConstant.resuInfo(ResultConstant.ERROR,"删除分组失败！");
                }
                break;
        }
        //返回结果
        return ResultConstant.resuInfo(ResultConstant.SUCCESS,"成功操作分组！");
    }

    /**
     * 根据ID获取单个分组对象
     * @param groups ：分组对象
     * @return
     */
    @Override
    public Groups queryGroup(Groups groups) {
        return groupsMapper.selectOne(groups);
    }
}
