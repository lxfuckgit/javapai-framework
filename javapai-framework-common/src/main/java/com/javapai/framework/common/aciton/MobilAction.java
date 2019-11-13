package com.javapai.framework.common.aciton;
//package com.javapai.framework.common.action;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.fasterxml.jackson.databind.JsonNode;
////import com.javapai.framework.action.RequestHead;
////import com.javapai.common.TopBaseAction;
////import com.javapai.web.action.RequestHead;
////import com.javapai.web.action.RequestResponse;
//import com.javapai.framework.action.RequestResponse;
//
///**
// * 移动端请求处理器.<br>
// * <p>
// * @author liu.xiang
// *
// */
//public class MobilAction extends TopBaseAction {
//
//	/**/
//	private static Logger logger = LoggerFactory.getLogger(MobilAction.class);
//	
//	/**
//	 * 解析请求参数. <br>
//	 * 
//	 * @param request
//	 * @return
//	 */
//	// @ModelAttribute
//	public RequestResponse initReqeust(HttpServletRequest request) {
//		JsonNode json = super.getRequestJson(request);
//		
//		RequestResponse rr = new RequestResponse();
////		ResponseHead rh = new ResponseHead("9000", "成功");
//		
////		RequestHead requestH = new RequestHead();
////		JsonNode jsonHead = json.get("head");
////		requestH.setAppId(jsonHead.path("appId").asText());
////		requestH.setVersion(jsonHead.path("version").asText());
////		requestH.setTokenId(jsonHead.path("tokenId").asText());
////		requestH.setTimeTx(jsonHead.path("TimeTx").asText());
////		rr.setRequestHead(requestH);
//		
////		rr.setRequestArgs(json.path("args").asText());
//		rr.setRequestArgs(json.path("args").toString());
////		rr.setResponseHead(rh);
//		
//		/*验证token有效性*/
//		HttpSession session = request.getSession();
//		System.out.println(">>>"+session.getId());
//		session.getId();
//		
//
////		rr.setResponseHead(rh);
//		
//		logger.info(">>>request head : " + json.get("head"));
//		logger.info(">>>request args : " + rr.getRequestArgs());
//		
//		return rr;
//	};
//	
//	/**
//	 * 
//	 * @param request
//	 * @param checkLogin
//	 * @return
//	 */
//	public RequestResponse initReqeust(HttpServletRequest request,boolean checkLogin) {
//		RequestResponse rr = initReqeust(request);
//		return rr;
//	}
//	
//
//}
