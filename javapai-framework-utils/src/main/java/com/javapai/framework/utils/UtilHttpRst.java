package com.javapai.framework.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

/**
 * 负责Http请求有关的工具类.<br>
 * 
 * <br>
 * 依赖于apache-httpclient.jar<br>
 * 
 * <br>
 * http请求可选方式种类繁多，从URLConnection->HttpURLConnection->HttpClient->OkHttp3->Volley->Retrofit2->RestTemplate等等。
 * 
 * 1:HttpURLConnection原生支持gzip压缩，HttpClient默认不支持,但是可以自己添加进IO流的处理里.<br>
 * <br>
 * 2:HttpClient比HttpURLConnection提供了更多高级特性(比如重访问的自定)，但是更加占用内存和CPU资源.<br>
 * <br>
 * 3:Spring的RestTemplate内部实现(SimpleClientHttpRequestFactory)就是一个完全不依赖三方包的纯HttpURLConnection实现;如果选用RestTemplate建议忽略此工具.
 * <br>
 * 4:同时Spring的RestTemplate内部实现还有更多实现方式，包括apache-httpclient、Okhttp3等;如果选用RestTemplate建议忽略此工具.
 * <br>
 * 
 * @author pooja
 *
 */
public final class UtilHttpRst {
//public final class UtilHttpRst extends UtilHttp {// 未继承UtilHttp,因为UtilHttp中的静态方法不能被@Override
	/**
	 * 
	 * @param url 
	 * @return
	 */
	public static String requestGet(String url) {
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse httpResponse = getHttpClient().execute(httpGet);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return EntityUtils.toString(httpResponse.getEntity());
			} else {
				System.out.println("doGet Error Response: " + httpResponse.getStatusLine().toString());
				return httpResponse.getStatusLine().toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param url
	 * @param parameters
	 * @return
	 */
	public static String requestGet(String url, Map<String, Object> parameters) {
		StringBuffer sb = new StringBuffer("?1=1");
		for (String v : parameters.keySet()) {
			sb.append("&").append(v).append("=").append(parameters.get(v));
		}

		return requestGet(url);
	}
	
	/**
	 * 
	 * @param url
	 * @param data
	 * @return
	 * 
	 * @deprecated 目的在屏蔽第三方组件在本方法上的直接暴露.<br>
	 */
	@Deprecated
	public static String requestGet(String url, List<BasicNameValuePair> data) {
		return requestGet(url);
	}

//	/**
//	 * 带文件的post.<br>
//	 * 
//	 * @param url
//	 * @param file
//	 *            例如：File file = new File("c:/TRASH/zaba_1.jpg");
//	 * @throws Exception
//	 */
//	public static void requestPost(String url,File file) throws Exception {
//		File a = new File("C:\\Users\\liu.xiang\\Desktop\\jym\\业务流程.bmp");
//		
////		FileBody fileBody = new FileBody(a);
////		StringBody stringBody1 = new StringBody("Message 1", ContentType.MULTIPART_FORM_DATA);
////		StringBody stringBody2 = new StringBody("Message 2", ContentType.MULTIPART_FORM_DATA);
////		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
////		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
////		builder.addPart("upfile", fileBody);
////		builder.addPart("text1", stringBody1);
////		builder.addPart("text2", stringBody2);
////		HttpEntity entity = builder.build();
////		//
////		HttpPost post = new HttpPost(url);
////		post.setEntity(entity);
////		HttpClient httpclient = new DefaultHttpClient();
////		HttpResponse response = httpclient.execute(post);
////		System.out.println(response.toString());
////		System.out.println(response.getStatusLine().getStatusCode());
//		
//		
//		HttpPost post = new HttpPost(url);
//		String message = "This is a multipart post";
//		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//		builder.addBinaryBody("upfile", file, ContentType.DEFAULT_BINARY, "abc");
////		builder.addBinaryBody("xxx", "fileInputSteam")
//		builder.addTextBody("text", message, ContentType.DEFAULT_BINARY);
//		HttpEntity entity = builder.build();
//		post.setEntity(entity);
//		HttpClient httpclient = new DefaultHttpClient();
//		HttpResponse response = httpclient.execute(post);
//		System.out.println(response.toString());
//		System.out.println(response.getStatusLine().getStatusCode());
//		
//	}

//	/**
//	 * 获取图片字节流
//	 * 
//	 * @param uri
//	 * @return
//	 * @throws Exception
//	 */
//	public static byte[] getByte(String uri) {
//		HttpClient client = new DefaultHttpClient();
//		client.getParams().setParameter(
//				CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
//		
//		HttpGet get = new HttpGet(uri);
//		get.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
//				5000);
//		try {
//			HttpResponse resonse = client.execute(get);
//			if (resonse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//				HttpEntity entity = resonse.getEntity();
//				if (entity != null) {
//					return EntityUtils.toByteArray(entity);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			client.getConnectionManager().shutdown();
//		}
//		return new byte[0];
//	}
	
	/**
	 * 
	 * @param url
	 * @param jsonString
	 * @return
	 */
	public static String requestPut(String url, String jsonString) {
		System.out.println(">>>请求数据格式：" + jsonString);
		StringEntity httpEntity = new StringEntity(jsonString,"utf-8");
		return requestPut(url, httpEntity);
	}
	
	public static String requestPut(String url, AbstractHttpEntity httpEntity) {
		httpEntity.setContentEncoding("UTF-8");
		httpEntity.setContentType("application/json");
		
		CloseableHttpClient httpclient = getHttpClient();
		System.out.println(">>>请求地址格式：" + url);
		
		HttpPut httpput = new HttpPut(url);
		httpput.setEntity(httpEntity);

		try {
			CloseableHttpResponse response = httpclient.execute(httpput);
			int httpCode = response.getStatusLine().getStatusCode();
			System.out.println(">>>正在请求状态码：" + httpCode);
			if (httpCode == 200) {
				HttpEntity entity = response.getEntity();
				String jsonStr = EntityUtils.toString(entity, "utf-8");
				System.out.println(">>>请求返回内容：" + jsonStr);
				return jsonStr;
			} else {
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpput.releaseConnection();
		}
		
		return null;
	}
	
	/**
	 * 向指定url目标以post方式发送请求.<br>
	 * 
	 * @param url
	 *            请求地址.<br>
	 * @param jsondata
	 *            json格式请求体.<br>
	 * @return
	 */
	public static String requestPost(String url, String jsondata) {
		return requestPost(url, jsondata, null);
	}
	
	/**
	 * 向指定的URL发起post请求.<br>
	 * 默认Content-Type=application/json. <br>
	 * 
	 * @param url
	 *            请求地址.<br>
	 * @param jsondata
	 *            json格式请求体.<br>
	 * @param header
	 *            请求头.<br>
	 * @return
	 */
	public static String requestPost(String url, String jsondata, Map<String, String> header) {
		System.out.println(">>>请求数据格式：" + jsondata);
		
		//check
		if(StringUtils.isBlank(jsondata)) {
			System.out.println(">>>请检查jsondata数据：" + jsondata);
			return null;
		}
		
		//
		StringEntity httpEntity = new StringEntity(jsondata,"utf-8");
		httpEntity.setContentEncoding("UTF-8");
		
		//
        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(httpEntity);
        httppost.addHeader("Content-Type", "application/json;charset=UTF-8");
		if (null != header) {
			for (String key : header.keySet()) {
				httppost.addHeader(key, header.get(key));
			}
		}
		
		//
		String result = "";
		CloseableHttpResponse response = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			response = httpclient.execute(httppost);
			HttpEntity responseEntity = response.getEntity();
			result = EntityUtils.toString(responseEntity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param url
	 *            请求URL.
	 * @param param
	 *            请求体参数.
	 * @return
	 */
	public static String requestPost(String url, Map<String, Object> param) {
		return UtilHttpRst.requestPost(url, param, null);
	}
	
	/**
	 * 
	 * @param url
	 *            请求URL.
	 * @param param
	 *            请求体参数.
	 * @param header
	 *            请求头参数.
	 * @return
	 */
	public static String requestPost(String url, Map<String, Object> param, Map<String, Object> header) {
		return "";
	}
	
	/**
	 * 
	 * @param url
	 * @param httpEntity
	 * @return
	 * 
	 * @deprecated 过期原因：屏蔽直接暴露第三方类.
	 */
	@Deprecated
	public static String requestPost(String url, AbstractHttpEntity httpEntity) {
		System.out.println(">>>请求地址格式：" + url);
		
		httpEntity.setContentEncoding("UTF-8");
		httpEntity.setContentType("application/json");
		
		HttpPost httppost = new HttpPost(url);
		httppost.setEntity(httpEntity);
		return doRequest(httppost);
	}
	
	private static String doRequest(HttpRequestBase request) {
		CloseableHttpClient httpclient = getHttpClient();
		try {
			CloseableHttpResponse response = httpclient.execute(request);
			int httpCode = response.getStatusLine().getStatusCode();
			System.out.println(">>>正在请求状态码：" + httpCode);
			if (httpCode == 200) {
				HttpEntity entity = response.getEntity();
				String jsonStr = EntityUtils.toString(entity, "utf-8");
				System.out.println(">>>请求返回内容：" + jsonStr);
				return jsonStr;
			} else {
				System.out.println(">>>请求错误：");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			request.releaseConnection();
		}
		return null;
	}
	
	/**
	 * Post表单数据.<br>
	 * 
	 * @param httpUrl
	 * @param params
	 * @return
	 */
	public static String requestPostForm(String httpUrl, Map<String, Object> params) {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		// 给参数赋值
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
		}
		
		return requestPostForm(httpUrl, formparams);
	}
	
	/**
	 * Post表单数据.<br>
	 * 
	 * @param httpUrl
	 *            请求连接地址，地址必须以"http://"开头。<br>
	 *            例如:http://www.baidu.com.
	 * @param params
	 *            表单参数集.<br>
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String requestPostForm(String httpUrl, List<NameValuePair> params) {
		//设置全局的标准cookie策略(4.5x后)
//		RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
		//设置可关闭的httpclient(4.5x后)
//		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(config).build();
		
		CloseableHttpClient httpclient = null;
		if (httpUrl.startsWith("https") || httpUrl.startsWith("HTTPS")) {
			httpclient = getHttpClient();
		} else {
			httpclient = getHttpsClient();
		}
		
		System.out.println(">>>正在测试请求：" + httpUrl);
		HttpPost httppost = new HttpPost(httpUrl);
//		httppost.setHeader(header);

//		httppost.setEntity(new UrlEncodedFormEntity(params));
//		httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		httppost.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));
		
		try {
			CloseableHttpResponse httpResponse = httpclient.execute(httppost);
			System.out.println(httpResponse.getStatusLine().getStatusCode());
			
			if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				System.out.println("---------------");
				HttpEntity entity = httpResponse.getEntity();
				return EntityUtils.toString(entity, "utf-8");
			} else {
				System.out.println("doPost Error Response: " + httpResponse.getStatusLine().toString());
				return httpResponse.getStatusLine().toString();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httppost.releaseConnection();
		}
		
		return null;
	}
	
	/**
	 * 以Post方式提交一个文件流。<br>
	 * 
	 * @param inputStream
	 * @param filename
	 */
	public void requestPost(String httpUrl, InputStream inputStream, String filename) {
		CloseableHttpClient httpclient = getHttpClient();

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addBinaryBody("file", inputStream, ContentType.create("multipart/form-data"), "text01.txt");

		builder.addPart("id", new StringBody("12", ContentType.MULTIPART_FORM_DATA));
		HttpEntity entity = builder.build();
		
		HttpPost post = new HttpPost(httpUrl);
		post.setEntity(entity);
        try {
        	HttpResponse response = httpclient.execute(post);
            entity = response.getEntity();
			inputStream = entity.getContent();
			
            //转换为字节输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Consts.UTF_8));
            String body = null;
            while ((body = br.readLine()) != null) {
                System.out.println(body);
            }
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
	}
	
//    public static String postByHttpClientWithFile(String url, Map<String, String> map, MultipartFile file, String fileParamName) {
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
//        HttpPost httppost = new HttpPost(url);
//        CloseableHttpResponse response = null;
//        try {
//            InputStreamBody inputStreamBody = new InputStreamBody(file.getInputStream(), ContentType.MULTIPART_FORM_DATA, file.getOriginalFilename());
//            // add the file params
//            multipartEntityBuilder.addPart(fileParamName, inputStreamBody);
//            multipartEntityBuilder.addPart("comment", new StringBody(file.getOriginalFilename(), Charset.forName("UTF-8")));
//            // 设置上传的其他参数
//            if (map != null && map.size() > 0) {
//    			Set<String> keys = map.keySet();
//    			for (String key : keys) {
//    				multipartEntityBuilder.addPart(key, new StringBody(map.get(key), Charset.forName(HTTP.UTF_8)));
//    			}
//    		}
//            
//            HttpEntity reqEntity = multipartEntityBuilder.build();
//            httppost.setEntity(reqEntity);
//            response = httpclient.execute(httppost);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        HttpEntity responseEntity = response.getEntity();
//        String result = "";
//        try {
//            result = EntityUtils.toString(responseEntity);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
    
	/**
	 * 带文件的Post请求(http-client实现)。
	 * 
	 * @param url
	 * @param file
	 * @param parameter
	 */
	public static void requestPostFie(String url, String file, Map<String, String> parameter) {
		HttpClient httpclient = new org.apache.http.impl.client.DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://localhost");
		
		// 多部分的实体
		org.apache.http.entity.mime.MultipartEntity reqEntity = new org.apache.http.entity.mime.MultipartEntity();
		// 一个本地的文件
		FileBody bin = new FileBody(new File("d:/BIMG1181.JPG"));
		// 增加
		reqEntity.addPart("bin", bin);
		//
		for(Map.Entry<String, String> entry: parameter.entrySet()){
			try {
				reqEntity.addPart(entry.getKey(),new StringBody(entry.getValue()));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		// 设置
		httppost.setEntity(reqEntity);
		System.out.println("执行: " + httppost.getRequestLine());
		try {
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity resEntity = response.getEntity();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		httpclient.getConnectionManager().shutdown();
	};

	private static CloseableHttpClient getHttpClient() {
		// CookieStore cookieStore = new BasicCookieStore();
		// CloseableHttpClient httpClient = HttpClientBuilder.create().
		// setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy()).
		// setRedirectStrategy(new DefaultRedirectStrategy()).
		// setDefaultCookieStore(cookieStore).
		// setDefaultRequestConfig(requestConfig).build();

		//后续修改成单例.
		return HttpClientBuilder.create().build();// =org.apache.http.impl.client.HttpClients.createDefault();
	}
	
	private static CloseableHttpClient getHttpsClient(){
		SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            
//            sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {
//                @Override
//                public boolean verify(String arg0, SSLSession arg1) {
//                    return true;
//                }
//                @Override
//                public void verify(String host, SSLSocket ssl) throws IOException {
//                }
//                @Override
//                public void verify(String host, X509Certificate cert) throws SSLException {
//                }
//                @Override
//                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
//                }
//            });
            
            sslsf = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					// TODO Auto-generated method stub
					return false;
				}
			});
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	}
}
