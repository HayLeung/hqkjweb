
//高级查找  js
$(".super").click(function(){
		$(".sup_look").css({"display":"block"});
	});
	$(".close").click(function(){
		$(".sup_look").css({"display":"none"});
	});
//tab  js
$(function(){
	$(".zzsc .tab a").click(function(){
		$(this).addClass('on').siblings().removeClass('on');
		var index = $(this).index();
		number = index;
		$('.zzsc .content li').hide();
		$('.zzsc .content li:eq('+index+')').show();
	});
});
//列表高度
$(function(){ 

var browser=navigator.appName
var b_version=navigator.appVersion
var version=b_version.split(";");
var trim_Version=version[1].replace(/[ ]/g,"");
if(browser=="Microsoft Internet Explorer" && trim_Version=="MSIE8.0")
{
     var WH=$(window).height();
     $("#menu_zzjs_net").css("height", WH+105);
}
else
{
 var WH=$(window).height();
     $("#menu_zzjs_net").css("height", WH-45);
}
     });

//列表
 function addEvent(el,name,fn){//绑定事件
  if(el.addEventListener) return el.addEventListener(name,fn,false);
  return el.attachEvent('on'+name,fn);
 }
 function nextnode(node){//寻找下一个兄弟并剔除空的文本节点
  if(!node)return ;
  if(node.nodeType == 1)
   return node;
  if(node.nextSibling)
   return nextnode(node.nextSibling);
 }
 function prevnode(node){//寻找上一个兄弟并剔除空的文本节点
  if(!node)return ;
  if(node.nodeType == 1)
   return node;
  if(node.previousSibling)
   return prevnode(node.previousSibling);
 }
 function parcheck(self,checked){//递归寻找父亲元素，并找到input元素进行操作
  var par =  prevnode(self.parentNode.parentNode.parentNode.previousSibling),parspar;
  if(par&&par.getElementsByTagName('input')[0]){
   par.getElementsByTagName('input')[0].checked = checked;
   parcheck(par.getElementsByTagName('input')[0],sibcheck(par.getElementsByTagName('input')[0]));
  }
 }
 function sibcheck(self){//判断兄弟节点是否已经全部选中
  var sbi = self.parentNode.parentNode.parentNode.childNodes,n=0;
  for(var i=0;i<sbi.length;i++){
   if(sbi[i].nodeType != 1)//由于孩子结点中包括空的文本节点，所以这里累计长度的时候也要算上去
    n++;
   else if(sbi[i].getElementsByTagName('input')[0])// else if(sbi[i].getElementsByTagName('input')[0].checkbox
    n++;
  }
  return n==sbi.length?true:false;
 }
 addEvent(document.getElementById('menu_zzjs_net'),'click',function(e){//绑定input点击事件，使用menu_zzjs_net根元素代理
  e = e||window.event;
  var target = e.target||e.srcElement;
  var tp = nextnode(target.parentNode.nextSibling);
  switch(target.nodeName){
   case 'A'://点击A标签展开和收缩树形目录，并改变其样式会选中checkbox
    if(tp&&tp.nodeName == 'UL'){
     if(tp.style.display != 'block' ){
      tp.style.display = 'block';
      prevnode(target.parentNode.previousSibling).className = 'ren'
     }else{
      tp.style.display = 'none';
      prevnode(target.parentNode.previousSibling).className = 'add'
     }
    }
    break;
   case 'SPAN'://点击图标只展开或者收缩
    var ap = nextnode(nextnode(target.nextSibling).nextSibling);
    if(ap.style.display != 'block' ){
     ap.style.display = 'block';
     target.className = 'ren'
    }else{
     ap.style.display = 'none';
     target.className = 'add'
    }
    break;
   case 'INPUT'://点击checkbox，父亲元素选中，则孩子节点中的checkbox也同时选中，孩子结点取消父元素随之取消
    if(target.checked){
     if(tp){
      var checkbox = tp.getElementsByTagName('input');
      for(var i=0;i<checkbox.length;i++)
       //checkbox[i].checked = true;
	   $(checkbox[i]).attr('checked', 'checked')
     }
    }else{
     if(tp){
      var checkbox = tp.getElementsByTagName('input');
      for(var i=0;i<checkbox.length;i++)
       //checkbox[i].checked = false;
	   $(checkbox[i]).attr('checked', '')
     }
    }
    parcheck(target,sibcheck(target));//当孩子结点取消选中的时候调用该方法递归其父节点的checkbox逐一取消选中
    break;
  }
 });
 window.onload = function(){//页面加载时给有孩子结点的元素动态添加图标
  var labels = document.getElementById('menu_zzjs_net').getElementsByTagName('label');
  for(var i=0;i<labels.length;i++){
   var span = document.createElement('span');
   span.style.cssText ='display:inline-block;height:16px;vertical-align:middle;width:20px;cursor:pointer;';
   span.innerHTML = ' '
   span.className = 'add';
   if(nextnode(labels[i].nextSibling)&&nextnode(labels[i].nextSibling).nodeName == 'UL')
    labels[i].parentNode.insertBefore(span,labels[i]);
   else
    labels[i].className = 'rem'
  }
 }
