package com.hqkj.example.dto;

import com.hqkj.example.entity.Func;

import java.util.List;

/**
 * Created by HuangXuFeng on 2017/3/15.
 */
public class Tree {
    private String text; //节点显示的数据
    private  Integer id; //节点ID
    private  boolean checked; //是否选中
    private boolean expanded;//是否展开
    private List<Tree> children; //子节点

    public String getText() {
        return text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getChecked() {
       return this.checked ;
    }

    public boolean getExpanded() {
       return this.expanded ;
    }


    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }



}
