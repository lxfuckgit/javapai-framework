package com.javapai.framework.common.aciton;

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;

//import javax.servlet.ServletInputStream;
//import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @deprecated SpringMVC后，系统可实现自动类型绑定，无需手工读取inputstream再转化为相应自动定对象.<br>
 * @author pooja
 *
 */
@Deprecated
public abstract class TopBaseAction {
	/**/
	private static Logger logger = LoggerFactory.getLogger(TopBaseAction.class);
	
	/**
	 * 读取Request对象中流数据，以String形式返回流数据.<br>
	 * 
	 * @param request
	 * @return JSON String
	 */
//	protected String getRequestString(HttpServletRequest request) {
//		BufferedReader in = null;
//		StringBuilder sb = new StringBuilder();
//		try {
//			request.setCharacterEncoding("UTF-8");
//			ServletInputStream sis = request.getInputStream();
//			in = new BufferedReader(new InputStreamReader(sis, "UTF-8"));
//			String line = null;
//			while ((line = in.readLine()) != null) {
//				sb.append(line);
//			}
//			sis.close();
//			in.close();
//			logger.info(">>>接收到Request请求参数:" + sb.toString());
//		} catch (IOException e) {
//			e.printStackTrace();
//			logger.info(">>>读取Request请求参数失败!");
//		} finally {
//			try {
//				in.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return sb.toString();
//	}

	/**
	 * 读取Request对象中流数据，以Json对象形式返回流数据.<br>
	 * 
	 * @param request
	 * @return JSON Object
	 */
//	protected JsonNode getRequestJson(HttpServletRequest request) {
//		try {
//			String jsonString = getRequestString(request);
//			ObjectMapper mapper = new ObjectMapper();
//			return mapper.readTree(jsonString);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//			return null;
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}

}
