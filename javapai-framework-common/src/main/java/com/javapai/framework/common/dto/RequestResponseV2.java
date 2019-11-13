package com.javapai.framework.common.dto;
//package com.javapai.framework.action;
//
//import java.io.IOException;
//
////import javax.validation.constraints.NotNull;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
///**
// * JSON请求与响应对象.<br>
// * 
// * 主要是对v1改进，取消了RequestHead对象,将RequestHead对象的内容平行于requestArgs属性。
// * 
// * <br>
// * <p>
// * 
// * 请求标准json格式(V2)：
// * {
// *   "comeFrom": 1,
// *   "token": 1,
// *   "sign": "6cd7a0cec3ba9bbab2f95a4570aa54a5",
// *   "args": {
// *       "user": {
// *           "userId": 10006,
// *           "token": 18516632554
// *       },
// *       "bill": {
// *       	"billType":1,
// *       	"billNo":40200051,
// *       	"issueDate":"2015-08-21",
// *       	"dueDate":"2016-08-21",
// *       	"payerCompany":"浙江联谊建筑工程有限公司",
// *       	"payerBank":"萧山农商银行城厢支行",
// *       	"payeeCompany":"杭州前程混粘土有限公司",
// *       	"payeeBank":"萧山农商银行城厢支行"
// *       }
// *   }
// *}
// * 
// * 
// * @author liu.xiang
// *
// */
//public final class RequestResponseV2 {
//	/**
//	 * 错误标识.
//	 */
//	public static final String Error = "9999";
//	
//	/**
//	 * 正确标识.
//	 */
//	public static final String Success = "0000";
//
//	/**
//	 * 
//	 */
//	private static final ObjectMapper mapper = new ObjectMapper();
//	
//	/**
//	 * 应用ID标识.
//	 */
//	String appId;
//	/**
//	 * 请求令牌.
//	 */
////	@NotNull
//	String tokenId;
//	/**
//	 * 请求签名. <br>
//	 * 在安全要求高的情况下，利用证书对整个"请求参数"的加密签名。
//	 */
//	String requestSign = null;
//	/**
//	 * 请求版本.
//	 */
//	String version;
//	/**
//	 * 请求时间戳.
//	 */
//	String timeTx;
//	/**
//	 * 请求参数.
//	 */
//	private String requestArgs = null;
//
//	/**
//	 * 响应头
//	 */
//	private ResponseHead responseHead = null;
//
//	public String getAppId() {
//		return appId;
//	}
//
//	public void setAppId(String appId) {
//		this.appId = appId;
//	}
//
//	public String getTokenId() {
//		return tokenId;
//	}
//
//	public void setTokenId(String tokenId) {
//		this.tokenId = tokenId;
//	}
//	/**
//	 * 读取请求参数.<br>
//	 * 
//	 * @return
//	 */
//	public String getRequestArgs() {
//		return requestArgs;
//	}
//	
//	/**
//	 * 读取请求参数中指定key的value值.<br>
//	 * 
//	 * @param argsKey requestArgs中的key.
//	 * @return
//	 */
//	public String getRequestArgsValue(String argsKey) {
//		try {
//			JsonNode json = mapper.readTree(requestArgs);
//			return json.path(argsKey).toString();
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//			return null;
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	/**
//	 * 设置请求参数.<br>
//	 * @param requestArgs
//	 */
//	public void setRequestArgs(String requestArgs) {
//		this.requestArgs = requestArgs;
//	}
//
//	public String getVersion() {
//		return version;
//	}
//	
//	public void setVersion(String version) {
//		this.version = version;
//	}
//	
//	public String getTimeTx() {
//		return timeTx;
//	}
//	
//	public void setTimeTx(String timeTx) {
//		this.timeTx = timeTx;
//	}
//	
//	/**
//	 * getResponseHeadJson=getResponseHead;<br>
//	 * 同时getResponseHeadJson也被getResponseHead(String code,String msg)方法取代。<br>
//	 * <br>
//	 * getResponseHead与setResponseHead因为ResponseHead的私有性(非public)，返回给客户没办法使用，
//	 * 所以get与set ResponseHead 也变得没有意义，于是被设置为过期。 <br>
//	 * <br>
//	 * 但有一个问题，客户没有办法得到ResponseHead.data属性的数据。
//	 * 
//	 * @return
//	 * @throws JsonProcessingException
//	 */
//	@Deprecated
//	public String getResponseHeadJson() throws JsonProcessingException {
//		return mapper.writeValueAsString(responseHead);
//	}
//
//	@Deprecated
//	public ResponseHead getResponseHead() {
//		return responseHead;
//	}
//	
//	@Deprecated
//	public void setResponseHead(ResponseHead responseHead) {
//		this.responseHead = responseHead;
//	}
//
//	/**
//	 * 返回一个成功的响应头.<br>
//	 * 
//	 * @return
//	 */
////	public ResponseHead getSuccessResponseHead() {
//	public String getSuccessResponseHead() {
//		ResponseHead response = new ResponseHead();
//		response.setCode(Success);
////		response.setMsg("操作成功!");
////		return response;
//		try {
//			return mapper.writeValueAsString(responseHead);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	/**
//	 * 返回一个失败响应头.<br>
//	 * 
//	 * @return
//	 */
////	public ResponseHead getErrorResponseHead() {
//	public String getErrorResponseHead() {
//		ResponseHead response = new ResponseHead();
//		response.setCode(Error);
////		response.setMsg("操作失败!");
////		return response;
//		try {
//			return mapper.writeValueAsString(response);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	/**
//	 * 返回一个自定义响应头.<br>
//	 * @return
//	 */
////	public ResponseHead getResponseHead(String code,String msg) {
//	public String getResponseHead(String code,String msg) {
//		ResponseHead response = new ResponseHead();
//		response.setCode(code);
////		response.setMsg(msg);
////		return response;
//		try {
//			return mapper.writeValueAsString(response);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//}
