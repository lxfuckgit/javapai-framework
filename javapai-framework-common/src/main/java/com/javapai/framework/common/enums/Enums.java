package com.javapai.framework.common.enums;

/**
 * Title : 枚举类基类.<br>
 * 建议所有枚举类(基础业务枚举类)继承实现本接口，强制实现读取枚举编码与枚举消息.<br>
 * 
 * Description :K为泛型,为Code字段的类型定义 <br>
 * <P>
 * 
 * 这样是不是可以代替大部分的简单字典表或是限定字典数据取值范围?。
 * <p>
 * 
 * @author liuxiang<br>
 */
public interface Enums<K,V> {
	/**
	 * 读取当前枚举类编码标识.
	 * <br>
	 * getKey() = getCode();
	 * 
	 * @return
	 */
	K getKey();

	/**
	 * 读取当前枚举类代表的值. <br>
	 * getValue()=getName();
	 *
	 * @return
	 * 
	 */
	V getValue();

	/**
	 * 取得排序号.
	 * 
	 * @return
	 */
//	K getIndex();

}
