package com.hqkj.example.service.impl;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.hqkj.example.dao.RegionMapper;
import com.hqkj.example.entity.Region;
import com.hqkj.example.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by HuangXuFeng on 2017/3/9.
 */
@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionMapper regionMapper;

    @Override
    public List<Region> select(Region region) {

        return regionMapper.select(region);
    }

    @Override
    public Region selectParent(String regionId) {

        //查询城市信息
        Region region=new Region();
        region.setRegionId(regionId);
        region= regionMapper.selectOne(region);
        if (region!=null){
            //在用父级id查询父级城市信息
            String parentId=region.getParentId();

            region=new Region();
            region.setRegionId(parentId);

            region=regionMapper.selectOne(region);
        }

        return region;
    }

    @Override
    public Region selectByPrimaryKey(String regionId) {
        Region region=new Region();
        region.setRegionId(regionId);
        return  regionMapper.selectOne(region);
    }
}
