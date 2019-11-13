package com.javapai.framework.common.aciton;
//package com.javapai.framework.common.action;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
////import com.javapai.common.TopBaseAction;
//
///**
// * WebPc端公共Controller<br>
// * 
// * <p>
// * 带有系统公共service.
// * 
// * @author lx
// * 
// * @date 2011-10-24 下午03:29:32
// */
//public abstract class WebAction extends TopBaseAction {
//	public static final String BASE_PATH = "/";
//	public static final String COMMON_FAIL_PAGE = "fail";
//	public static final String COMMON_FAIL_ALERT_KEY = "fail_key";
//	public static final String USER_LOGIN_SESSION = "user_login_session_key";
//
//	// 直接访问jsp
//	@RequestMapping("/{jsp}.htm")
//	public String jsp(@PathVariable(value = "jsp") String jsp) {
//		System.out.println(jsp);
//
//		return jsp;
//	}
//
//	/**
//	 * 
//	 * @param response
//	 * @param message
//	 */
//	protected void writeMessage(HttpServletResponse response, String message) {
//		writeMessage(response, message, "utf-8");
//	}
//
//	/**
//	 * 
//	 * @param response
//	 * @param message
//	 * @param charset
//	 */
//	protected void writeMessage(HttpServletResponse response, String message, String charset) {
//		response.setContentType("text/html;charset=" + charset);
//		response.setDateHeader("Expires", -10L);
//		try {
//			PrintWriter out = response.getWriter();
//			out.println(message);
//			out.flush();
//			out.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 
//	 * @param response
//	 * @param message
//	 */
//	protected void writeJsMessage(HttpServletResponse response, String message) {
//		writeMessage(response, "<script language='javascript'>" + message
//				+ "</script>", "utf-8");
//	}
//
//}
