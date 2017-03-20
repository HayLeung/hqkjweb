package com.hqkj.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.hqkj.example.dao.FuncMapper;
import com.hqkj.example.dao.RoleFuncMapper;
import com.hqkj.example.dto.Tree;
import com.hqkj.example.entity.Func;
import com.hqkj.example.entity.RoleFunc;
import com.hqkj.example.service.FuncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuangXuFeng on 2017/3/9.
 */
@Service
public class FuncServiceImpl implements FuncService {

    @Autowired
    FuncMapper funcMapper;
    @Autowired
    RoleFuncMapper roleFuncMapper;
    @Override
    public List<Func> selectAll() {
        PageHelper.startPage(1,2);
        return funcMapper.selectAll();
    }

    @Override
    public List<Tree> treeQuery(Integer roleId) {
        //返回结果
        List<Tree> treeList=new ArrayList<>();

        //先查询父节点
        Func func=new Func();
        func.setLevel(1);//1=根节点
        List<Func> funcList=funcMapper.select(func);
        //填充树形菜单
        for (Func f:funcList) {
            Tree tree =new Tree();
            //数据转换
            tree.setText(f.getFuncName()); //节点显示的内容
            tree.setId(f.getFuncId()); //节点ID
            tree.setChecked(setChecked(roleId,tree,f)); //判断是否已经有了这个权限
            //递归开始，查询子节点
            tree.setChildren(getChildren(f.getFuncId().toString(),roleId));
            treeList.add(tree);
        }

        return treeList;
    }

    /**
     * 递归查询子节点
     * @param parentId
     * @return
     */
    public List<Tree> getChildren(String parentId,Integer roleId)
    {
        //根级父节点ID查询
        List<Tree> treeList =new ArrayList<>();
        Func func=new Func();
        func.setParentId(parentId);
        List<Func> funcList=funcMapper.select(func);

        //是否存在子节点
        if (funcList!=null&&funcList.size()>0)
        {
            for (Func f: funcList) {
                Tree tree=new Tree();
                tree.setId(f.getFuncId());
                tree.setText(f.getFuncName());
                tree.setChecked(setChecked(roleId,tree,f)); //判断是否已经有了这个权限
                tree.setChildren(getChildren(f.getFuncId().toString(),roleId));//递归遍历下面的子节点
                treeList.add(tree); //子节点集合
            }
        }
        return treeList;
    }

    /**
     * 判断是否已经拥有权限
     * @param roleId
     * @param tree
     * @param f
     * @return
     */
    public boolean setChecked(Integer roleId,Tree tree,Func f)
    {
        if (roleId!=null&&roleId>0) {
            //查询并判断角色是否已经有这个权限
            RoleFunc roleFunc = new RoleFunc();
            roleFunc.setFuncId(f.getFuncId().toString());
            roleFunc.setRoleId(roleId);
            roleFunc = roleFuncMapper.selectOne(roleFunc);
            if (roleFunc != null) {
                return true;
            }
        }
        return  false;
    }
}
