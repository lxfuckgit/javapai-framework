package com.javapai.framework.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Http请求工具(原生JDK实现)。<br>
 * 
 * 暂时只支持GET,POST提交方式，更复杂的请求方式请移步UtilHttpRst.java工具类.<br>
 * 
 * @author pooja
 *
 */
public abstract class UtilHttp {
	/**/
	@SuppressWarnings("serial")
	static Map<String, String> form_header = new HashMap<String, String>() {
		{
			put("Content-Type", "x-www-form-urlencoded");
		}
	};
	
	/**/
	@SuppressWarnings("serial")
	static Map<String, String> json_header = new HashMap<String, String>() {
		{
			put("Content-Type", "application/json");
		}
	};
	
	/**
	 * {@link UtilHttp#requestGet(String, Map)}
	 * 
	 * @param url
	 * @return
	 */
	public static String requestGet(String url) {
		return request(url, null, null, "GET");
	};
	
	/**
	 * 通过Get方式向url地址以parameter参数提交请求。<br>
	 * 
	 * @param url 请求地址.<br>
	 * @param parameter 请求参数.<br>
	 * @return
	 */
	public static String requestGet(String url, Map<String, Object> parameter) {
		return request(url, parameter, null, "GET");
	};
	
	/**
	 * 通过Post方式请求指定url。<br>
	 * 
	 * @param url
	 * @return
	 */
	public static String requestPost(String url) {
		return request(url, null, null, "Post");
	};
	
	public static String jsonPost(String url) {
		return request(url, null, json_header, "Post");
	};
	
	public static String jsonPost(String url, Map<String, Object> parameter) {
		return request(url, parameter, json_header, "Post");
	};
	
	public static String jsonPost(String url, String jsondata, Map<String, String> header) {
		return null;//jsonPost(url, UtilJson.string2Map(jsondata), header);
	}

	public static String jsonPost(String url, Map<String, Object> parameter, Map<String, String> header) {
		if (null == header) {
			header = json_header;
		} else {
			header.putAll(json_header);
		}
		return request(url, parameter, header, "POST");
	}
	
	/**
	 * 通过Post方式以parameter中指定的参数请求请定url.<br>
	 * 
	 * @param url
	 * @param parameter
	 * @return
	 * 
	 * @deprecated 由formPost(String url, Map<String, Object> parameter);方法代替。
	 */
	@Deprecated
	public static String requestPost(String url, Map<String, Object> parameter) {
		return request(url, parameter, null, "Post");
	};
	
	public static String formPost(String url, Map<String, Object> parameter) {
		return request(url, parameter, form_header, "Post");
	};
	
	/**
	 * 
	 * @param url
	 * @param parameter
	 * @param header
	 * @return
	 */
	public static String requestPost(String url, Map<String, Object> parameter, Map<String, String> header) {
		return request(url, parameter, header, "Post");
	};
	
