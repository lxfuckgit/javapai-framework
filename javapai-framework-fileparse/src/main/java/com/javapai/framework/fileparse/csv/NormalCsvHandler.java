package com.javapai.framework.fileparse.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public final class NormalCsvHandler extends CsvHandler<String> {

	@Override
	public List<String> readFile(File file) {
		// TODO Auto-generated method stub
		if (!file.exists() && file.isFile()) {
			System.out.println("文件不存在!");
			return null;
		}

		// 这里要统一编码
		InputStreamReader read = null;
		try {
			read = new InputStreamReader(new FileInputStream(file), getCharset());
			return readFile(read, ",");
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				read.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<String> readFile(String filePath) {
		// TODO Auto-generated method stub
		try {
			// 这里，可能会乱码。
			// return readFile(new java.io.FileReader(filePath), null);
			return readFile(new InputStreamReader(new FileInputStream(filePath), getCharset()), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<String> readFile(InputStream stream) {
		// TODO Auto-generated method stub
		try {
			/* 字节流 -> 字符流 */
			return readFile(new InputStreamReader(stream, getCharset()), ",");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param reader
	 * @param spiltStr CSV格式文件的数据分隔符，默认(",");
	 * @return
	 */
	private List<String> readFile(Reader reader,String spiltStr) {
//	private List<String> readFile(InputStreamReader isr, String spiltStr) {
		List<String> list = new ArrayList<>();
		//用缓冲区类型字符流包装流数据，方便分批读取。
		BufferedReader br = new BufferedReader(reader);
//		reader.readLine();// 第一行信息，为标题信息，不用,如果需要，注释掉
		if (null == spiltStr || spiltStr.length() <= 0) {
			spiltStr = ",";
		}
		
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				list.add(line);
				String item[] = line.split(spiltStr);
				String last = item[item.length - 1];// 这就是你要的数据了
				// int value = Integer.parseInt(last);//如果是数值，可以转化为数值
				System.out.println(last);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
}
