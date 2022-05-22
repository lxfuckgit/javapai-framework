package com.javapai.framework.fileparse.excel;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.javapai.framework.config.TableFormat;
import com.javapai.framework.fileparse.DocReader;
import com.javapai.framework.fileparse.excel.config.SheetConfig;

/**
 * 定义Office-Excel文件的读取时的基本行为方式。<br>
 * 
 * <p>
 * 当解析某类复杂的Excel时，如果需要对excel的解析内容很精确， 可以采用此类方案，对整个Excel进行详细控制解析。<br>
 * <br>
 * <p>
 * 
 * Excel解析过程(按内容结构解析):<br>
 * 1：读取整个Excel文件。<br>
 * 2：读取文件中每个sheet表单。<br>
 * 3：读取sheet表单中数据信息。<br>
 * 3.1:表头标题。<br>
 * 3.2:表体内容。 <br>
 * 3.3:落款备注。<br>
 * 
 * @author lx
 * @author pooja
 * 
 */
public interface IExcelReader extends DocReader<List<TableFormat>> {
	/**
	 * 
	 * @param file
	 * @param sheetIndex
	 * @return
	 */
	public TableFormat readSheet(File file, int sheetIndex);

	/**
	 * 
	 * @param file
	 * @param sheetIndex
	 * @param config
	 * @return
	 */
	public TableFormat readSheet(File file, int sheetIndex, SheetConfig config);

	/**
	 * 
	 * @param file
	 * @param config
	 * @return
	 */
	// public TableFormat readSheet(File file, SheetConfig config);

	/**
	 * 指取指定input输入流中的指定sheent表单。<br?
	 * 
	 * @param inputStream
	 *            输入流。<br>
	 * @param sheetIndex
	 *            表单序号（默认第1个表单序号为1）。<br>
	 * @return 格式化后的表单内容。
	 */
	public TableFormat readSheet(InputStream inputStream, int sheetIndex);

	public TableFormat readSheet(InputStream inputStream, int sheetIndex, SheetConfig config);

	public TableFormat readSheet(InputStream inputStream, String sheetName);

	public TableFormat readSheet(InputStream inputStream, String sheetName, SheetConfig config);

	// 未开放原因:通过类实例来绑定config，再调用：readFile(File file)即可。
	// public java.util.List<TableFormat> readFile(File file, SheetConfig
	// config);

	/**
	 * 返回默认sheet索引标识号。<br>
	 * 
	 * @param sheetIndex
	 *            用户指定sheet索引标识号。<br>
	 *            <strong>注意：</strong>如果sheetIndex参数未输入或输入小于1，则默认sheetIndex=1；原因是友好的面向使用者。<br>
	 * @return sheet索引标识号。<br>
	 */
	default int getDefaultSheetIndex(int sheetIndex) {
		return sheetIndex <= 0 ? 1 : sheetIndex;
	};

}