	/**
	 * 向指定 URL附带其参数(parameters)后发送按指定类型(method)Http请求.<br>
	 * <br>
	 * 注意： <br>
	 * 1:此接口现在只支持post或get方式提交；<br>
	 * 2:content-type只支持application/x-www-form-urlencoded;<br>
	 * 3:如果有其它需求，尽量选用其它接口方式.<br>
	 * 
	 * <br>
	 * private私有化说明：本工具类初衷只提供明确的方式（如xxxPost、xxxGet），于是私有化reqeust()方法供上层调用。
	 * 
	 * @param url
	 *            请求URL,必需以http://开头。<br>
	 * @param parameters
	 *            指定请求参数。<br>
	 * @param method
	 *            暂时只支持POST or GET<br>
	 * @return 请求结果.<br>
	 */
	private static String request(String url, Map<String, Object> parameters, Map<String, String> headers, String method) {
		String result = "";
		BufferedReader br = null;
		HttpURLConnection conn = null;
		
		try {
			URL httpUrl = new java.net.URL(url);
			// 1:打开和URL之间的连接
			conn = (HttpURLConnection) httpUrl.openConnection();
			
			// 2:设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setConnectTimeout(10000);
			
			// 3:增加http-header参数.
			if (null != headers && headers.size() > 0) {
				for (String key : headers.keySet()) {
					conn.setRequestProperty(key, headers.get(key));
//		            conn.setRequestProperty("Content-Type", "application/json");//post请求一定要设置这样？
				}
			}
			
			// 4：对不同的请求方式(get/post)参数(parameter)作不同处理.
			if ("POST".equalsIgnoreCase(method)) {
				// 发送POST请求必须设置如下两行
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            conn.setRequestMethod("POST");
	            
				if (parameters != null && parameters.size() >= 0) {
					// 4.1:获取URLConnection对象对应的输出流
					OutputStream out = conn.getOutputStream();
//					out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
					
					// 4.2:发送请求参数
					if ("application/json".equalsIgnoreCase(conn.getRequestProperty("Content-Type"))) {
						// json data
						out.write(UtilJson.object2Json(parameters).getBytes("UTF-8"));
					} else if (form_header.get("Content-Type").equals(conn.getRequestProperty("Content-Type"))) {
						// form data
						StringBuffer ss = new StringBuffer();
						for (Map.Entry<String, Object> entry : parameters.entrySet()) {
							ss.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
							ss.append("=");
							ss.append(URLEncoder.encode(String.valueOf(entry.getValue()), "UTF-8"));
						}
						out.write(ss.toString().getBytes("UTF-8"));
					}
					
					//4.3:flush输出流的缓冲
					out.flush();
					out.close();
				}
			
			} else if ("GET".equalsIgnoreCase(method)) {
				conn.setRequestMethod("GET");
				// 连接请求参数.
				if(null != parameters && parameters.size() > 0) {
					StringBuilder sb = new StringBuilder();
					for (String v : parameters.keySet()) {
						sb.append("&").append(v).append("=").append(parameters.get(v));
					}
					url += sb.toString();	
					
//					url = url + "?";
//	                int capacity = param.size() * 30;
//	                buffer = new StringBuilder(capacity);
//	                Iterator<Map.Entry<String, String>> it = param.entrySet().iterator();
//	                while (it.hasNext()) {
//	                    Map.Entry<String, String> entry = it.next();
//	                    Object key = entry.getKey();
//	                    buffer.append(key);
//	                    buffer.append('=');
//	                    Object value = entry.getValue();
//	                    buffer.append(value);
//	                    if (it.hasNext()) {
//	                        buffer.append("&");
//	                    }
//	                }
				}
			}
			
			// 5：建立实际的连接
			conn.connect();
			
			// 6：读取返回内容(ByteArrayOutputStream字节流)
//			int readLength = 0;
//			byte[] rByte = new byte[1024];
//			java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
//			while ((readLength = conn.getInputStream().read(rByte, 0, 1024)) != -1) {
//				baos.write(rByte, 0, readLength);
//			}
//			result = new String(baos.toByteArray(), "UTF-8");
//			baos.close();
			
			// 6：读取返回内容(BufferedReader字符流)
			// br = new BufferedReader(new InputStreamReader(url.openStream()));/// so, do that is Ok
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));//为什么原生xxxStream不行,会乱码呢?
			String line = "";
			while ((line = br.readLine()) != null) {
				result += line;
			}
			br.close();
			
			//7：关闭连接
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	private static final String nextLine = "\r\n";//定义结束换行字符串
    private static final String twoHyphens = "--";// 定义连接字符串
    private static final String boundary = "-------WebKitFormBoundaryUey8ljRiiZqhZHBu";// 定义分界线字符串
    
