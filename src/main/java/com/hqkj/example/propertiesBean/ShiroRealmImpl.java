
package com.hqkj.example.propertiesBean;

import com.hqkj.example.entity.User;
import com.hqkj.example.service.UserService;
import com.hqkj.example.util.MD5Check;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;


/**
 * Created by Administrator on 2017/3/14.
 */

@Component("shiroRealmImpl")
public class ShiroRealmImpl extends AuthorizingRealm {


    //日志对象
    private Logger logger = Logger.getLogger(ShiroRealmImpl.class);
    @Resource
    private UserService userService;    //用户实现对象



    /**
     * 权限认证
     * @param principalCollection
     * @return
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("##################执行Shiro权限认证##################");
        //获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getName()).iterator().next();
        String loginName = (String)super.getAvailablePrincipal(principalCollection);
        // 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        //创建对象
        User obj = new User();
        obj.setUserName(loginName);
        //查出是否有此用户
        User user=userService.queryUserByName(obj);
        if(user!=null){
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
            //用户的角色集合
            info.addRole("admin");
            info.addStringPermission("groups:operationGroups");
            info.addStringPermission("groups:queryGroupsList");
            info.addStringPermission("groups:queryGroup");
            //用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的四行可以不要
            // 或者按下面这样添加
            //添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
//            simpleAuthorInfo.addRole("admin");
            //添加权限
//            simpleAuthorInfo.addStringPermission("admin:manage");
//            logger.info("已为用户[mike]赋予了[admin]角色和[admin:manage]权限");
            return info;
        }
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }


    /**
     * 验证当前用户是否登录成功
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;

        logger.info("验证当前Subject时获取到token为：" +
                ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        //创建对象
        User obj = new User();
        obj.setUserName(token.getUsername());
        //查出是否有此用户
        User user=userService.queryUserByName(obj);
        if(user!=null){   //自定义进行校验
            //设置加盐，以用户编号加盐，UserID最好以UUID，保证username可改且每个盐值都唯一
            boolean boo = MD5Check.verify(new String(token.getPassword()),user.getPassword());
            //判断是否密码正确
            if(boo){
                //密码正确，返回凭证
                return new SimpleAuthenticationInfo(user.getUserName(), token.getCredentials(), getName());
            }
            //密码错误，抛出异常
            throw new IncorrectCredentialsException();
        }
        return null;
    }
}
