package com.javapai.framework.fileparse.excel.poi;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javapai.framework.config.TableFormat;
import com.javapai.framework.fileparse.excel.config.ReadSheetConfig;

/**
 * 利用POI框架对Excel表格以TableFormat格式进行读取。<br>
 * 
 * @author pooja
 *
 */
public class POITableFormatReader extends POIExcelReader<TableFormat> {
	protected static Logger log = LoggerFactory.getLogger(POITableFormatReader.class);

	protected ReadSheetConfig config;

	public POITableFormatReader() {
		this.config = new ReadSheetConfig();
	}

	public POITableFormatReader(ReadSheetConfig config) {
		this.config = config;
	}

	@Override
	public List<TableFormat> readFile(File file) {
		return readSheet(getWorkbook(file));
	}
	
	@Override
	public List<TableFormat> readFile(String filePath) {
		return readFile(new File(filePath));
	}

	@Override
	public List<TableFormat> readFile(InputStream stream) {
		return readSheet(getWorkbook(stream));
	}

	@Override
	public List<TableFormat> readSheet(Workbook workBook) {
		List<TableFormat> list = new ArrayList<TableFormat>();
		for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
			list.add(readSheet(workBook.getSheetAt(i)));
			// list.add(readSheet(workBook.getSheet("sheetName"));
		}
		return list;
	}

	@Override
	public TableFormat readSheet(Sheet sheet) {
		return readSheet(sheet, this.config);
	}

	@Override
	public TableFormat readSheet(Sheet sheet, ReadSheetConfig config) {
		/*
		 * 获取最后行的行号（行号从0开始）。 
		 * 提示：sheet.getLastRowNum()检测方式是即使本行数据为空时，只要单元格的格式还存在时，也算有效最后行。
		 * 例如：原Excel有11行数据，个性后只保留2行，当getLastRowNum()时扔然是11行。解决办法是“跳过空行”。
		 */
		int lastRow = sheet.getLastRowNum();
		/*
		 * 获取物理行数（不包括空行和隔行的情况，行号从0开始）。
		 */
		int physicalRow = sheet.getPhysicalNumberOfRows();
		/*
		 * 取最大行数作为遍历行数（其实最大行就是lastRowNum）。
		 */
		int rows = lastRow > physicalRow ? lastRow : physicalRow;
		log.info("正在处理sheet表单[" + sheet.getSheetName() + "]，表单数据总行数[" + rows + "]。");
		// log.info("批注：sheet表单数据开始于[" + sheet.getFirstRowNum() + "]行结束于[" + lastRow + "]行。");
		if (rows > 30000) {
			/* sheet数据集过大有可能报内存问题， 可以借用PoiEventStrategy提供的内部类来优化性解决。 */
			log.warn(">>>批注:sheet数据集过大可能有内存OOM问题，可以借用PoiEventStrategy提供的内部类来优化性解决。");
		}

		TableFormat table = new TableFormat();
		// 记录：这里没有采用【for (Row row : sheet)】的形式的原因是于foreach方式无法迭代出空行的数据，且在提示语上也无法定位行标位置。
		for (int index = 0; index <= rows; index++) {
			Row row = sheet.getRow(index);
			if (isEmptyRow(row)) {
				log.info(">>>提示：检测到空行，行标位置{}！", (index + 1));
				continue;
			}
			Map<Integer, Object> data = readRow(row, config.getPosition(), config.getX2Position());
			if (row.getRowNum() >= config.getDataIndex() - 1) {
				// config.getDataIndex-1：将用户指定索引号转化为系统索引号。
				table.addContent(data);
			} else if (row.getRowNum() == config.getTitleIndex() - 1) {
				// config.getTitleIndex-1：将用户指定索引号转化为系统索引号。
				table.setTableTitle(data.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> String.valueOf(entry.getValue()))));
			} else if (null != config.getNoteIndex() && config.getNoteIndex().contains(row.getRowNum() + 1)) {
				table.addRemark(data);
			}
		}

		log.info(">>>the sheet(" + sheet.getSheetName() + ")处理完毕!---");
		log.info("-------------------------------------------------------");

		return table;
	}

	@Override
	public TableFormat readSheet(Workbook workBook, int sheetIndex) {
		return readSheet(workBook.getSheetAt(sheetIndex - 1));
	}

	@Override
	public TableFormat readSheet(Workbook workBook, int sheetIndex, ReadSheetConfig config) {
		return readSheet(workBook.getSheetAt(sheetIndex), config);
	}

	@Override
	public TableFormat readSheet(Workbook workBook, String sheetName) {
		return readSheet(workBook.getSheet(sheetName));
	}

	@Override
	public TableFormat readSheet(Workbook workBook, String sheetName, ReadSheetConfig config) {
		return readSheet(workBook.getSheet(sheetName), config);
	}

	@Override
	public TableFormat readSheet(InputStream strean, int sheetIndex) {
		// 将用户级的索引号(约定从1开始)转换成系统级索引号（约定从0开始）。
		int newSheetIndex = (sheetIndex <= 0) ? sheetIndex : sheetIndex - 1;
		return readSheet(getWorkbook(strean).getSheetAt(newSheetIndex));
	}
	
	@Override
	public TableFormat readSheet(InputStream strean, int sheetIndex, ReadSheetConfig config) {
		return readSheet(getWorkbook(strean).getSheetAt(sheetIndex), config);
	}

	@Override
	public TableFormat readSheet(InputStream strean, String sheetName) {
		return readSheet(getWorkbook(strean), sheetName);
	}

	@Override
	public TableFormat readSheet(InputStream strean, String sheetName, ReadSheetConfig config) {
		return readSheet(getWorkbook(strean), sheetName, config);
	}
	
	@Override
	public TableFormat readSheet(File file, int sheetIndex) {
		return readSheet(getWorkbook(file), sheetIndex);
	}

	@Override
	public TableFormat readSheet(File file, int sheetIndex, ReadSheetConfig config) {
		return readSheet(getWorkbook(file), sheetIndex, config);
	}
	
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableFormat readSheet(File file, String sheetName) {
		return readSheet(file, sheetName, this.config);
	}

	@Override
	public TableFormat readSheet(File file, String sheetName, ReadSheetConfig config) {
		return readSheet(getWorkbook(file), sheetName, config);
	}

	@Override
	public List<TableFormat> readFile(File file, ReadSheetConfig config) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TableFormat> readFile(InputStream inputStream, ReadSheetConfig config) {
		Workbook workBook = getWorkbook(inputStream);
		List<TableFormat> list = new ArrayList<TableFormat>();
		for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
			list.add(readSheet(workBook.getSheetAt(i), config));
		}
		return list;
	}
	
}
