//package com.javapai.framework.interceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
////import javax.servlet.http.HttpSession;
//
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
////import com.javapai.domain.security.UserLogin;
//
///**
// * 权限拦截器适配器.<br>
// * Authorization Interceptor Adapter. <br>
// * 
// * @author liu.xiang
// *
// */
//public class AuthInterceptor extends HandlerInterceptorAdapter {
//
//	@Override
//	public boolean preHandle(HttpServletRequest request,
//			HttpServletResponse response, Object handler) throws Exception {
//		// TODO Auto-generated method stub
//		System.out.println(">>>AuthInterceptor doing....");
////		HttpSession session = request.getSession();
////		UserLogin userLogin = (UserLogin) session.getAttribute("userLogin");
////		String requestUri = request.getRequestURI();
////		String contextPath = request.getContextPath();
////		String url = requestUri.substring(contextPath.length());
////		if(userLogin == null) {
////			//未登录，请登录
////		}
//
//		return super.preHandle(request, response, handler);
//	}
//
//	@Override
//	public void postHandle(HttpServletRequest request,
//			HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		// TODO Auto-generated method stub
//		super.postHandle(request, response, handler, modelAndView);
//	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest request,
//			HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		// TODO Auto-generated method stub
//		super.afterCompletion(request, response, handler, ex);
//	}
//
//	@Override
//	public void afterConcurrentHandlingStarted(HttpServletRequest request,
//			HttpServletResponse response, Object handler) throws Exception {
//		// TODO Auto-generated method stub
//		super.afterConcurrentHandlingStarted(request, response, handler);
//	}
//
//}
