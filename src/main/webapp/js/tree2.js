//tree js
$(document).ready(function(){
    BUI.use(['bui/tree','bui/data','../operate/config.js'],function (Tree,Data,config) {

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
            var checkedNodes = roleTreeEdit.getCheckedNodes();
            var str = '';
            BUI.each(checkedNodes,function(node){
                str += node.id + ',';
            });
            console.log(str);
        //    $('.log').text(str);
        });

    });
  
});
