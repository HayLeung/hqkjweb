package com.hqkj.example.util;


/**
 * Created by Administrator on 2016/12/21 0021.
 */
public class PageSumComp {

    //默认的页数
    public static Integer defaultCount = 10;


    /**
     *传入总数量，显示数量计算总页数
     * @param sumCount:总数量
     * @param showCount：每页显示数量
     * @return ：返回总页数
     */
    public static Integer computerSumPage(Integer sumCount,Integer showCount){
        //计算总页数[计算规则：总数量 % 显示的数量 = 0 就用 总数量 / 显示的数量 反之 总数量 / 显示的数量 + 1 ]
        Integer sumPage = sumCount%showCount==0?sumCount/showCount:sumCount/showCount+1;
        //返回总页数
        return sumPage;
    }


    /**
     *传入总数量，使用默认的显示数量计算总页数
     * @param sumCount:总数量
     * @return ：返回总页数
     */
    public static Integer computerSumPage(Integer sumCount){
        //计算总页数[计算规则：总数量 % 显示的数量 = 0 就用 总数量 / 显示的数量 反之 总数量 / 显示的数量 + 1 ]
        Integer sumPage = sumCount%defaultCount==0?sumCount/defaultCount:sumCount/defaultCount+1;
        //返回总页数
        return sumPage;
    }



    /**
     * 校验总页数跟当前页数比较
     * @param pageSum：总页数
     * @param pageNum：当前页
     * @return ：返回当前页
     */
    public static Integer validationPage(Integer pageSum,Integer pageNum){
        //判断当前页是否为空，为空则默认为第一页
        if(pageNum == null){
            pageNum = 1;
        }
        //判断pageSum是否小于等于0 ,并且当前页 大于 总页数
        if(pageSum <= 0 && pageNum>= pageSum){
            pageNum = 1;   //标识页码为 1
        }
        //判断pageSum是否小于等于0，并且当前页 大于 总页数
        if(pageSum > 0 && pageNum>= pageSum){
            pageNum = pageSum;   //标识页码为 总页数
        }
        //返回当前页码
        return pageNum;
    }

}
