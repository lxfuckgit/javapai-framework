package com.javapai.framework.fileparse.office;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface OfficeReader<T> extends OfficeHandler {
	/**
	 * 读取指定的office文件对象。<br>
	 * 
	 * @param file
	 *            文件对象(office file object).<br>
	 * @return 返回文件内容.<br>
	 */
	public List<T> readFile(File file);

	/**
	 * 读取指定的office文件流。 <br>
	 * 
	 * @param stream
	 *            文件流可以是InputStream对象及其子类（例如：FileInputStream）.<br>
	 * @return 返回文件内容.<br>
	 */
	public List<T> readFile(InputStream stream);
}
