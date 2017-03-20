package com.hqkj.example.service;

import com.hqkj.example.dto.Tree;
import com.hqkj.example.entity.Func;

import java.util.List;

/**
 * Created by HuangXuFeng on 2017/3/9.
 */
public interface FuncService {
    public List<Func> selectAll();

    /**
     * 获取树形菜单
     * @return
     */
    public List<Tree> treeQuery(Integer roleId);
}
