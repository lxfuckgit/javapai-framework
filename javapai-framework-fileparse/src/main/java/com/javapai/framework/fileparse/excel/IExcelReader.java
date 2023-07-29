package com.javapai.framework.fileparse.excel;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.javapai.framework.config.TableFormat;
import com.javapai.framework.fileparse.DocReader;
import com.javapai.framework.fileparse.excel.config.ReadSheetConfig;

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
 * @author pooja
 * 
 */
public interface IExcelReader extends DocReader<List<TableFormat>> {
	/**
	 * 读取Excel的指定表单。<br>
	 * 
	 * @param file       文件句柄。
	 * @param sheetIndex 索引号。
	 * @return
	 */
	public TableFormat readSheet(File file, int sheetIndex);

	/**
	 * 
	 * @param file
	 * @param sheetName
	 * @return
	 * 
	 * @deprecated 建议采用{@link IExcelReader#Sheet(File file, int sheetIndex)}代替。
	 */
	public TableFormat readSheet(File file, String sheetName);

	/**
	 * 读取Excel的指定表单。<br>
	 * 
	 * @param file       文件句柄。
	 * @param sheetIndex 索引号。
	 * @param config     读取配置项。
	 * @return
	 */
	public TableFormat readSheet(File file, int sheetIndex, ReadSheetConfig config);

	/**
	 * 
	 * @param file
	 * @param sheetName
	 * @param config
	 * @return
	 * @deprecated 建议采用{@link IExcelReader#Sheet(File file, int sheetIndex, ReadSheetConfig config)}代替。
	 */
	public TableFormat readSheet(File file, String sheetName, ReadSheetConfig config);

	/**
	 * 指取指定input输入流中的指定sheent表单。<br>
	 * 
	 * @param inputStream 输入流。<br>
	 * @param sheetIndex  表单序号（默认第1个表单序号为1）。<br>
	 * @return 格式化后的表单内容。
	 */
	public TableFormat readSheet(InputStream inputStream, int sheetIndex);

	/**
	 * 
	 * @param inputStream
	 * @param sheetName
	 * @return
	 * 
	 * @deprecated 建议采用{@link IExcelReader#Sheet(InputStream inputStream, int sheetIndex)}代替。
	 */
	public TableFormat readSheet(InputStream inputStream, String sheetName);

	/**
	 * 
	 * @param inputStream
	 * @param sheetIndex
	 * @param config
	 * @return
	 */
	public TableFormat readSheet(InputStream inputStream, int sheetIndex, ReadSheetConfig config);

	/**
	 * 
	 * @param inputStream
	 * @param sheetName
	 * @param config
	 * @return
	 * 
	 * @deprecated 建议采用{@link IExcelReader#Sheet(InputStream inputStream, int sheetIndex, ReadSheetConfig config)}代替。
	 */
	public TableFormat readSheet(InputStream inputStream, String sheetName, ReadSheetConfig config);

	// 所有带SheetConfig参数的方法都采用实例绑类的方式：例如：Xxxx xx = new Xxx(SheetConfig config);
	/**
	 * 读取Excel的所有表单。<br>
	 * 
	 * @param file   文件句柄。
	 * @param config
	 * @return
	 */
	public List<TableFormat> readFile(File file, ReadSheetConfig config);
	
	/**
	 * 指取指定input输入流中的所有表单。<br>
	 * 
	 * @param inputStream 输入流。<br>
	 * @param config
	 * @return
	 */
	public List<TableFormat> readFile(InputStream inputStream, ReadSheetConfig config);

	/**
	 * 返回默认sheet索引标识号。<br>
	 * 
	 * @param sheetIndex 用户指定sheet索引标识号。<br>
	 *                   <strong>注意：</strong>如果sheetIndex参数未输入或输入小于1，则默认sheetIndex=1；原因是友好的面向使用者。<br>
	 * @return sheet索引标识号。<br>
	 */
	default int getDefaultSheetIndex(int sheetIndex) {
		return sheetIndex <= 0 ? 1 : sheetIndex;
	};

}
