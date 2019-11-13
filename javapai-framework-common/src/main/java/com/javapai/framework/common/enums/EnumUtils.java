package com.javapai.framework.common.enums;

import java.util.LinkedHashMap;

/**
 * <strong>Title : EnumUtils<br></strong>
 * <strong>Description : </strong>
 * 枚举工具类<br> 
 * <strong>Create on : 2011-11-2<br></strong>
 * <p>
 * <strong>Copyright (C) Ecointel Software Co.,Ltd.<br></strong>
 * <p>
 * @author peng.shi peng.shi@ecointellects.com<br>
 * @version <strong>Ecointel v1.0.0</strong><br>
 * <br>
 * <strong>修改历史:</strong><br>
 * 修改人		修改日期		修改描述<br>
 * -------------------------------------------<br>
 * <br>
 * <br>
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class EnumUtils {
	/**
	 * 将BaseEnum.getCode()作为Key,Enums.getDesc()作为value,存放在Map中并返回
	 * @param <T>
	 * @param values
	 * @return
	 */
	public static <T extends Enums> LinkedHashMap toMap(Class<? extends Enums> enumClass) {
		return toMap(enumClass.getEnumConstants());
	}

	/**
	 * 将BaseEnum.getCode()作为Key,Enums.getDesc()作为value,存放在Map中并返回
	 * @param <T>
	 * @param values
	 * @return
	 */
	public static <T extends Enums> LinkedHashMap toMap(T[] values) {
		LinkedHashMap map = new LinkedHashMap();
		for (Enums item : values) {
			map.put(item.getKey(), item.getValue());
		}
		return map;
	}

	public static <T extends Enums> Object getCode(T kv) {
		if (kv == null)
			return null;
		return kv.getKey();
	}

//	public static <T extends Enums> String getDesc(T kv) {
//		if (kv == null) {
//			return null;
//		}
//			
//		return kv.getValue();
//	}

	public static <T extends Enum> String getName(T kv) {
		if (kv == null)
			return null;
		return kv.name();
	}

	/**
	 * 根据code查找得到Enum
	 * @param code
	 * @param values
	 * @return
	 */
	public static <T extends Enums> T getByCode(Object code, Class<? extends Enums> enumClass) {
		return (T) getByCode(code, enumClass.getEnumConstants());
	}

	/**
	 * 根据code查找得到Enum
	 * @param code
	 * @param values
	 * @return
	 */
	public static <T extends Enums> T getByCode(Object code, T[] values) {
		if (code == null)
			return null;
		for (T item : values) {
			if (item.getKey().equals(code)) {
				return item;
			}
		}
		return null;
	}

	/**
	 * 根据code查找得到Enum
	 * @param code
	 * @param values
	 * @return
	 */
	public static <T extends Enums> T getRequiredByCode(Object code, Class<? extends Enums> enumClass) {
		return (T) getRequiredByCode(code, enumClass.getEnumConstants());
	}

	/**
	 * 根据code得到Enum,找不到则抛异常
	 * @param <T>
	 * @param code
	 * @param values
	 * @return
	 * @throws IllegalArgumentException 根据code得到Enum,找不到则抛异常
	 */
	public static <T extends Enums> T getRequiredByCode(Object code, T[] values) throws IllegalArgumentException {
		Enums v = getByCode(code, values);
		if (v == null) {
			if (values.length > 0) {
				String className = values[0].getClass().getName();
				throw new IllegalArgumentException("not found " + className + " value by code:" + code);
			} else {
				throw new IllegalArgumentException("not found Enum by code:" + code);
			}
		}
		return (T) v;
	}

}
