package com.javapai.framework.fileparse.office;

/**
 * 
 * •HWPF － 提供读写Microsoft Word DOC格式档案的功能。
 * <p>
 * •XSSF － 提供读写Microsoft Excel OOXML XLSX格式档案的功能。
 * <p>
 * •HSLF － 提供读写Microsoft PowerPoint格式档案的功能。
 * <p>
 * •HDGF － 提供读Microsoft Visio格式档案的功能。
 * <p>
 * •HPBF － 提供读Microsoft Publisher格式档案的功能。
 * <p>
 * •HSMF － 提供读Microsoft Outlook格式档案的功能。
 * <p>
 * 
 * @author lx
 * 
 */
public interface OfficeHandler {
	/**
	 * 2003版本
	 */
	public static final int version2003 = 2003;

	/**
	 * 2007版本
	 */
	public static final int version2007 = 2007;

	/**
	 * 2010版本
	 */
	public static final int version2010 = 2010;
	
}
