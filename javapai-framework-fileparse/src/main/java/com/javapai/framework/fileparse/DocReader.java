package com.javapai.framework.fileparse;

import java.io.File;
import java.io.InputStream;

/**
 * 接口：文档读取器。<br>
 * 
 * @author pooja
 *
 * @param <T>
 */
public interface DocReader<T> {
	/**
	 * 取得文档标题
	 * 
	 * @return 文档标题
	 */
	public String getTitle();
	
	/**
	 * 解析指定文件.<br>
	 * 
	 * @param file
	 * @return
	 */
	public T readFile(File file);

	/**
	 * 解析指定路径文件.<br>
	 * 
	 * @param filePath
	 * @return
	 */
	public T readFile(String filePath);
	
	/**
	 * 解析指定流数据.<br>
	 * 
	 * @param stream
	 * @return
	 */
	public T readFile(InputStream stream);

}
