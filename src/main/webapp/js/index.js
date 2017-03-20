$(function(){
	//右侧操作面板显示隐藏
    $(".dl-second-slib-con2").click(function(){
		$(".dl-inner-tab").css({"margin-right":"280"+"px"});
		$(".target").css({"width":"280"+"px"});
		$(".dl-second-slib-con2").css({"display":"none"});
		$(".dl-second-slib-con3").css({"display":"block"});
	});
	$(".dl-second-slib-con3").click(function(){
		$(".dl-inner-tab").css({"margin-right":"10"+"px"});
		$(".target").css({"width":"10"+"px"});
		$(".dl-second-slib-con2").css({"display":"block"});
		$(".dl-second-slib-con3").css({"display":"none"});
	});
	//右侧操作面板赋值高度
	$(function(){ 
         var WH=$(window).height();
         $(".target").css("height", WH - 20-70);
     });
	 //点击首页tab隐藏右侧操作面板
    $("#J_Nav li").click(function(){
		
		$(".dl-second-slib-con2").click();
		});
	 //点击目标监控tab隐藏右侧操作面板
	$("#home").click(function(){
		$(".dl-second-slib-con3").click();

		});
	$("#yunying").click(function(){
		$(".dl-second-slib-con3").click();

		});
});