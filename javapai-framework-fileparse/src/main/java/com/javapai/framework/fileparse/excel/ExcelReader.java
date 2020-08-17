package com.javapai.framework.fileparse.excel;

import java.io.File;
import java.io.InputStream;

import com.javapai.framework.config.TableFormat;
import com.javapai.framework.fileparse.excel.config.SheetConfig;
import com.javapai.framework.fileparse.office.OfficeReader;

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
public interface ExcelReader extends OfficeReader<TableFormat> {
	public TableFormat readFile(File File, int sheetIndex);
	
	public TableFormat readSheet(File File, int sheetIndex, SheetConfig config);
	
	public TableFormat readSheet(InputStream strean, int sheetIndex);

	public TableFormat readSheet(InputStream strean, int sheetIndex, SheetConfig config);

	public TableFormat readSheet(InputStream strean, String sheetName);

	public TableFormat readSheet(InputStream strean, String sheetName, SheetConfig config);

}
