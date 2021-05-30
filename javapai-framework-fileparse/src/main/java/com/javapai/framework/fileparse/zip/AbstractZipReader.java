package com.javapai.framework.fileparse.zip;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.javapai.framework.fileparse.DocReader;

public abstract class AbstractZipReader implements DocReader<List<String>> {
	/**
	 * 解析zip文件到相对目录.<br>
	 * 
	 * @param file
	 */
	public abstract void unZip(File file);

	public abstract void unZip(String filePath);

	public abstract void unZip(InputStream stream);
}
