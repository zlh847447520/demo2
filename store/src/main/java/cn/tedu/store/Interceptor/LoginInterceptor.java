package cn.tedu.store.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

//	快捷键生成shift+alt+s     选择override
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 从request中获取session
		HttpSession session = request.getSession();
		//判断Session中是否包含登录信息
		if(session.getAttribute("uid")==null) {
			//重定向到登录页面
			response.sendRedirect("/web/login.html");
			//阻止继续运行
			return false;
		}
		//有登录信息,则放行
		return true;
	}
	
}
