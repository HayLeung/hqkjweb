package com.hqkj.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hqkj.example.constants.ResultConstant;
import com.hqkj.example.constants.SessionKeyConstant;
import com.hqkj.example.entity.User;
import com.hqkj.example.propertiesBean.Platform;
import com.hqkj.example.service.UserService;
import com.hqkj.example.util.LoggerDB;
import com.hqkj.example.util.MD5Check;
import com.hqkj.example.util.PageSumComp;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HayLeung on 2017/3/8.
 * 用户所有操作的工作单元
 */
@RestController
@RequestMapping("/user")
public class UserLoginController {

    //添加日志对象
    private Logger logger = Logger.getLogger(UserLoginController.class);

    @Resource
    private UserService userService;     //用户服务实现对象
    @Resource
    private Platform platform;   //配置文件对象

    /**
     * 生成验证码
     * @param request ：请求对象
     * @return
     */
    @PostMapping(value = "/createCode",produces = "text/application;charset=UTF-8")
    public String createCode(HttpServletRequest request){
        //用于接收验证码
        String  code = "";
        //验证码长度
        Integer codeLength = 4;
        //验证码随机人、内容
        Object[] arr = {0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',
                    'S','T','U','V','W','X','Y','Z'};
        //遍历随机
        for(int i = 0; i < codeLength; i++) {
            int index = (int)Math.floor(Math.random()*36);
            code += arr[index];
        }
        //将验证码存入session
        request.getSession().setAttribute("code",code);
        //将验证码返回
        return  code;
    }


    /**
     * 获取cookie
     * 新增cookie
     * 删除cookie
     * @param request ：请求对象
     * @param response ：响应对象
     * @return
     */
    @PostMapping(value = "/getCookie",produces = "text/application;charset=UTF-8")
    public String addCookie(HttpServletRequest request,HttpServletResponse response,
                            String userName,String password,Integer flag) {
        //如果参数为空，证明是获取cookie进行显示
        if (userName == null && password == null) {
            //获取cookie数组
            Cookie[] cookieArr = request.getCookies();
            //判断cookie数组是否为空
            if (cookieArr == null || cookieArr.length < 0) {
                //cookie中无值
                return ResultConstant.RESULT_NULL_ERROR;
            }
            String getCookieName = null;
            String getCookiePass = null;
            //遍历cookie数组
            for (Cookie cookie : cookieArr) {
                //获取cookies中的用户名
                if (cookie.getName().equals("userName")) {
                    getCookieName = cookie.getValue();
                }
                //获取cookies中的密码
                if (cookie.getName().equals("password")) {
                    getCookiePass = cookie.getValue();
                }
            }
             //判断cookie中的值是否为空
            if(getCookieName!= null  && !getCookieName.equals("")&&
                    getCookiePass!=null && !getCookiePass.equals("")){
                //创建JSON对象
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("username", getCookieName);
                jsonObject.put("password", getCookiePass);
                //将结果进行封装
                String resuMsg = jsonObject.toJSONString();
                //将数据返回
                return resuMsg;
            }else{
                //无返回结果
                return ResultConstant.RESULT_NULL_ERROR;
            }

            //如果参数有值，则证明是保存或删除
        } else{
            //创建cookie
            Cookie cookieName = new Cookie("userName", userName);
            //设置cookie的各种属性
            Cookie cookiePass = new Cookie("password", password);
            //判断是保存还是删除
            if (flag == 1) {  //保存
                cookiePass.setMaxAge(platform.getCookieDay()*(60*60*24));
                cookiePass.setPath("/");
                cookieName.setMaxAge(platform.getCookieDay()*(60*60*24));
                cookieName.setPath("/");
            } else {   //删除
                cookiePass.setMaxAge(0);
                cookiePass.setPath("/");
                cookieName.setMaxAge(0);
                cookieName.setPath("/");
            }
            //将cookies添加进去
            response.addCookie(cookieName);
            response.addCookie(cookiePass);
            //成功存入
            return ResultConstant.SUCCESS;
        }
    }