//右键
var kyoPopupMenu={};
	kyoPopupMenu = (function(){
    return {
        sys: function (obj) {

			$('.popup_menu').remove();
            popupMenuApp = $('<div class="popup_menu app-menu"><ul><li><a menu="menu1">点名</a></li><li><a menu="menu2">追踪</a></li><li><a menu="menu3">立即拍照</a></li><li><a menu="menu4">监听</a></li><li><a menu="menu5">对讲</a></li><li><a menu="menu6">电话回拨</a></li><li><a menu="menu7">实时视频</a></li><li><a menu="menu8">报警参数设置</a></li><li><a menu="menu9">常用参数设置</a></li><li><a menu="menu10">服务器参数设置</a></li><li><a menu="menu11">下发电子围栏</a></li><li><a menu="menu12">文本信息下发</a></li><li><a menu="menu13">行驶记录仪</a></li><li><a menu="menu14">轨迹回放</a></li><li><a menu="menu15">历史图片查询</a></li><li><a menu="menu16">历史视频回放</a></li><li><a menu="menu17">查看车辆详细信息</a></li></ul></div>')
			.find('a').attr('href','javascript:;')
			.end().appendTo('body');
			//绑定事件
			$('.app-menu a[menu="menu1"]').on('click', function (){
				window.location.href="###";
				
				if(!$(obj).is(":checked"))
				$(obj).click();
				$(obj).attr('checked', 'checked');	
            });
			$('.app-menu a[menu="menu2"]').on('click', function (){
			$(obj).click();
				window.location.href="###";
				
				if(!$(obj).is(":checked"))
				$(obj).click();
				$(obj).attr('checked', 'checked');	
            });
			$('.app-menu a[menu="menu3"]').on('click', function (){
				if(!$(obj).is(":checked"))
				$(obj).click();
				$(obj).attr('checked', 'checked');	
				 window.location.href="###";
            });
			$('.app-menu a[menu="menu5"]').on('click', function (){
				if(!$(obj).is(":checked"))
				$(obj).click();
				$(obj).attr('checked', 'checked');		
				window.location.href="###";
            });
			$('.app-menu a[menu="menu6"]').on('click', function (){
				if(!$(obj).is(":checked"))
				$(obj).click();
				$(obj).attr('checked', 'checked');	
				window.location.href="###";
            });
			$('.app-menu a[menu="menu7"]').on('click', function (){
				if(!$(obj).is(":checked"))
				$(obj).click();
				$(obj).attr('checked', 'checked');	
				 window.location.href="###";
            });
			$('.app-menu a[menu="menu8"]').on('click', function (){
				if(!$(obj).is(":checked"))
				$(obj).click();
				$(obj).attr('checked', 'checked');	
				window.location.href="###";
            });
			$('.app-menu a[menu="menu9"]').on('click', function (){
				if(!$(obj).is(":checked"))
				$(obj).click();
				$(obj).attr('checked', 'checked');	
				window.location.href="###";
            });
			$('.app-menu a[menu="menu10"]').on('click', function (){
				if(!$(obj).is(":checked"))
				$(obj).click();
				$(obj).attr('checked', 'checked');	
				 window.location.href="###";
            });
			$('.app-menu a[menu="menu11"]').on('click', function (){
				if(!$(obj).is(":checked"))
				$(obj).click();
				$(obj).attr('checked', 'checked');	
				window.location.href="###";
            });
			$('.app-menu a[menu="menu12"]').on('click', function (){
				if(!$(obj).is(":checked"))
				$(obj).click();
				$(obj).attr('checked', 'checked');	
				window.location.href="###";
            });
			$('.app-menu a[menu="menu13"]').on('click', function (){
				if(!$(obj).is(":checked"))
				$(obj).click();
				$(obj).attr('checked', 'checked');	
				 window.location.href="###";
            });
			$('.app-menu a[menu="menu14"]').on('click', function (){
				if(!$(obj).is(":checked"))
				$(obj).click();
				$(obj).attr('checked', 'checked');	
				window.location.href="###";
            });
			$('.app-menu a[menu="menu15"]').on('click', function (){
				if(!$(obj).is(":checked"))
				$(obj).click();
				$(obj).attr('checked', 'checked');	
				window.location.href="###";
            });
			$('.app-menu a[menu="menu16"]').on('click', function (){
				if(!$(obj).is(":checked"))
				$(obj).click();
				$(obj).attr('checked', 'checked');	
				 window.location.href="###";
            });
			return popupMenuApp;
		}
	}})();
	 $(document).bind("contextmenu",function(e){
        return false;
    });
	//取消右键
	$("#menu_zzjs_net").on('mousedown', function (){return false;}).click(function(){
		$('.popup_menu').hide();
	});
	//label点击右击
    $("label").on('mousedown',function (e){
		if(3==e.which){
			var popupmenu = kyoPopupMenu.sys($(this).find("input").first());
			l = ($(document).width() - e.clientX) < popupmenu.width() ? (e.clientX - popupmenu.width()) : e.clientX;
			t = ($(document).height() - e.clientY) < popupmenu.height() ? (e.clientY - popupmenu.height()) : e.clientY;
			popupmenu.css({right:0,top: t}).show();
			return false;
		}
    });
    $('.popup_menu').remove();
