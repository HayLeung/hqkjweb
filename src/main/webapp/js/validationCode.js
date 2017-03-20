
$(function(){

    getCookie();   //获取cookie进行填充
    //因为可能出现执行顺序不一样，导致session中的验证码无法得到
    //所以采用延迟0.1秒进行执行
    setTimeout("createCode()",100);//窗体加载调用，生成验证码
    //用户名失去焦点校验
    $("#userName").blur(function(){
        var username = $("#userName").val();  //获取文本框的值
        //判断用户名是否为空
        if(username == ""){
            $("#user_span").text("用户名不能为空！");
        }else{
            $("#user_span").text("");
        }
    });
    //密码失去焦点校验
    $("#password").blur(function(){
        var password = $("#password").val();  //获取文本框的值
        //判断密码是否为空
        if(password == ""){
            $("#pass_span").text("密码不能为空！");
        }else{
            $("#pass_span").text("");
        }
    });

});



function getCookie(){
    //请求后台，获取验证码
    $.post("user/getCookie","flag=1",function(data){
        //判断是否没有cookie
        if(data != "10005" ){
            var resu = JSON.parse(data);
            //判断是否有值
            if(resu.username=="" || resu.password == ""){
                $("input[id='userName']").val("");
                $("input[id='password']").val("");
                // $("#cookie_pass").prop("checked","false");
                document.getElementById("cookie_pass").checked=false;
            }else{
                $("input[id='userName']").val(resu.username);
                $("input[id='password']").val(resu.password);
                $("#cookie_pass").prop("checked","true");
            }
        }else{
            document.getElementById("cookie_pass").checked=false;
        }
    });
}




function createCode(){
    //得到验证码放置位置
    var checkCode = document.getElementById("code");
    //请求后台，获取验证码
    $.post("user/createCode","",function(data){
        checkCode.value = data;
    });
}  

//参数校验
function validate(){
    //用于判断
    var boo = true;
    //获取用户名、密码；
    var userName = $("input[id='userName']").val();
    var password = $("input[id='password']").val();
    //判断是否选择记住密码
    var $cookie_pass = $("#cookie_pass").is(":checked");
    //用于保存密码标识
    var pass = 2;  //默认不保存：1 - 保存  2 -不保存
    //判断是否勾选：保存密码
    if($cookie_pass){
        pass =1;
    }
    //用于保存提醒标识
    var info = 2;  //默认不保存：1 - 保存  2 -不保存
    var $cookies_info =$("#cookie_info").is(':checked');
    //判断是否勾选：保存密码
    if($cookies_info){
        info = 1;
    }
    //判断是否提醒
    var inputCode = document.getElementById("input").value.toUpperCase(); //获取输入的验证码
    //判断用户名是否为空
    if(userName == ""){
        $("#user_span").text("用户名不能为空！");
        boo = false;
    }else{
        $("#user_span").text("");
    }
    //判断密码是否为空
    if(password == ""){
        $("#pass_span").text("密码不能为空！");
        boo = false;
    }else{
        $("#pass_span").text("");
    }
    if(inputCode.length <= 0) { //未输入验证码
        alert("请输入验证码！"); //提示
        boo = false;
    }
    //判断是否能走下去
    if(boo){
        checkSubmit(userName,password,inputCode,pass,info);
    }
}

//提交
function checkSubmit(userName,password,code,pass,info){
    var url = "user/login";
    var data = "userName="+userName+"&"+"password="+password+"&code="+code+
            "&cookie_pass="+pass+"&cookie_info="+info;
    var resuData = "";  //接收返回数据
    //调函数
    $.post(url,data,function(resu){
        //判断是否登录成功
        if(resu == "10000"){
            alert("登录成功....");
            location.href="index.html"
        }else  if(resu == "10021"){
            alert("验证码错误！");
        }else  if(resu == "10009"){
            alert("用户名不存在！");
        }else  if(resu == "10014"){
            alert("用户名或密码错误！");
        }else{
            alert("网络异常，登录失败！");
        }
    });
}