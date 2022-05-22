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
	 * 读取并解析指定文件对象。<br>
	 * 
	 * @param file
	 * @return 返回文件内容.<br>
	 */
	public T readFile(File file);

	/**
	 * 读取并解析指定路径文件。<br>
	 * 
	 * @param filePath
	 * @return 返回文件内容.<br>
	 */
	public T readFile(String filePath);
	
	/**
	 * 读取并解析指定输入流对象。<br>
	 * 
	 * @param stream
	 *            输入流对象可以是InputStream对象及其子类（例如：FileInputStream）.<br>
	 * @return 返回文件内容.<br>
	 */
	public T readFile(InputStream stream);

}
