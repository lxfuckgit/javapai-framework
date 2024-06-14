package com.javapai.framework.fileparse.csv;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

/**
 * 提示：使用时需要自行导入OpenCsv所需要的依赖包.<br>
 * 
 * @author pooja
 *
 */
public final class OpenCsvHandler extends CsvHandler<String[]> {
	protected static Logger logger = LoggerFactory.getLogger(OpenCsvHandler.class);

	@Override
	public List<String[]> readFile(File file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> readFile(String filePath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> readFile(InputStream stream) {
		return readFile(stream, "GB2312", 0);
	}
	
	/**
	 * 读取并解析指定输入流对象。<br>
	 * 
	 * @param stream      数据流。<br>
	 * @param maxReadSize 读取行数(0代表全量读取)。<br>
	 * @return
	 */
	public List<String[]> readFile(InputStream stream, int maxReadSize) {
		return readFile(stream, "GB2312", maxReadSize);
	}
	
	/**
	 * 读取并解析指定输入流对象。<br>
	 * 
	 * @param stream  数据流。<br>
	 * @param charset 字符集(默认字符集=GB2312)。<br>
	 * @return
	 */
	public List<String[]> readFile(InputStream stream, String charset) {
		return readFile(stream, charset, 0);
	}
	
	/**
	 * 读取并解析指定输入流对象。<br>
	 * 
	 * @param stream      数据流。<br>
	 * @param charset     字符集(默认字符集=GB2312)。<br>
	 * @param maxReadSize 读取行数(0代表全量读取)。<br>
	 * @return
	 */
	public List<String[]> readFile(InputStream stream, String charset, int maxReadSize) {
		List<String[]> csvList = null;
		CSVReader reader = null;
		try {
			if (null == charset || charset.length() == 0) {
				charset = "GB2312";
			}
			
			//com.opencsv.CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
			//reader = new CSVReaderBuilder(new InputStreamReader(stream, charset)).withCSVParser(parser).build();
			reader = new CSVReaderBuilder(new InputStreamReader(stream, charset)).build();
			if (maxReadSize > 0) {
				csvList = new LinkedList<>();
				int initSize = 0;
				String[] record = null;
				while ((record = reader.readNext()) != null && initSize < maxReadSize) {
					initSize++;
					csvList.add(record);
				}
			} else {
				csvList = reader.readAll();
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("--->文件字符集解析异常：", e);
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return csvList;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

}
