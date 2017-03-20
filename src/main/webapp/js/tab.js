//tab  js
$(function(){
	$(".zzsc .tab a").click(function(){
		$(this).addClass('on').siblings().removeClass('on');
		var index = $(this).index();
		number = index;
		$('.zzsc .content2 li').hide();
		$('.zzsc .content2 li:eq('+index+')').show();
	});
});
$(function(){
	$(".zzsc2 .tab a").click(function(){
		$(this).addClass('on').siblings().removeClass('on');
		var index = $(this).index();
		number = index;
		$('.zzsc2 .content2 li').hide();
		$('.zzsc2 .content2 li:eq('+index+')').show();
	});
});


//<div class="zzsc">
//  <div class="tab"> 
//    <a href="javascript:;" class="on">车辆信息</a> 
//    <a href="javascript:;">安装信息</a> 
//    <a href="javascript:;">客户信息</a> 
//  </div>
//  <div class="content2">
//    <ul>
//      <li style="display:block;">
//      1
//      </li>
//      <li>
//      2
//      </li>
//      <li>
//      3
//      </li>
//    </ul>
//  </div>
//</div>



//<div class="zzsc2">
//  <div class="tab"> 
//    <a href="javascript:;" class="on">车辆信息</a> 
//    <a href="javascript:;">安装信息</a> 
//    <a href="javascript:;">客户信息</a> 
//  </div>
//  <div class="content2">
//    <ul>
//      <li style="display:block;">
//      
//      </li>
//      <li>
//      
//      </li>
//      <li>
//      
//      </li>
//    </ul>
//  </div>
//</div>