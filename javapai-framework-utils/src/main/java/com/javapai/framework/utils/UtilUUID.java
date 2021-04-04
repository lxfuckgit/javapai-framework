package com.javapai.framework.utils;

import java.util.Random;
import java.util.UUID;

/**
 * 
 * @author liu.xiang
 * @deprecated 换用apache.commons.lang3的RandomStringUtils。<br>
 */
public final class UtilUUID {

	/**
	 * 
	 */
	private static UtilUUID sequence = null;
	/**
	 * 
	 */
	private static char[] mark = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
			'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
			'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
			'Z' };

	private UtilUUID() {

	}
	
	/**
	 * 
	 * @return
	 */
	public static UtilUUID getInstance() {
		if (sequence == null) {
			sequence = new UtilUUID();
		}
		return sequence;
	}

	/**
	 * 返回一个指定长度的不重复序列,此方法有重复的可能性<br>
	 * 调用方法为UtilUUID.getInstance().getSequence(12)
	 * 
	 * @param length
	 *            序列长度
	 * @return 指定长度的序列码
	 */
	public String getSequence(int length) {
		if (length == 32) {
			String uuid = UUID.randomUUID().toString().replace("-", "")
					.toUpperCase();
			return uuid;
			
		} else {
			StringBuffer sf = new StringBuffer(length);
			for (int i = 0; i < length; i++) {
				sf.append(mark[(int) ((1 - Math.random()) * 62)]);
			}
			return sf.toString();
		}
	}
	
	public static String getRandomInteger() {
		int temp = (int) (Math.random() * 9999);
		return String.valueOf(temp);
	}
	
	/**
	 * 
	 * @param length
	 *            生成字符串的长度。<br>
	 * @return
	 */
	public static String getRandomString(int length) {// length表示
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	// 感觉用UUID一点逻辑感都没了 也看不出日期
	// 还是自己写一个??

	// 流水编号+日期+日流水基本上可以确保编号的唯一。不保证当日交易过大，流水用尽的情况

	// 流水编号+[进程号|实例号]+日期+日流水号 这样分布式生成唯纺号重复可能性小一点
}