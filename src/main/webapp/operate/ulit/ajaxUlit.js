define(function(require, exports) {
    var resultConstant=require("../constants/resultConstant.js")
    /**
     * 批量删除根据ID
     * @param gridId grid表单ID
     * @param path 请求路径
     * @param formBtnId 查询
     */
    exports.delSelectAll = function(gridId,path,formBtnId)
    {
        BUI.Message.Confirm('确认要删除选中的数据吗？', function () {
            setTimeout(function () {
                var delArray =new Array();
                //遍历选中的行，并且获取隐藏的ID
                 $('#'+gridId+' .bui-grid-row-selected').each(function(){
                     var id=parseInt($(this).find("#id").val());
                     delArray.push(id);
                });
                 //数据转换
                 var data={delArray:delArray.toString()};
                //执行调用
                ajaxs('del',data,path);
                //刷新页面数据
                $('#'+formBtnId).submit();
            });

        }, 'question');
    };

    /**
     *外部调用
     * @param type add,del,upd
     * @param data 请求数据
     * @param path 路径
     */
    exports.publicAjax=function(type,data,path)
    {
        ajaxs(type,data,path);
    }

    //内部调用
    function ajaxs(type,data,path)
    {
        var alertStr="";
        switch (type){
            case  'add':
                alertStr="新增"
                break;
            case  'del':
                alertStr="删除"
                break;
            case  'upd':
                alertStr="修改"
                break;
            default:
                BUI.Message.Alert('调用异常!','error');
                return false;
                break;
        }
        $.post(path, data, function (response) {
            switch (response.resuleCode.toString())
            {
                case resultConstant.SUCCESS:
                    BUI.Message.Show({
                        msg : alertStr+'成功!',
                        icon : 'info',
                        buttons : [],
                        autoHide : true,
                        autoHideDelay : 1000
                    });

                    break;
                case resultConstant.ERROR:
                    BUI.Message.Alert(alertStr+'失败!','error');
                    break;
                case resultConstant.EXCEPTION_ERROR:
                    BUI.Message.Alert('系统异常'+alertStr+'失败!','error');
                    break;
            }
        }).error(function (xhr, errorText, errorType) {
            BUI.Message.Alert('系统异常'+alertStr+'失败','error');
        });
    }
});