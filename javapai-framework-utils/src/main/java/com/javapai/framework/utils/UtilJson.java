package com.javapai.framework.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * JSON格式化工具类。<br>
 * 依赖第三方jar:jackson。<br>
 * https://www.zhihu.com/question/44199956 关于为什么此包依赖jackson而非fastjson的说明:<br>
 * 1:fastjson并非有明显的高性能相比于jackson。<br>
 * 2：因为fastjson快的特点，所有其json标准支持得并非那么完整。<br>
 * 3:<br>
 * 
 * <br>
 * <strong>重要提示：</strong>本工具类依赖第三方jackson序列化包，在使用时，请自行导入所需要的依赖包。<br>
 * <strong>提示说明：</strong>框架提供方认为jackson包属于常规包，在很多成熟的框架中都有集成(例如spring框架)，为了防止出现不同jackson包的版本冲突，我们提倡让使用方自行引用jackson包决定包的版本号。<br>
 * 
 * @author pooja
 *
 */
public class UtilJson {
	/**
	 * 
	 */
	protected static Logger logger = LoggerFactory.getLogger(UtilJson.class);

	/* mapper会不会多实例？ */
	private static final ObjectMapper mapper = new ObjectMapper();
	/* http://www.blogjava.net/crazycy/archive/2014/07/15/415839.html */
	// private static ObjectMapper mapper;
	//
	// public static synchronized ObjectMapper getInstance() {
	// if (mapper == null) {
	// mapper = new ObjectMapper();
	// }
	// return mapper;
	// }

	static {
		/*
		 * 序列化时间的指定时间格式，时区问题。1：@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",
		 * locale = "zh" , timezone="GMT+8")
		 */
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mapper.setDateFormat(dateFormat);
		// 指定时区->TimeZone.getTimeZone("GMT+8:00")
		mapper.setTimeZone(TimeZone.getDefault());

		// 限定格式化timestamp.
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

		/* 反序列化时，忽略属性与json一一对应 */
		// before jackson 1.9
		// mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
		// false);
		// after jackson 2.0
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// 忽略未知属性解析[1:注解申明@JsonIgnoreProperties(ignoreUnknown=true);2:属性配置mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
		// false);]
	}

	public static JsonNode json2Object(String jsonString) {
		// JsonNode ArrayNode ObjectNode
		try {
			return mapper.readTree(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("方法json2Object---->" + e.getMessage());
		}
		return null;
	}

	/**
	 * 使用泛型方法，把json字符串转换为相应的JavaBean对象。 <br>
	 * 
	 * @param jsonString
	 * @param c
	 * @return
	 */
	public static <T> T json2Object(String jsonString, Class<T> c) {
		try {
			return mapper.readValue(jsonString, c);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("方法json2Object---->" + e.getMessage());
		}
		return null;
	}
	
	public static <T> List<T> json2List(String jsonString, Class<T> c) {
		JavaType type = mapper.getTypeFactory().constructParametricType(List.class, c);
		try {
			return mapper.readValue(jsonString, type);
		} catch (IOException e) {
			logger.error("---->" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	};
	
	/**
	 * 把JavaBean转换为json字符串.<br>
	 * 
	 * @param obj
	 * @return
	 */
	public static String object2Json(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error("方法object2Json---->" + e.getMessage());
		}
		return null;
	}
	
	/**
	 * 将json格式的String转化为Map<String,String>对象.<br>
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Map<String, String> string2Map(String jsonString) {
		try {
			return mapper.readValue(jsonString, new TypeReference<Map<String, String>>() {
			});
		} catch (IOException e) {
			logger.error("---->" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
}
