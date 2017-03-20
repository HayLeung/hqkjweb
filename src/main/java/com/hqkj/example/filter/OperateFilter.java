/*
package com.hqkj.example.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hqkj.example.constants.ResultConstant;
import com.hqkj.example.constants.SessionKeyConstant;
import com.hqkj.example.entity.User;
import com.hqkj.example.util.LoggerDB;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;

//import com.ycxc.base.util.SessionUtil;

*/
/**
 * 操作过滤器，防止用户未登录进入后台</br>
 * 
 * @author 
 *//*

public class OperateFilter extends HttpServlet implements Filter
{

	//日志对象
	private static Logger logger = Logger.getLogger(OperateFilter.class);

	private static final long serialVersionUID = 7711738258783809653L;

	
	public void doFilter(final ServletRequest httpRequest, final ServletResponse httpResponse, final FilterChain filterChain)
			throws IOException, ServletException
	{
		logger.info("进入："+ LoggerDB.resuMethod(this));
		logger.info("===============================>>>在"+ LoggerDB.resuMethod(this)+"进行过滤<<<================================");

		final HttpServletRequest request = (HttpServletRequest) httpRequest;
		final HttpServletResponse response = (HttpServletResponse) httpResponse;
		// 取出用户：在静态变量中，判断是否为空；为空则表明没有登录过
		final User user = (User)request.getSession().getAttribute(SessionKeyConstant.ADMIN_USER);
		final String url = request.getRequestURI();

		logger.info("===============================>>>访问的URL："+url+"<<<================================");

		if (user == null)
		{
			// 判断获取的路径不为空且不是访问登录页面或执行登录操作时跳转
			if (url != null && !url.equals("") && (url.indexOf("index") < 0 &&
					url.indexOf("login") < 0  && url.indexOf("createCode") < 0
					&& url.indexOf("getCookie") < 0))
			{
				//返回未登录状态码
	            response.getWriter().print(ResultConstant.NOT_LOGIN_ERROR);
				logger.info("跳出："+ LoggerDB.resuMethod(this));
				return;
			}
		}
		//通过过滤链，将请求返回目标地址
		filterChain.doFilter(httpRequest, httpResponse);
		logger.info("跳出："+ LoggerDB.resuMethod(this));
		return;
	}

	*/
/**
	 * 初始化
	 * @param filterConfig ：过滤器配置
	 * @throws ServletException：声明servlet异常
	 *//*

	public void init(final FilterConfig filterConfig) throws ServletException
	{
		System.out.println("=====================================进入"+filterConfig.getFilterName()+"过滤器:进行初始化==========================================");
	}

}
*/
