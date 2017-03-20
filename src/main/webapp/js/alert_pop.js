//弹出窗口  js
$(document).ready(function(){
	BUI.use(['bui/overlay','bui/form'],function(Overlay,Form){
		var form = new Form.Form({
		   srcNode : '#form'
		}).render();
		
		//公共1050px  开始
		var edit_dialog = new Overlay.Dialog({
			title:'编辑',
			width:1050,
			contentId:'edit_content',
		});
		$('.edit').on('click',function () {
		  var sender = $(this),
			type = sender.text(),
			effect = {
			  effect : type,
			  duration : 400// 
			  //callback : function(){} ,callback 回调函数
			};
		  edit_dialog.set('effect',effect);
		  edit_dialog.show();
		});
		
		var new_dialog = new Overlay.Dialog({
			title:'添加',
			width:1050,
			contentId:'new_content',
		});
		$('.new').on('click',function () {
		  var sender = $(this),
			type = sender.text(),
			effect = {
			  effect : type,
			  duration : 400// 
			  //callback : function(){} ,callback 回调函数
			};
		  new_dialog.set('effect',effect);
		  new_dialog.show();
		});
		
		
		//公共720px  开始
		var eq_edit_dialog = new Overlay.Dialog({
			title:'编辑',
			width:720,
			contentId:'eq_edit_content',
		});
		var eq_new_dialog = new Overlay.Dialog({
			title:'添加',
			width:720,
			contentId:'eq_new_content',
		});
		var eqmg_edit_dialog = new Overlay.Dialog({
			title:'编辑',
			width:720,
			contentId:'eqmg_edit_content',
		});
		var eqmg_new_dialog = new Overlay.Dialog({
			title:'添加',
			width:720,
			contentId:'eqmg_new_content',
		});
		$('.eq_edit').on('click',function () {
		  var sender = $(this),
			type = sender.text(),
			effect = {
			  effect : type,
			  duration : 400// 
			  //callback : function(){} ,callback 回调函数
			};
		  eq_edit_dialog.set('effect',effect);
		  eq_edit_dialog.show();
		});
		
		$('.eq_new').on('click',function () {
		  var sender = $(this),
			type = sender.text(),
			effect = {
			  effect : type,
			  duration : 400// 
			  //callback : function(){} ,callback 回调函数
			};
		  eq_new_dialog.set('effect',effect);
		  eq_new_dialog.show();
		});
		
		$('.eqmg_edit').on('click',function () {
		  var sender = $(this),
			type = sender.text(),
			effect = {
			  effect : type,
			  duration : 400// 
			  //callback : function(){} ,callback 回调函数
			};
		  eqmg_edit_dialog.set('effect',effect);
		  eqmg_edit_dialog.show();
		});
		
		$('.eqmg_new').on('click',function () {
		  var sender = $(this),
			type = sender.text(),
			effect = {
			  effect : type,
			  duration : 400// 
			  //callback : function(){} ,callback 回调函数
			};
		  eqmg_new_dialog.set('effect',effect);
		  eqmg_new_dialog.show();
		});
		//设备管理  结束
		
		
		//用户管理  开始
		var form = new Form.Form({
		   srcNode : '#form'
		}).render();
		var role_edit_dialog = new Overlay.Dialog({
			title:'编辑',
			width:720,
			contentId:'role_edit_content',
		});
		var role_new_dialog = new Overlay.Dialog({
			title:'添加',
			width:720,
			contentId:'role_new_content',
		});
		var user_edit_dialog = new Overlay.Dialog({
			title:'编辑',
			width:720,
			contentId:'user_edit_content',
		});
		var user_new_dialog = new Overlay.Dialog({
			title:'添加',
			width:720,
			contentId:'user_new_content',
		});
		$('.role_edit').on('click',function () {
		  var sender = $(this),
			type = sender.text(),
			effect = {
			  effect : type,
			  duration : 400// 
			  //callback : function(){} ,callback 回调函数
			};
		  role_edit_dialog.set('effect',effect);
		  role_edit_dialog.show();
		});
		
		$('.role_new').on('click',function () {
		  var sender = $(this),
			type = sender.text(),
			effect = {
			  effect : type,
			  duration : 400// 
			  //callback : function(){} ,callback 回调函数
			};
		  role_new_dialog.set('effect',effect);
		  role_new_dialog.show();
		});
		
		$('.user_edit').on('click',function () {
		  var sender = $(this),
			type = sender.text(),
			effect = {
			  effect : type,
			  duration : 400// 
			  //callback : function(){} ,callback 回调函数
			};
		  user_edit_dialog.set('effect',effect);
		  user_edit_dialog.show();
		});
		$('.user_new').on('click',function () {
		  var sender = $(this),
			type = sender.text(),
			effect = {
			  effect : type,
			  duration : 400// 
			  //callback : function(){} ,callback 回调函数
			};
		  user_new_dialog.set('effect',effect);
		  user_new_dialog.show();
		});
		//用户管理  结束
     
        //设置  报警参数  开始
		var form = new Form.Form({
          srcNode : '#form'
        }).render();
        var dialog = new Overlay.Dialog({
            title:'报警选项',
            width:700,
            contentId:'content',
          });
        //dialog.show();
        $('#btnGroup .button').on('click',function () {
          var sender = $(this),
            type = sender.text(),
            effect = {
              effect : type,
              duration : 400// 
              //callback : function(){} ,callback 回调函数
            };
 
          dialog.set('effect',effect);
          dialog.show();
 
        });
        //设置  报警参数   结束
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	});

});