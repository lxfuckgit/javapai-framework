package com.javapai.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

/**
 * 工具类：文件写入。<br>
 * <br>
 * <strong>提示：</strong> <br>
 * 依赖三方apache-commons-io.jar包。
 * 
 */
public final class UtilFileWriter {
	/**
	 * 将指定的数据流内容写入到指定文件中.<br>
	 * <br>
	 * 
	 * <strong>提示：</strong>inputStream流在写入完成后会关闭。
	 * 
	 * @param inputStream 数据流.<br>
	 * @param targetFile  目标文件.<br>
	 * @return
	 */
	public static boolean writeToFile(InputStream inputStream, File targetFile) {
		FileOutputStream out = null;
		try {
			byte[] bs = IOUtils.toByteArray(inputStream);
			out = new FileOutputStream(targetFile, true);
			// 拷贝数据到新文件
			IOUtils.write(bs, out);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (null != inputStream) {
				try {
					inputStream.close();
					inputStream = null;
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
			if (null != out) {
				try {
					out.close();
					out = null;
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 将指定文件复制到指定文件夹中。<br>
	 * 
	 * @param file    原文件
	 * @param dirPath 保存目录
	 * @return 操作结果boolean值；
	 */
	public static boolean copyFileToDir(File file, String dirPath) {
		try {
			File targetFile = new File(dirPath);
			return writeToFile(new FileInputStream(file), targetFile);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
