define(function(require, exports,module) {
    var config=require("../config.js");
    var resultConstant=require("../constants/resultConstant.js");
    var ajaxUlit=require("../ulit/ajaxUlit.js");
    var path=config.path+"/role/";
    var gridId='grid';//bui表格的ID
    var formBtnId='searchForm';//form表单的ID
    exports.pageSize=10;
    /**
     * 删除角色新
     */
    exports.del= function (roleId) {
        BUI.Message.Confirm('确认要删除这行数据吗？', function () {
            setTimeout(function () {
                  var paths=path+"/roleDel.action"; //请求地址
                  var data='roleId='+roleId; //参数
                  ajaxUlit.publicAjax('del',data,paths); //执行请求
                  $('#'+formBtnId).submit();//刷新表格
            });

        }, 'question');
    }

    /**
     *删除选中的行
     * @param gridId  //表格ID
     * @param formBtnId  //from 表单ID
     */
    exports.delSelectAll=function(){
        var paths=path+"/roleDelAll.action"
        //批量删除选中行
        ajaxUlit.delSelectAll(gridId,paths,formBtnId);
    }
    /**
     * 根据角色ID查询
     * @param dogId  需要在哪个块级元素里面填充数据，这个是ID
     * @param roleId
     */
    exports.queryKey=function (dom,roleId) {
        var data="roleId="+roleId;
        $.post(path+"/queryKey.action", data, function (response) {
            switch (response.resuleCode.toString())
            {
                case resultConstant.SUCCESS:
                    var role=response.data;
                    var roleNameHtml=$(dom).find("[name='roleName']").val();
                    console.log("roleName"+roleNameHtml);
                 //   var roleNameHtml=$(doghtml).find("[name='roleName']");
                 //   $("#"+dogId).find("[name='roleName']").val(role.roleName);//填充name值
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
    /**
     * 角色管理编辑弹框
     * @param Grid
     * @param config
     * @returns {*}
     */
    exports.roleEdit=function (Grid) {

        var role_edit = new Grid.Plugins.DialogEditing({
            contentId : 'role_edit_content', //设置隐藏的Dialog内容
            triggerCls : 'role_edit', //触发显示Dialog的样式
            editor: {
                title: '编辑',
                success : function(){
                    var edtor = this,
                        form = edtor.get('form'),
                        editType = role_edit.get('editType'),//add 或者 edit
                        url =path+ '/roleUpdate.action';
                    /*if(editType == 'add'){ //可以根据编辑类型决定url
                     url = 'xxx';
                     }else{
                     url = 'xxxxx';
                     }*/
                    // url += '?saveType=' + editType;
                    var checkedNodes=edtor.get('srcNode');
                    //检验
                    form.valid();
                    if(form.isValid()){
                        form.ajaxSubmit({ //表单异步提交
                            url : url,
                            method : 'post', //更改为POST
                            success : function(data){
                                if(editType == 'add'){
                                    //do something
                                }else{
                                    //do other
                                }
                                //将a 改成 1 测试一下显示错误
                                if(data.hasError){ //返回的数据是 {hasError : fasle,error : 'xxxx',field : 'xxx'},也可以是任意格式的数据如 ： {success : false,'error' : 'xxxxx'}
                                    var field = data.field;
                                    form.getField(field).showErrors([data.error]); //也可以多个字段的错误信息 例如 errors : [{field : 'a',error: 'addd'},{field : 'a',error: 'addd'}]
                                }else{
                                    edtor.accept();
                                }

                            },
                            error : function(){
                                //do something
                            }
                        });
                    }
                },
            }
        });

        return role_edit;
    }

    /**
     * 绑定树节点
     */
    exports.loadTree=function () {
        BUI.use(['bui/tree','bui/data'],function (Tree,Data) {

            //一次性异步加载所有数据
            var store = new Data.TreeStore({
                url :config.path+ '/func/treeQuery.action', //返回的数据如果数据有children字段，且children.length == 0 ，则认为存在未加载的子节点
                //leaf = false，没有children字段也会认为子节点未加载，展开时会自动加载
                proxy : {
                    method : 'post',//post请求
                    dataType : 'json'
                },
                map : {
                    funcName : 'text',
                    funcId : 'id',
                    nodes : 'children'
                },
                autoLoad : true
            });

            var roleTreeEdit = new Tree.TreeList({
                render : '#roleTreeEdit',
                showLine : true,
                height:300,
                store : store
            });
            roleTreeEdit.render();


            var roleTreeAdd = new Tree.TreeList({
                render : '#roleTreeAdd',
                showLine : true,
                height:300,
                store : store
            });
            roleTreeAdd.render();

            roleTreeEdit.on('checkedchange',function(ev){
                var checkedNodes = roleTreeEdit.getCheckedNodes();//获取树全部节点
                var str = '';
                var roleFuncArrayName = $("#roleTreeEdit [name='roleFuncArray']");
                var roleFuncArrayValus=new Array();
                if (roleFuncArrayName.length<1)  //创建roleFuncArray隐藏框，用来form提交带参数
                {
                    $("#roleTreeEdit").append('<input type="hidden" name="roleFuncArray" >');
                    roleFuncArrayName = $("#roleTreeEdit [name='roleFuncArray']");
                }
                BUI.each(checkedNodes,function(node){//获取选中节点的ID
                    str += node.id + ',';
                });
              //  $('.log').text(str);
             ///   roleFuncArrayValus=config.splitNumArray(str,',');
                $(roleFuncArrayName).val(str);
                console.log(roleFuncArrayValus);
            });

            roleTreeAdd.on('checkedchange',function(ev){
                var checkedNodes = roleTreeAdd.getCheckedNodes();
                var str = '';
                BUI.each(checkedNodes,function(node){
                    str += node.id + ',';
                });
                console.log(str);
            });
        });
    }
});