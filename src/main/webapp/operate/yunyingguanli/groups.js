/**
 * Created by HayLeung on 2017/3/16.
 */

/**
 * 定义函数：define
 * =======>>标识在define函数里面的都为自定义函数
 */
define(function(require,exports,module){
    //引用需要的JS文件
    var config=require("../config.js");
    var resultConstant=require("../constants/resultConstant.js");
    var ajaxUlit=require("../ulit/ajaxUlit.js");


    /**
     *  自定义添加分组函数
     */
    require.addGroups = function(){


    }

    /**
     * 自定义获取分组函数
     * @param contextId ：获取数据填充的容器
     * @param groupsId ：获取分组ID，进行获取分组对象
     */
    require.queryGroups = function(contextId,groupsId){
        //拼接参数
        var params  = "groupid="+groupsId;
        //用ajax进行调用接口
        $.post("../groups/queryGroups",params,function(resu){
            //判断返回是否出错
            if(resu != "10014"){  //参数为空的状态码
                //将返回结果进行填充
                $("#"+contextId).find("[name='name']").val(resu.name);
                $("#"+contextId).find("[name='groupid']").val(resu.name);
                $("#"+contextId).find("[name='type']").val(resu.name);
                $("#"+contextId).find("[name='trade']").val(resu.name);
                $("#"+contextId).find("[name='certificate']").val(resu.name);
                $("#"+contextId).find("[name='contacts']").val(resu.name);
                $("#"+contextId).find("[name='phone']").val(resu.name);
                $("#"+contextId).find("[name='address']").val(resu.name);
                $("#"+contextId).find("[name='describe']").val(resu.name);
                $("#"+contextId).find("[name='pgroupid']").val(resu.name);
            }else{   //参数为空
                BUI.Message.Alert('参数为空！','error');
            }
            //系统错误函数
        }).error(function (xhr, errorText, errorType) {
            BUI.Message.Alert('系统异常！','error');
        });;

    }



})






