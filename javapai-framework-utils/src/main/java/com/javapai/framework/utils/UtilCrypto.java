package com.javapai.framework.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * 基于原生JDK实现的加解密工具类。<br>
 * 是另一个apache-commons-codec实现.<br>
 * 
 * @author pooja
 *
 */
public class UtilCrypto {
	private static final String AES = "AES";
	private static final String SHA = "SHA";
	private static final String SHA1 = "SHA1";
	private static final String MD5 = "MD5";
	private static final String HMAC_SHA1 = "HmacSHA1";
	private static final String DES = "DES";
	
	private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
	
	private static byte iv[] = { 0x12, 0x34, 0x56, 0x78, (byte) 0x90,
			(byte) 0xAB, (byte) 0xCD, (byte) 0xEF };
	
	// private static byte iv [] = {0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD,
		// (byte) 0xEF,0x12, 0x34, 0x56};
	
	private static IvParameterSpec zeroIv = new IvParameterSpec(iv);

	/**
	 * AES加密字符串.<br>
	 * 
	 * @param source
	 *            需要被加密的字符串.
	 * @param key
	 *            加密需要的密码.
	 * @return 密文和密钥.
	 */
	public static String encryptAES(String source, String key) {
		if (key == null || "".equals(key) || key.length() != 16) {
			return null;
		}

		try {
			byte[] raw = key.getBytes(); // 获得密码的字节数组
			SecretKeySpec skey = new SecretKeySpec(raw, "AES"); // 根据密码生成AES密钥
			Cipher cipher = Cipher.getInstance(ALGORITHM); // 根据指定算法ALGORITHM自成密码器
			cipher.init(Cipher.ENCRYPT_MODE, skey);// 初始化密码器，第一个参数为加密(ENCRYPT_MODE)或者解密(DECRYPT_MODE)操作，第二个参数为生成的AES密钥
			byte[] byte_content = source.getBytes("utf-8"); // 获取加密内容的字节数组(设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
			byte[] encode_content = cipher.doFinal(byte_content); // 密码器加密数据
			System.out.println("加密后的内容：" + encode_content);
			return Base64.encodeBase64String(encode_content); // 将加密后的数据转换为字符串返回
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
    /**
	 * 解密AES加密过的字符串
	 *
	 * @param source
	 *            AES加密过过的内容
	 * @param key
	 *            加密时的密码
	 * @return 明文
	 */
	public static String decryptAES(String source, String key) {
		if (key == null || "".equals(key) || key.length() != 16) {
			return null;
		}
		try {
			byte[] raw = key.getBytes(); // 获得密码的字节数组
			SecretKeySpec skey = new SecretKeySpec(raw, "AES"); // 根据密码生成AES密钥
			Cipher cipher = Cipher.getInstance(ALGORITHM); // 根据指定算法ALGORITHM自成密码器
			cipher.init(Cipher.DECRYPT_MODE, skey); // 初始化密码器，第一个参数为加密(ENCRYPT_MODE)或者解密(DECRYPT_MODE)操作，第二个参数为生成的AES密钥
			byte[] encode_content = Base64.decodeBase64(source); // 把密文字符串转回密文字节数组
			byte[] byte_content = cipher.doFinal(encode_content); // 密码器解密数据
			return new String(byte_content, "utf-8"); // 将解密后的数据转换为字符串返回
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * SHA加密 并转换为16进制大写字符串
	 * 
	 * @param source
	 * @return
	 */
	public static String encryptSHA(String source) {
		try {
			MessageDigest sha = MessageDigest.getInstance(SHA);
			sha.update(source.getBytes());
			byte[] bytes = sha.digest();

			StringBuilder stringBuilder = new StringBuilder("");
			if (bytes == null || bytes.length <= 0) {
				return null;
			}
			for (int i = 0; i < bytes.length; i++) {
				int v = bytes[i] & 0xFF;
				String hv = Integer.toHexString(v);
				if (hv.length() < 2) {
					stringBuilder.append(0);
				}
				stringBuilder.append(hv);
			}
			return stringBuilder.toString().toUpperCase();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return "";
	}
	
	
	
	/**
	 * DES加密。<br>
	 * 
	 * @param encryptStr
	 *            待加密字符串.<br>
	 * @param encryptKey
	 *            加密密钥.<br>
	 * @return
	 */
	public static String encrypDES(String encryptStr, String encryptKey) {
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), DES);
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
		} catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			byte[] encryptedData = cipher.doFinal(encryptStr.getBytes());
			return new String(encryptedData);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * DES解密。<br>
	 * 
	 * @param encryptStr
	 *            待解密字符串.<br>
	 * @param decryptKey
	 *            解密密钥.<br>
	 * @return
	 */
	public static String decryptDES(String encryptStr, String decryptKey) {
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
		} catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			byte[] decryptedData = cipher.doFinal(encryptStr.getBytes());
			return new String(decryptedData);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;		
	}

	/**
	 * SHA加密 并转换为16进制大写字符串
	 * 
	 * @param source
	 * @return
	 */
	public static String encryptSHA1(String source) {
		try {
			MessageDigest sha = MessageDigest.getInstance(SHA1);
			sha.update(source.getBytes());
			byte[] bytes = sha.digest();

			StringBuilder stringBuilder = new StringBuilder("");
			if (bytes == null || bytes.length <= 0) {
				return null;
			}
			for (int i = 0; i < bytes.length; i++) {
				int v = bytes[i] & 0xFF;
				String hv = Integer.toHexString(v);
				if (hv.length() < 2) {
					stringBuilder.append(0);
				}
				stringBuilder.append(hv);
			}
			return stringBuilder.toString().toUpperCase();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key) {
		return filter((new sun.misc.BASE64Encoder()).encodeBuffer(key));
	}

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public static byte[] decryptBASE64(String key) {
		try {
			return (new sun.misc.BASE64Decoder()).decodeBuffer(key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除BASE64加密时出现的换行符 <功能详细描述>
	 * 
	 * @param str
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private static String filter(String str) {
		String output = null;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			int asc = str.charAt(i);
			if (asc != 10 && asc != 13) {
				sb.append(str.subSequence(i, i + 1));
			}
		}
		output = new String(sb);
		return output;
	}

	/**
	 * MD5 加密
	 */
	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance(MD5);
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			} else {
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
		}

		return md5StrBuff.toString();
	}

	/**
	 * 加密
	 * 
	 * @param encData
	 *            要加密的数据
	 * @param secretKey
	 *            密钥 ,16位的数字和字母
	 * @param vector
	 *            初始化向量,16位的数字和字母
	 * @return
	 * @throws Exception
	 */
	// public static String Encrypt(String encData, String secretKey, String
	// vector) throws Exception {
	// if (secretKey == null) {
	// return null;
	// }
	// if (secretKey.length() != 16) {
	// return null;
	// }
	// byte[] raw = secretKey.getBytes();
	// SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	// Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//
	// "算法/模式/补码方式"
	// IvParameterSpec iv = new IvParameterSpec(vector.getBytes());//
	// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
	// cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
	// byte[] encrypted = cipher.doFinal(encData.getBytes());
	// return ObjectSerializer.encodeBytes(encrypted);
	// }

	/**
	 * 生成签名数据
	 * 
	 * @param data
	 *            待加密的数据
	 * @param key
	 *            加密使用的key
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] getSignature(String data, String key) throws NoSuchAlgorithmException, InvalidKeyException {
		byte[] keyBytes = key.getBytes();
		SecretKeySpec signingKey = new SecretKeySpec(keyBytes, HMAC_SHA1);
		Mac mac = Mac.getInstance(HMAC_SHA1);
		mac.init(signingKey);
		// byte[] rawHmac = mac.doFinal(("GET&"+data).getBytes());
		byte[] rawHmac = mac.doFinal((data).getBytes());
		/*
		 * StringBuilder sb=new StringBuilder(); for(byte b:rawHmac){
		 * sb.append(byteToHexString(b)); }
		 */
		return rawHmac;
	}

	private static String byteToHexString(byte ib) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		char[] ob = new char[2];
		ob[0] = Digit[(ib >>> 4) & 0X0f];
		ob[1] = Digit[ib & 0X0F];
		String s = new String(ob);
		return s;
	}

	/**
	 * 创富md5加密方法
	 */
	public static String encode(String encodestr) {
		try {
			char[] hexDigits = { '9', '0', '1', '4', 'g', '2', 'a', '5', 'p', '6', 'l', 'u', '7', '8', '3', 'e' };
			byte[] strTemp = encodestr.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char[] str = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];
				str[(k++)] = hexDigits[(byte0 & 0xF)];
			}
			return new String(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(encode("yituke" + "abc"));
		System.out.println(getMD5Str("yituke" + "abc"));
		
		String content = "这是一次测试AES加密";
        String password = "qbj9mkrqza2svcw0";//getRandomString(16);//"821455a3-58ce-4965-ae08-3e6b0d090fae";//UUID.randomUUID().toString();
        System.out.println("加密的密钥：" + password);
        System.out.println("加密之前的内容：" + content);

        // 加密
        String contant = encryptAES(content, password);
        System.out.println("转码后的内容：" + contant);
        //System.out.println("加密的key：" + retMap.get("key").toString());

        // 解密
        String decrypt = encryptAES(contant, password);
        System.out.println("解密后的内容：" + decrypt);

	}
}
