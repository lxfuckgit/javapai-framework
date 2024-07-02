package com.javapai.framework.fileparse.excel.poi;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javapai.framework.fileparse.excel.IExcelReader;
import com.javapai.framework.fileparse.excel.config.ReadSheetConfig;

/**
 * 利用POI策略去实现Excel的数据读取操作。<br>
 * <br>
 * 
 * <strong>POI读取Excel模式：</strong><br>
 * <br>
 * <strong>1、老版的xls格式读取方式：</strong><br>
 * <br>
 * HSSFWorkbook<br>
 * <br>
 * <strong>2、新版的xlsx格式读取方式：</strong><br>
 * <br>
 * XSSFWorkbook<br>
 * <br>
 * 
 * <strong>3、新版的xlsx格式（大数据量）读取方式：</strong><br>
 * <br>
 * SXSSFWorkbook<br>
 * <br>
 * 
 * 
 * 
 * POI核心类关系：<br>
 * Excel文件：Workbook、(97~03版本)、XSSFWorkbook(07+版本)<br>
 * <br>
 * 针对XSSFWorkbook读取Excel有两种模式：XSSFWorkbook模式 和模式。<br>
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
	 * 字符串常量-yyyy/mm/dd
	 */
	private static final String yyyymmdd3 = "yyyy/mm/dd";
	
	/**
	 * 格式化（yyyy-MM-dd）
	 */
	protected static SimpleDateFormat sdf1_yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 格式化（yyyy/MM/dd）
	 */
	protected static SimpleDateFormat sdf2_yyyy_MM_dd = new SimpleDateFormat("yyyy/MM/dd");
	
	/**
	 * 格式化（MM/dd/yyyy）
	 */
	protected static SimpleDateFormat sdf3_MM_dd_yyyy = new SimpleDateFormat("MM/dd/yyyy");
	
	/**
	 * 格式化（yyyy/MM/dd HH:mm:ss）
	 */
	protected static SimpleDateFormat SDF4 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	/**
	 * 格式化（yyyy-MM-dd HH:mm:ss）
	 */
	protected static SimpleDateFormat SDF5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 通用格式化（HH:mm:ss）
	 */
	protected static DateTimeFormatter SDF1_HH_mm_ss = DateTimeFormatter.ofPattern("[]H:[]m:[]s");
	
	/**
	 * 通用格式化（HH:mm:ss）
	 */
	protected static DateTimeFormatter SDF2_HH_mm_ss = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	/**
	 * 格式化（hh:mm:ss）
	 */
