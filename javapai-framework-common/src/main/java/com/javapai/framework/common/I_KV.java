package com.javapai.framework.common;

/**
 * 统一全局K-V形式对象接口。<br>
 * 
 * @author pooja
 *
 * @param <K>
 *            对象－键.<br>
 * @param <V>
 *            对象-值.<br>
 */
public interface I_KV<K, V> {
	/**
	 * 读取对象键。 <br>
	 * 
	 * @return
	 */
	K getKey();

	/**
	 * 读取对象值。 <br>
	 *
	 * @return
	 * 
	 */
	V getValue();
}
