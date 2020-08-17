package com.javapai.framework.fileparse.excel;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.javapai.framework.config.TableFormat;
import com.javapai.framework.fileparse.excel.config.SheetConfig;

/**
 * 
 * @author pooja
 *
 */
// public abstract class POIExcelReader {
public interface POIExcelReader extends ExcelReader {
	public List<TableFormat> readSheet(Workbook workBook);

	/**
	 * POI通过默认配置读取并解析Sheet工作表的行集数据.<br>
	 * 
	 * 注意：如需要对sheet按指定配置进行解析，有两种配置方式：<br>
	 * 1、对ExcelReader实例对象( 例如new ExcelReader(new SheetConfig()); )进行config配置(所有sheet共享).<br>
	 * 2、对ExcelReader实例方法( 例如{@link ExcelReader#readSheet(Sheet, SheetConfig)}; )进行config配置(当前sheet独享).<br>
	 * 
	 * @param sheet
	 *            excel工作表。<br>
	 */
	public TableFormat readSheet(Sheet sheet);
//	protected abstract TableFormat readSheet(Sheet sheet);

	/**
	 * POI通过指定配置读取并解析Sheet工作表的行集数据.<br>
	 * 
	 * @param sheet
	 *            sheet表单。<br>
	 * @param config
	 *            sheet配置。<br>
	 * @return TableFormat工作表对象.<br>
	 */
	public TableFormat readSheet(Sheet sheet, SheetConfig config);
//	protected abstract TableFormat readSheet(Sheet sheet, SheetConfig config);

	/**
	 * 读取指定表单的指定工作表.<br>
	 * 
	 * @param workBook
	 * @param sheetIndex
	 * @return
	 */
	public TableFormat readSheet(Workbook workBook, int sheetIndex);

	/**
	 * 按指定的配置信息读取指定表单的指定工作表.<br>
	 * 
	 * @param workBook
	 * @param sheetIndex
	 * @param config
	 * @return
	 */
	public TableFormat readSheet(Workbook workBook, int sheetIndex, SheetConfig config);

	/**
	 * 读取指定表单的指定工作表.<br>
	 * 
	 * @param workBook
	 * @param sheetName
	 * @return
	 */
	public TableFormat readSheet(Workbook workBook, String sheetName);

	public TableFormat readSheet(Workbook workBook, String sheetName, SheetConfig config);

}
