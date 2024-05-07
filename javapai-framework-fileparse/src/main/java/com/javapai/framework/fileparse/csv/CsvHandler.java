package com.javapai.framework.fileparse.csv;

import com.javapai.framework.fileparse.DocReader;

/**
 * 
 * RFC 4180 formalized CSV.
 * 
 * 可用解析包：<br>
 * 1、纯手工解析。<br>
 * 2、Apache Commons CSV <br>
 * 3、OpenCSV<br>
 * 4、uniVocity-parsers和https://github.com/uniVocity/csv-parsers-comparison(抽象的csv解析组合)
 * 5、JavaCSV(2014-12-10后已不再更新) <br>
 *
 * @author pooja
 * 
 */
public abstract class CsvHandler<T> implements DocReader<T> {
//	private String title;// 文件名 or 文件标题.

	// @Override
	// public List<String> readFile(File file) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	// @Override
	// public List<String> readFile(String filePath) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	@Override
	public java.lang.String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}
	//
	// @Override
	// public java.lang.String getContent() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// public List<String> readFile() {
	//
	// }
	
	protected String getCharset() {
		byte[] b = new byte[3];
		if (b[0] == -17 && b[1] == -69 && b[2] == -65) {
			return "UTF-8";
		} else {
			return "GBK";
		}
	}

}
