package com.javapai.framework.fileparse.excel.poi;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javapai.framework.fileparse.excel.IExcelReader;
import com.javapai.framework.fileparse.excel.config.ReadSheetConfig;

/**
 * 利用POI策略去实现Excel的数据读取操作。<br>
 * <br>
 * POI核心类关系：<br>
 * Excel文件：Workbook、HSSFWorkbook(97~03版本)、XSSFWorkbook(07+版本)<br>
 * <br>
 * 针对XSSFWorkbook读取Excel有两种模式：XSSFWorkbook模式 和SXSSFWorkbook模式。<br>
 * <br>
 * 第一种模式XSSFWorkbook，此方式仅适用与小数据量的poi操作； <br>
 * 第二种模式SXSSFWorkbook，适用与较大数据量的poi写入操作。 [POI3.8版本新增加].<br>
 * <br>
 * Excel表单：Sheet、HSSFSheet、XSSFSheet<br>
 * Excel数据行：Row、HSSFRow、XSSFRow<br>
 * Excel单无格类：Cell、HSSFCell、XSSFCell<br>
 * 
 * @author pooja
 *
 */
public abstract class POIExcelReader<T> implements IExcelReader<T> {
	protected static Logger log = LoggerFactory.getLogger(POIExcelReader.class);
	
	/**
	 * 解析workBook工作表对象的所有sheet表单数据。<br>
	 * 
	 * @param workBook
	 * @return
	 */
	public abstract List<T> readSheet(Workbook workBook);

	/**
	 * POI通过默认配置读取并解析Sheet工作表的行集数据.<br>
	 * 
	 * 注意：如需要对sheet按指定配置进行解析，有两种配置方式：<br>
	 * 1、对ExcelReader实例对象( 例如new ExcelReader(new ReadSheetConfig()); )进行config配置(所有sheet共享).<br>
	 * 2、对ExcelReader实例方法( 例如{@link OfficeExcelReader#readSheet(Sheet, ReadSheetConfig)}; )进行config配置(当前sheet独享).<br>
	 * 
	 * @param sheet
	 *            excel工作表。<br>
	 */
	protected abstract T readSheet(Sheet sheet);

	/**
	 * POI通过指定配置读取并解析Sheet工作表的行集数据.<br>
	 * 
	 * @param sheet
	 *            sheet表单。<br>
	 * @param config
	 *            sheet配置。<br>
	 * @return TableFormat工作表对象.<br>
	 */
	protected abstract T readSheet(Sheet sheet, ReadSheetConfig config);

	/**
	 * 读取指定表单的指定工作表.<br>
	 * 
	 * @param workBook
	 *            表单对象。<br>
	 * @param sheetIndex
	 *            表单索引号。<br>
	 * @return 当前表单内容。<br>
	 * 
//	 * @deprecated 过期原因：<br>
//	 *             1:建议用户通过File对象或是InputStream对象得到一个WorkBook对象，而非直接传入一个WorkBook对象。<br>
//	 *             如果用户能直接拿到workBook对象后
	 */
	public abstract T readSheet(Workbook workBook, int sheetIndex);

	/**
	 * 按指定的配置信息读取指定表单的指定工作表.<br>
	 * 
	 * @param workBook
	 * @param sheetIndex
	 * @param config
	 * @return 当前表单内容。<br>
	 */
	public abstract T readSheet(Workbook workBook, int sheetIndex, ReadSheetConfig config);

	/**
	 * 读取指定表单的指定工作表.<br>
	 * 
	 * @param workBook
	 * @param sheetName
	 * @return
	 */
	public abstract T readSheet(Workbook workBook, String sheetName);

	/**
	 * 读取指定表单的指定工作表.<br>
	 * 
	 * @param workBook
	 * @param sheetName
	 * @param config
	 * @return
	 */
	public abstract T readSheet(Workbook workBook, String sheetName, ReadSheetConfig config);
	
	protected boolean isEmptyRow(Row row) {
		// TODO Auto-generated method stub
		if (null == row) {
			log.info(">>>发现一个空行数据对象!");
			return true;
		}

		/*
		 * int cells = 0;// 空单元格记数器。 
		 * Iterator<Cell> iter = row.cellIterator();
		 * while (iter.hasNext() && iter.next().getCellType() == Cell.CELL_TYPE_BLANK) { cells++; } 
		 * return cells == (row.getLastCellNum() - row.getFirstCellNum());
		 */

		Iterator<Cell> iter = row.cellIterator();
		while (iter.hasNext()) {
			Cell cell = iter.next();
			if (null != readCell(cell) && !"".equals(readCell(cell))) {
				return false;
			}
		}
		return true;
	}
	
	protected Map<Integer, Object> readRow(Row row) {
		return readRow(row, 0, 0);
	}
	