    /**
     * 用户进行登录
     * @param request ：请求对象
     * @return ：返回登录的状态码
     */
    @PostMapping(value = "/login",produces = "text/application;charset=UTF-8")
    public String userLogin(HttpServletRequest request,HttpServletResponse response,User param){
        logger.info("进入："+LoggerDB.resuMethod(this));
        //获取验证码
        String paramCode = request.getParameter("code");
        //获取session中的验证码
        String sessionCode = request.getSession().getAttribute("code").toString();
        //判断是否相同
        if (!paramCode.equals(sessionCode)){  //验证码错误
            return ResultConstant.resuInfo(ResultConstant.LOGIN_CODE,"验证码错误！");
        }
        //创建Token对象
        UsernamePasswordToken token = new UsernamePasswordToken(param.getUserName(), param.getPassword());
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        //异常处理
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            logger.info("对用户[" + param.getUserName() + "]进行登录验证..验证开始");
            currentUser.login(token);
            logger.info("对用户[" + param.getUserName() + "]进行登录验证..验证通过");
            //获取保存用户名密码标识
            String cookiePass = request.getParameter("cookie_pass");
            //判断是否记住
            if(cookiePass.equals("1")){
                //记住：调用函数 1 -保存
                addCookie(request,response, param.getUserName(),param.getPassword(),1);
            }else{
                //删除用户名、密码  2 -删除
                addCookie(request,response, param.getUserName(),param.getPassword(),2);
            }
            //获取保存提醒标识
            String cookieInfo = request.getParameter("cookie_info");
            //判断是否记住
            if(cookieInfo.equals("1")){
                //记住：调用函数
            }
            User users = (User) currentUser.getSession().getAttribute("user");
            //密码正确将用户存入session
            request.getSession().setAttribute(SessionKeyConstant.ADMIN_USER,currentUser.getSession().getAttribute("user"));
        }catch(UnknownAccountException uae){
            logger.info("对用户[" + param.getUserName() + "]进行登录验证..验证未通过,未知账户");
            return ResultConstant.resuInfo(ResultConstant.NO_EXIST_ERROR,"用户不存在！");
        }catch(IncorrectCredentialsException ice){
            logger.info("对用户[" + param.getUserName() + "]进行登录验证..验证未通过,错误的凭证");
            return ResultConstant.resuInfo(ResultConstant.USERNAME$PASSWORD_ERROR,"用户名或密码错误！");
        }
        /*catch(LockedAccountException lae){
            logger.info("对用户[" + param.getUserName() + "]进行登录验证..验证未通过,账户已锁定");
            return ResultConstant.ERROR;
        }catch(ExcessiveAttemptsException eae){
            logger.info("对用户[" + param.getUserName() + "]进行登录验证..验证未通过,错误次数过多");
            return ResultConstant.ERROR;
        }*/
        catch(AuthenticationException ae){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.info("对用户[" + param.getUserName() + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            return ResultConstant.resuInfo(ResultConstant.ERROR,"系统异常！");
        }
        //退出过滤器
//        currentUser.logout();
        logger.info("跳出："+LoggerDB.resuMethod(this));
        //返回成功登录的状态码
        return ResultConstant.SUCCESS;
    }


