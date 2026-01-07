package com.javapai.framework.wrapper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.javapai.framework.utils.UtilJson;

/**
 * 带Token令牌请求包装器。<br>
 * 
 * <strong>提示：</strong>将当前请求头的令牌（token）参数对应的用户标识（userId）参数包装到该请求的Body报文体中。
 * 
 * 实现参考：https://zhuanlan.zhihu.com/p/338394865
 * 
 * @author pooja
 *
 */
public class XTokenRequestWrapper extends HttpServletRequestWrapper {
	private Long userId;
	private String requestIp;
	private final byte[] requestBody;

	/**
	 * 
	 * @param request
	 * @param userId
	 * @param requestIp
	 */
	public XTokenRequestWrapper(HttpServletRequest request, Long userId, String requestIp) {
		super(request);
		this.userId = userId;
		this.requestIp = requestIp;
		this.requestBody = wrapperRequestParam(request).getBytes();
	}

	/**
	 * 包装请求参数。<br>
	 * 
	 * @param request
	 * @return
	 */
	private String wrapperRequestParam(ServletRequest request) {
		/* 1、read request body string */
		StringBuilder sb = new StringBuilder();
		InputStream stream = null;
		BufferedReader reader = null;
		try {
			stream = request.getInputStream();
			reader = new BufferedReader(new InputStreamReader(stream, Charset.forName("UTF-8")));
			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			stream.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != stream) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		/* 2、wrapper request body string */
		ObjectNode json = UtilJson.json2Object(sb.toString(), ObjectNode.class);
		if (null == json) {
			json = new ObjectNode(null);
		} else {
			json.put("userId", String.valueOf(userId));
			json.put("requestIp", requestIp);
		}

		/* 3、return new reqeust body string */
		return json.toString();
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream bais = new ByteArrayInputStream(requestBody);
		return new ServletInputStream() {
			@Override
			public int read() throws IOException {
				return bais.read();
			}

			@Override
			public void setReadListener(ReadListener listener) {
			}

			@Override
			public boolean isReady() {
				return false;
			}

			@Override
			public boolean isFinished() {
				return false;
			}
		};
	}

}