	protected Map<Integer, Object> readRow(Row row, int xPosition, int yPosition) {
//		short lastCell = row.getLastCellNum(); //获取最后一个不为空的列的索引值.
		short firstCell = row.getFirstCellNum();// 获取第一个不为空的列的索引值.
		int totalCells = row.getPhysicalNumberOfCells(); // 获取不为空的列的个数。
		log.info(">>>批注：正在处理Excel表单第" + row.getRowNum() + "行数据!");
		log.info(">>>本行数据开始于[" + firstCell + "]列结束于[" + row.getLastCellNum() + "]列，其中有效数据列共计[" + totalCells + "]列!");

		// java.text.DecimalFormat df = new DecimalFormat("#");
		Map<Integer, Object> datamap = new LinkedHashMap<Integer, Object>();

		/* 如果是第一行，我们当表头行处理，其它行当数据行处理 */
		if (row.getRowNum() <= 0 || row.getRowNum() == 0) {
			for (int index = firstCell - xPosition; index < totalCells - firstCell; index++) {
				// if (row.getCell(index).getStringCellValue().trim().length() == 0) {
				if (null == row.getCell(index) || null == row.getCell(index).getStringCellValue()) {
					// throw new IOException("列名不允许为空");
					datamap.put(index + yPosition, "列[" + index + "]");
				} else {
					datamap.put(index + yPosition, row.getCell(index).getStringCellValue().trim());
				}
			}
		} else if (row.getRowNum() >= 1 && firstCell >= 0) {// 加条件是因为有些情况下，firstCell为-1.
			//for (int index = firstCell; index < totalCells; index++) {
			for (int index = firstCell - xPosition; index < row.getLastCellNum(); index++) {
				Object fieldValue = readCell(row.getCell(index));
				datamap.put(index + yPosition, fieldValue);
			}
		}

		return datamap;
	}
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 返回单元格内容.<br>
	 * 
	 * @param cell
	 * @return
	 */
	private Object readCell(Cell cell) {
		if (null == cell) {
			// row.getLastCellNum()与row.getPhysicalNumberOfCells()不相等时，会出现此类情况.
			return "";
			// return null; // 这里不返回NULL了，后面也就不用进行java.util.Objects.isNULL()判断；
		}
		
		Object cellValue = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
//				if ("yyyy-MM-dd".equals(cell.getCellStyle().getDataFormatString())) {
				if (cell.getCellStyle().getDataFormat() == 164) {
					// 164包含：yyyy-mm-dd、yyyy-MM-dd、yyyy/MM/dd等等。
					cellValue = sdf.format(cell.getDateCellValue());
				} else {
					cellValue = cell.getDateCellValue();
				}
			} else {
				// cellValue = cell.getNumericCellValue();
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String temp = cell.getStringCellValue();
				// 判断是否包含小数点，如果不含小数点，则以字符串读取，如果含小数点，则转换为Double类型的字符串
				if (temp.indexOf(".") > -1) {
					cellValue = String.valueOf(new Double(temp)).trim();
				} else {
					cellValue = temp.trim();
				}
			}
			break;

		// if string(cell.getCellType() == 1)
		case Cell.CELL_TYPE_STRING:
			cellValue = cell.getStringCellValue().trim();
			break;

		// if boolean(cell.getCellType() == 4)
		case Cell.CELL_TYPE_BOOLEAN:
			cellValue = Boolean.toString(cell.getBooleanCellValue());
			break;

		// if boolean(cell.getCellType() == 2)
		case Cell.CELL_TYPE_FORMULA:
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cellValue = cell.getStringCellValue();
			if (cellValue != null) {
				//cellValue = cellValue.replaceAll("#N/A", "").trim();
			}
			break;
		// case XSSFCell.CELL_TYPE_FORMULA:
		// cellType = cell.getCachedFormulaResultType();
		// if (cellType == XSSFCell.CELL_TYPE_NUMERIC) {
		// result = Double.valueOf(cell.getRawValue());
		// } else {
		// result = cell.getCellFormula();
		// }
		// break;
		case Cell.CELL_TYPE_BLANK:
			break;
			
		case Cell.CELL_TYPE_ERROR:
			break;
			
		default:
			break;
		}
		
		return cellValue;
	}
	
	protected static Workbook getWorkbook(File file) {
		if (!file.isFile()) {
			log.error(">>>not found the file,please check it!");
			return null;
		} else {
			log.info(">>>正在处理文件" + file.getAbsolutePath());
			// file.encoding的属性值就是main方法所在类文件的编码。
			// log.info(">>>当前文件编码格式：" + System.getProperty("file.encoding"));
			try {
				return WorkbookFactory.create(file);
			} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
				e.printStackTrace();
				return null;
			}
		}

		/*
		Workbook wb = null;
		try {
			InputStream is = new java.io.FileInputStream(file);
			// InputStream is = new FileInputStream(file.getAbsolutePath());

			// 经过判断得理过的wb对象在后续处理Sheet、Row、Cell统统用接口实现，就不用关心03(HSSFCell)与07(XSSFCell)的格式差别
			if (file.getName().endsWith(".xls")) {
				wb = new HSSFWorkbook(is);
				log.info(">>>检测到当前Excel Version is 2003!");
			} else if (file.getName().endsWith(".xlsx")) {
				wb = new XSSFWorkbook(is);
				// wb = new XSSFWorkbook(file.getAbsolutePath());
				log.info(">>>检测到当前Excel Version is 2007!");
			} else {
				// WorkbookFactory.create方法能自动匹配excel版本号
				wb = WorkbookFactory.create(is);
			}
		} catch (java.io.FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		return wb;
		*/
	}

	public static Workbook getWorkbook(InputStream input) {
		Workbook wb = null;
		try {
			// 能兼容excel03和excel07版本.
			wb = WorkbookFactory.create(input);
			log.info("ready go......");
			log.info("共发现" + wb.getNumberOfSheets() + "张sheet表单......：");
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
			return null;
		}
		return wb;
	}
	
	/**
	 * POI检查EXCEL版本
	 * 
	 * @param is
	 * @return
	 */
	public static boolean isExcel2003(InputStream is) {
		try {
			HSSFWorkbook hssf = new HSSFWorkbook(is);
			hssf.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	/**
	 * POI检查EXCEL版本
	 * 
	 * @param is
	 * @return
	 */
	public static boolean isExcel2007(InputStream is) {
		try {
			XSSFWorkbook xssf = new XSSFWorkbook(is);
			xssf.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	

}