    /**
     * 用户修改密码
     * @param request ： 请求对象
     * @return ：返回修改密码的状态码
     */
    @PostMapping(value = "/modifyPass",produces = "text/application;charset=UTF-8")
    public String modifyPass(HttpServletRequest request){
        logger.info("进入："+LoggerDB.resuMethod(this));
        //获取原密码
        String oldPass = request.getParameter("oldPass");
        //获取登录的用户
        User user = (User)request.getSession().getAttribute(SessionKeyConstant.ADMIN_USER);
        //将输入的原密码进行对比
        if(!MD5Check.verify(oldPass, user.getPassword())){  //原密码错误
            logger.info("跳出："+LoggerDB.resuMethod(this));
            return ResultConstant.resuInfo(ResultConstant.PASSWORD_ERROR,"原密码错误！");
        }
        //获取新密码
        String newPass = request.getParameter("newPass");
        //获取确认密码
        String yesPass = request.getParameter("yesPass");
        //进行对比
        if(!yesPass.equals(newPass)){  //表示两次密码不一致（密码跟确定密码）
            logger.info("跳出："+LoggerDB.resuMethod(this));
            return ResultConstant.resuInfo(ResultConstant.MATCH_ERROR,"两次密码不一致！");
        }
        //将新密码进行加密
        newPass = MD5Check.generate(newPass);
        //将密码设置到对象中
        user.setPassword(newPass);
        //调用接口进行修改数据
        Integer count = userService.modifyPass(user);
        //判断是否修改成功
        if(count > 0 ){
            //修改成功后，将session中的对象进行更新
            request.getSession().setAttribute(SessionKeyConstant.ADMIN_USER,user);
            logger.info("跳出："+LoggerDB.resuMethod(this));
            return ResultConstant.resuInfo(ResultConstant.SUCCESS,"成功修改密码！");
        }
        logger.info("跳出："+LoggerDB.resuMethod(this));
        return ResultConstant.resuInfo(ResultConstant.ERROR,"修改密码失败！");
    }

    /**
     * 分页显示用户信息
     * @param request ：请求对象
     * @return ：返回用户列表
     */
    @PostMapping(value = "queryUserList",produces = "text/application;charsetUTF-8")
    public String queryUserList(HttpServletRequest request,Integer pageIndex,Integer limit){
        logger.info("进入："+LoggerDB.resuMethod(this));
        //获取当前页码
        String pageNumStr = request.getParameter("pageNum");
        //获取各种条件参数

        //创建参数集合
        Map<String,Object> params = new HashMap<>();
        //进行分页
        Page<User> page = PageHelper.startPage(pageIndex,limit);
        //调用接口
        List<User> list = userService.queryUserList(params);
        //获取总数量
        Integer total = (int)page.getTotal();
        Integer pageNum = page.getPageNum();
        //将数据进行封装
        String resuMsg = ResultConstant.convertJSON$ToString(list ,pageNum, total, null);
        logger.info("跳出："+LoggerDB.resuMethod(this));
        return resuMsg;
    }


    /**
     * 根据ID，获取单个用户信息
     * @param request ：请求对象
     * @return ：返回用户信息
     */
    @PostMapping(value = "/queryUserInfo",produces = "text/application;charset=UTF-8")
    public String queryUserInfo(HttpServletRequest request){
        logger.info("进入："+LoggerDB.resuMethod(this));
        //获取传入的用户ID参数
        String userId = request.getParameter("userId");
        //调用接口，获取用户信息
        User user = userService.queryUserInfo(Integer.valueOf(userId));
        //判断是否为空
        if(user == null){   //返回结果为空
            logger.info("跳出："+LoggerDB.resuMethod(this));
            return ResultConstant.resuInfo(ResultConstant.RESULT_NULL_ERROR,"不存在该用户！");
        }
        //调用函数，封装对象
        String resuMsg = ResultConstant.convertJSON$ToString(user);
        logger.info("跳出："+LoggerDB.resuMethod(this));
        return resuMsg;
    }

    /**
     * 用于操作用户信息：新增、修改、删除
     * @param request ：请求对象，各个参数从请求中获取
     * @param excuType  ：操作类型：1 -新增 || -修改 2 -删除
     * @return ：返回操作的状态码
     */
    @PostMapping(value = "AddOrEditOrDelUserInfo",produces = "text/application;charset=UTF-8")
    public String AddOrEditOrDelUserInfo(HttpServletRequest request,User user,
                                         @RequestParam(value = "userId",required = false)String userId,
                                         @RequestParam(value = "excuType",required = true)Integer excuType){
        logger.info("进入："+LoggerDB.resuMethod(this));
        //异常处理
        try{
            //调用接口
            String resuMsg = userService.AddOrEditOrDelUserInfo(user,userId, excuType);
            logger.info("跳出："+LoggerDB.resuMethod(this));
            return resuMsg;
        }catch (Exception e){   //出现异常
            logger.info("跳出："+LoggerDB.resuMethod(this));
            return ResultConstant.resuInfo(ResultConstant.EXCEPTION_ERROR,"系统异常！");
        }

    }





}
