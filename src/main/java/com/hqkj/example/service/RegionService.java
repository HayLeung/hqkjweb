package com.hqkj.example.service;

import com.hqkj.example.entity.Region;

import java.util.List;

/**
 * Created by HuangXuFeng on 2017/3/9.
 */
public interface RegionService {

    /**
     * 动态查询
     * @param region
     * @return
     */
    public List<Region> select(Region region);

    /**
     * 查询父级
     * @return
     */
    public Region selectParent(String regionId);

    /**
     * 根据ID查询
     */
    public Region selectByPrimaryKey(String regionId);

}
