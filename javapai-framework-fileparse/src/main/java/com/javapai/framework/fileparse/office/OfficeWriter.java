package com.javapai.framework.fileparse.office;

import java.io.File;

public interface OfficeWriter<T> extends OfficeHandler {
	/**
	 * 
	 * @param file
	 * @param content
	 *            写入内容。格式怎么办？
	 * @return
	 */
	public boolean writeFile(File file, T content);

	/**
	 * 
	 * @param filepath
	 * @param content
	 *            写入内容。格式怎么办？
	 * @return
	 */
	public boolean writeFile(String filepath, T content);

	/**
	 * 
	 * 1：制作静态模版
-->代码生成。
麻烦、适合动态格式。

-->文件模版。
简单、适合固定格式。

2：准备动态数据

3：数据填充模版

4：导出到文件（pdf,html,word）
	 * 
	 * @param filepath
	 *            目标文件。<br>
	 * @param content
	 *            写入内容。
	 * @param tempalte
	 *            写入模版。<br>
	 * @return
	 */
	public boolean writeFile(String filepath, T content, ExcelTemplate tempalte);
}