//	protected static DateTimeFormatter sdf1_hh_mm_ss = DateTimeFormatter.ofPattern("hh:mm:ss");
	
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
	public abstract T readSheet(Sheet sheet);

	/**
	 * POI通过指定配置读取并解析Sheet工作表的行集数据.<br>
	 * 
	 * @param sheet
	 *            sheet表单。<br>
	 * @param config
	 *            sheet配置。<br>
	 * @return TableFormat工作表对象.<br>
	 */
	public abstract T readSheet(Sheet sheet, ReadSheetConfig config);

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
		log.debug(">>>批注：正在处理Excel表单第" + row.getRowNum() + "行数据!");
		log.debug(">>>本行数据开始于[" + firstCell + "]列结束于[" + row.getLastCellNum() + "]列，其中有效数据列共计[" + totalCells + "]列!");

		Map<Integer, Object> datamap = new LinkedHashMap<Integer, Object>();

		/* 如果是第一行，我们当表头行处理，其它行当数据行处理 */
		if (row.getRowNum() <= 0 || row.getRowNum() == 0) {
			for (int index = firstCell - xPosition; index < row.getLastCellNum(); index++) {
//			for (int index = firstCell - xPosition; index < totalCells - firstCell; index++) {//totalCells - firstCell无法处理第1列为空第2列有值的情况。
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
	
	/**
	 * 返回单元格内容.<br>
	 * 
	 * 关于单元格是日期时的格式说明(基于POI.5.2.x版本)： <br>
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
		if (CellType.NUMERIC.equals(cell.getCellType())) {
			if (DateUtil.isCellDateFormatted(cell)) {
//				if ("yyyy-MM-dd".equals(cell.getCellStyle().getDataFormatString())) {
				if (cell.getCellStyle().getDataFormat() == 164) {
					// 164包含：yyyy-mm-dd、yyyy-MM-dd、yyyy/MM/dd等等。
					cellValue = sdf1_yyyy_MM_dd.format(cell.getDateCellValue());
				} else if ("yyyy-mm-dd;@".equals(cell.getCellStyle().getDataFormatString())) {
					// 177包含：yyyy-mm-dd;@
					cellValue = sdf1_yyyy_MM_dd.format(cell.getDateCellValue());
				} else if (179 == cell.getCellStyle().getDataFormat()) {
					// 179包含：yyyy\-mm\-dd\ hh:mm:ss
					cellValue = SDF5.format(cell.getDateCellValue());
				} else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
					// 14包含：m/d/yy、dd/mm/yyyy等等。
					//cellValue = sdf3_MM_dd_yyyy.format(cell.getDateCellValue());
					//我也不晓得为什么excel格式设置是m/d/yy（poi返回格式）但excel显示是yyyy_MM_dd，操作系统差异？
					cellValue = sdf2_yyyy_MM_dd.format(cell.getDateCellValue());
				} else if ("m/d/yy h:mm".equals(cell.getCellStyle().getDataFormatString())) {
					//22包含：m/d/yy h:mm
					if (22 == cell.getCellStyle().getDataFormat()) {
						cellValue = SDF4.format(cell.getDateCellValue());
					} else {
						cellValue = cell.getDateCellValue();
					}
				} else if (yyyymmdd3.equals(cell.getCellStyle().getDataFormatString())) {
					// 提示：yyyy/mm/dd格式时cell.getCellStyle().getDataFormat()可能是176、178，所以此判断需要前置。
					cellValue = sdf2_yyyy_MM_dd.format(cell.getDateCellValue());
				} else if (cell.getCellStyle().getDataFormat() == 176) {
					// 176包含：yyyy\\-mm\\-dd、yyyy/mm/dd等等。
					if("yyyy\\/mm\\/dd".equals(cell.getCellStyle().getDataFormatString())) {
						cellValue = sdf2_yyyy_MM_dd.format(cell.getDateCellValue());
					} else {
						cellValue = sdf1_yyyy_MM_dd.format(cell.getDateCellValue());
					}
				} else if ("h:mm:ss".equals(cell.getCellStyle().getDataFormatString())) {
					java.time.Instant instant = cell.getDateCellValue().toInstant();
					LocalTime time = instant.atOffset(ZoneOffset.ofHours(8)).toLocalTime();
					//在使用OffsetDateTime表示时间时，会遇到一个问题：当时间为 0 秒时，OffsetDateTime 没有显示秒时间的能力。例如：15:55:00 只会显示15:55；
					if (time.toString().length() < 7) {
						cellValue = SDF2_HH_mm_ss.format(time);
					} else {
						cellValue = time.toString();
					}
				} else {
					cellValue = cell.getDateCellValue();
				}
			} else {
				// 对当前单元格当“常规”的类型处理；
				/* POI3.8.x处理方法 */
//				cellValue = cell.getNumericCellValue();
//				cell.setCellType(Cell.CELL_TYPE_STRING);
//				cellValue = cell.getCellFormula();
				// 判断是否包含小数点，如果不含小数点，则以字符串读取，如果含小数点，则转换为Double类型的字符串
//				if (cellValue.indexOf(".") > -1) {
//					cellValue = String.valueOf(Double.parseDouble(cellValue)).trim();
//				} else {
//					cellValue = temp.trim();
//				}
				/* POI5.x处理方法 */
				//POI5.x取消了cell.setCellType方法，且POI5.x对Number类型的单元格取值方法（cell.getNumericCellValue()）只会返回Double类型；另外，当单元格内容比较长的时候还会返回科学计数法；
				cellValue = NumberToTextConverter.toText(cell.getNumericCellValue());
			}
		} else if(CellType.STRING.equals(cell.getCellType())){
			cellValue = cell.getStringCellValue().trim();
		} else if(CellType.FORMULA.equals(cell.getCellType())){
			// 当前单元格公式：cell.getCellFormula();
			// 当前单元格返回类型：cell.getCachedFormulaResultType()
			if (CellType.NUMERIC.equals(cell.getCachedFormulaResultType())) {
				cellValue = cell.getDateCellValue();
			} else {
				cellValue = cell.getCellFormula();
			}
		} else if(CellType.BOOLEAN.equals(cell.getCellType())){
			cellValue = Boolean.toString(cell.getBooleanCellValue());
		} else {
			//if CellType.BLANK,set default value.
			cellValue = "";
		}
		return cellValue;
	}
	
	/**
	 * 全量读取File文件内容。<br>
	 * 
	 * @param input
	 * @return
	 */
	public static Workbook getWorkbook(File file) {
		if (!file.isFile()) {
			log.error(">>>not found the file,please check it!");
			return null;
		} else {
			log.info(">>>正在处理文件" + file.getAbsolutePath());
			// file.encoding的属性值就是main方法所在类文件的编码。
			// log.info(">>>当前文件编码格式：" + System.getProperty("file.encoding"));
			try {
				return WorkbookFactory.create(file);
			} catch (EncryptedDocumentException | IOException e) {
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

	/**
	 * 全量读取Stream数据流内容。<br>
	 * 
	 * @param input
	 * @return
	 */
	public static Workbook getWorkbook(InputStream input) {
		Workbook wb = null;
		try {
			// 能兼容excel03和excel07版本.
			wb = WorkbookFactory.create(input);
			log.info("ready go......");
			log.info("共发现" + wb.getNumberOfSheets() + "张sheet表单......：");
		} catch (IOException e) {
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