	/**
	 * 上传文件
	 * 
	 * @deprecated 空实现.
	 * @param url 请求路径
	 * @param file 文件
	 */
	public static void requestPostFile(String url, File file) {
		HttpURLConnection connection = null;
		OutputStream outputStream = null;
		FileInputStream inputStream = null;
		try {
			// 获取HTTPURLConnection连接
	        URL url1 = new URL(url);
	        connection = (HttpURLConnection) url1.openConnection();
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Charsert", "UTF-8");
			
			// 运行写入默认为false，置为true
			connection.setDoOutput(true);
			// 禁用缓存
			connection.setUseCaches(false);
			// 设置接收编码
			connection.setRequestProperty("Accept-Charset", "utf-8");
			// 开启长连接可以持续传输
			connection.setRequestProperty("Connection", "keep-alive");
			// 设置请求参数格式以及boundary分割线
			connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			// 设置接收返回值的格式
			connection.setRequestProperty("Accept", "application/json");
			// 开启连接
			connection.connect();

			// 获取http写入流
			outputStream = new DataOutputStream(connection.getOutputStream());

			// 分隔符头部
			String header = twoHyphens + boundary + nextLine;
			// 分隔符参数设置
			header += "Content-Disposition: form-data;name=\"file\";" + "filename=\"" + file.getName() + "\"" + nextLine
					+ nextLine;
			// 写入输出流
			outputStream.write(header.getBytes());

			// 读取文件并写入
			inputStream = new FileInputStream(file);
			byte[] bytes = new byte[1024];
			int length;
			while ((length = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, length);
			}
			// 文件写入完成后加回车
			outputStream.write(nextLine.getBytes());

			// 写入结束分隔符
			String footer = nextLine + twoHyphens + boundary + twoHyphens + nextLine;
			outputStream.write(footer.getBytes());
			outputStream.flush();
			// 文件上传完成
			InputStream response = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(response);
			while (reader.read() != -1) {
				System.out.println(new String(bytes, "UTF-8"));
			}
			
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				System.out.println(connection.getResponseMessage());
			} else {
				System.err.println("上传失败");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
				if (connection != null) {
					connection.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};
	
	/**
	 * 
	 * @param serverUrl
	 * @param filePath
	 */
	public static void requestPostFile(String serverUrl, String filePath) {
		requestPostFile(serverUrl, new String[] { filePath }, null);
	};
	
	/**
	 * 上传多个文件。
	 *
	 * @param actionURL
	 *            上次的目标路径地址
	 * @param filePaths
	 *            上传的文件路径数组
	 * @return 服务器响应数据
	 */
	public static String requestPostFile(String serverUrl, String[] filePaths) {
		requestPostFile(serverUrl, filePaths, null);
		return serverUrl;
	}
	
	/**
	 * 
	 * @param url
	 * @param filepath
	 * @param parameter
	 */
	public static void requestPostFile(String serverUrl, String filepath, Map<String, String> parameter) {
		requestPostFile(serverUrl, new String[] { filepath }, parameter);
	};
	
	public static void requestPostFile(String serverUrl, String[] filePaths, Map<String, String> parameter) {
        try {
            // 创建URL对象
			URL url = new URL(serverUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 获取连接对象
			connection.setDoOutput(true);// 设置允许输入流输入数据到本机
			connection.setDoInput(true);// 设置允许输出流输出数据到服务器
			connection.setReadTimeout(30000);
			connection.setConnectTimeout(5000);
			connection.setUseCaches(false);// 设置不使用缓存
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Charsert", "UTF-8");
//			connection.setChunkedStreamingMode(10240);// 指定流的大小，当内容达到这个值的时候就把流输出
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			// 设置请求参数中的内容类型为multipart/form-data,设置请求内容的分割线为******
			connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

            // 从连接对象中获取输出流,实例化数据输出流对象，将输出流传入
			DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            
			/* 组装form表单参数 */
			if (parameter != null && parameter.size() > 0) {
				StringBuffer sb = new StringBuffer();
				Iterator<Map.Entry<String, String>> iter = parameter.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<String, String> entry = iter.next();
					if (entry.getValue() == null) {
						continue;
					}
					sb.append("\r\n").append("--").append(boundary).append("\r\n");
					sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"\r\n\r\n");
					sb.append(entry.getValue());
				}
				outputStream.write(sb.toString().getBytes());
			}

            // 遍历文件路径的长度,将路径数组下所有路径的文件都写到输出流中
            for (int i = 0; i < filePaths.length; i++) {
				/*
					Iterator<Map.Entry<String, String>> iter = fileMap.entrySet().iterator();
//						while (iter.hasNext()) {
//							Map.Entry<String, String> entry = iter.next();
							String inputName = "file";//(String) entry.getKey();
//							String inputValue = (String) entry.getValue();
//							File file = new File(inputValue);
//							String filename = file.getName();
//							MagicMatch match = Magic.getMagicMatch(file, false, true);
							String contentType = "application/octet-stream";//match.getMimeType();
//						}
				*/
            	
    			// 添加form属性
//    			List<String> list = new ArrayList<String>();
//    			list.add(filepath);
//    			int leng = list.size();
//    			for (int i = 0; i < leng; i++) {
//    				String fname = list.get(i);
//    				File file = new File(fname);
//    				StringBuilder sb = new StringBuilder();
//    				sb.append("--").append(boundary).append("\r\n");
//    				sb.append("Content-Disposition: form-data;name=\"file" + i + "\";filename=\"" + file.getName()+ "\"\r\n");
//    				sb.append("Content-Type:application/octet-stream\r\n\r\n");
//    				
////    				out.write(sb.toString().getBytes());
//    				DataInputStream in = new DataInputStream(new FileInputStream(file));
//    				int bytes = 0;
//    				byte[] bufferOut = new byte[1024];
//    				while ((bytes = in.read(bufferOut)) != -1) {
////    					out.write(bufferOut, 0, bytes);
//    				}
////    				out.write("\r\n".getBytes()); // 多个文件时，二个文件之间加入这个
//    				in.close();
//    			}
    			
//    			byte[] end_data = ("\r\n--" + boundary + "--\r\n").getBytes();// 定义最后数据分隔线
//    			out.write(end_data);
            	
                // 取出文件路径
                String filePath = filePaths[i];
                // 获取文件名称
                String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
                
				/* 数据报文-表头报文 */
				StringBuffer fileBuf = new StringBuffer();
				fileBuf.append(nextLine).append(twoHyphens).append(boundary).append(nextLine);
				fileBuf.append("Content-Disposition: form-data;name=file;filename=" + fileName + nextLine);
				String contentType = "application/octet-stream";
				fileBuf.append("Content-Type:" + contentType + "\r\n\r\n");
				
				/* 表头报文-写入到流 */
                outputStream.writeBytes(fileBuf.toString());

                /* 数据报文-文件内容 */
                FileInputStream fileInputStream = new FileInputStream(filePath); // 实例化文件输入流对象，将文件路径传入，用于将磁盘上的文件读入到内存中
                byte[] buffer = new byte[1024];  // 定义字节数组对象，用来读取缓冲区数据
                // 循环从文件输出流中读取1024字节的数据，将每次读取的长度赋值给length变量，直到文件读取完毕，值为-1结束循环
                int length = 0;
                while ((length = fileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, length);// 向数据输出流中写出数据
                }
                // 每写出完成一个完整的文件流后，需要向数据输出流中写出结束标志符
//                outputStream.writeBytes(nextLine);
                // 关闭文件输入流
                fileInputStream.close();
            }
            
			/* 向数据输出流中写出分隔符并刷新数据输出流 */
			// outputStream.writeBytes(twoHyphens + boundary + twoHyphens + nextLine);
			outputStream.writeBytes("\r\n--" + boundary + "--\r\n");

			/* 关闭打开的输出流 */
			outputStream.flush();
			outputStream.close();

            // 从连接对象中获取字节输入流
            InputStream inputStream = connection.getInputStream();
            // 实例化字符输入流对象，将字节流包装成字符流
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            // 创建一个输入缓冲区对象，将要输入的字符流对象传入
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // 创建一个字符串对象，用来接收每次从输入缓冲区中读入的字符串
            String line;
            // 创建一个可变字符串对象，用来装载缓冲区对象的最终数据，使用字符串追加的方式，将响应的所有数据都保存在该对象中
            StringBuilder sb = new StringBuilder();
            // 使用循环逐行读取缓冲区的数据，每次循环读入一行字符串数据赋值给line字符串变量，直到读取的行为空时标识内容读取结束循环
            while ((line = bufferedReader.readLine()) != null) {
                // 将缓冲区读取到的数据追加到可变字符对象中
                sb.append(line);
            }

            // 依次关闭打开的输入流
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("发送POST请求出错。");
        } finally {
//        	if (connection != null) {
//        		connection.disconnect();
//        		connection = null;
//			}
        }
        
	}
	
}
