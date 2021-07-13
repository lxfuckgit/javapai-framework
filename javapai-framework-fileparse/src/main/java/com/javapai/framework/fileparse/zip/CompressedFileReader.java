package com.javapai.framework.fileparse.zip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZipReader extends AbstractZipReader {
	protected static Logger log = LoggerFactory.getLogger(ZipReader.class);

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> readFile(File file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> readFile(String filePath) {
		// TODO Auto-generated method stub
		File file = new File(filePath);
		if (!file.exists()) {
			log.warn(filePath + "所指文件不存在!");
			return null;
		}
//		if (!file.isDirectory()) {
			//zip file is not directory."
//			return null;
//		}

		List<String> datas = new ArrayList<>();
		try {
			ZipFile zip = new ZipFile(file);
			Enumeration<?> entries = zip.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				datas.add(entry.getName());
			}
			zip.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return datas;
	}

	@Override
	public List<String> readFile(InputStream stream) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void unZip(File file) {
		// TODO Auto-generated method stub
		if (!file.exists()) {
			// File.getPath() + "所指文件不存在"
			return;
		}

		// 创建压缩文件对象
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 开始解压
		Enumeration<?> entries = zipFile.entries();
		while (entries.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			// 如果是文件夹，就创建个文件夹
			if (entry.isDirectory()) {
				file.mkdirs();
			} else {
				// 如果是文件，就先创建一个文件，然后用io流把内容copy过去
				File targetFile = new File(file.getAbsolutePath().replace(".zip", "") + "/" + entry.getName());
				// 保证这个文件的父文件夹必须要存在
				if (!targetFile.getParentFile().exists()) {
					targetFile.getParentFile().mkdirs();
				}
				try {
					targetFile.createNewFile();
					// 将压缩文件内容写入到这个文件中
					InputStream is = zipFile.getInputStream(entry);
					FileOutputStream fos = new FileOutputStream(targetFile);
					int len;
					byte[] buf = new byte[1024];
					while ((len = is.read(buf)) != -1) {
						fos.write(buf, 0, len);
					}
					// 关流顺序，先打开的后关闭
					fos.close();
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void unZip(String filePath) {
		// TODO Auto-generated method stub
		unZip(new File(filePath));
	}

	@Override
	public void unZip(InputStream stream) {
		// TODO Auto-generated method stub

	}

}
