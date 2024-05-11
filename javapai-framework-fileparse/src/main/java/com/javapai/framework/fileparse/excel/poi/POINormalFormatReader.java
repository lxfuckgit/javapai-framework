package com.javapai.framework.fileparse.excel.poi;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javapai.framework.fileparse.excel.config.ReadSheetConfig;

/**
 * 普通模式解析Excel内容。<br>
 * 
 * @author pooja
 *
 */
public class POINormalFormatReader extends POIExcelReader<List<Map<Integer, Object>>> {
	protected static Logger log = LoggerFactory.getLogger(POINormalFormatReader.class);
	
	protected ReadSheetConfig config;
	
	public POINormalFormatReader() {
		this.config = new ReadSheetConfig();
	}

	public POINormalFormatReader(ReadSheetConfig config) {
		this.config = config;
	}
	
	@Override
	public List<Map<Integer, Object>> readSheet(File file, int sheetIndex) {
		return readSheet(getWorkbook(file), sheetIndex);
	}

	@Override
	public List<Map<Integer, Object>> readSheet(File file, String sheetName) {
		return readSheet(getWorkbook(file), sheetName);
	}

	@Override
	public List<Map<Integer, Object>> readSheet(File file, int sheetIndex, ReadSheetConfig config) {
		log.warn("--->提示：当前类不支持配置化读取！");
		return List.of();
	}

	@Override
	public List<Map<Integer, Object>> readSheet(File file, String sheetName, ReadSheetConfig config) {
		log.warn("--->提示：当前类不支持配置化读取！");
		return List.of();
	}

	@Override
	public List<List<Map<Integer, Object>>> readFile(File file, ReadSheetConfig config) {
		log.warn("--->提示：当前类不支持配置化读取！");
		return List.of();
	}

	@Override
	public List<Map<Integer, Object>> readSheet(InputStream inputStream, int sheetIndex) {
		int newSheetIndex = (sheetIndex <= 0) ? sheetIndex : sheetIndex - 1;
		return readSheet(getWorkbook(inputStream).getSheetAt(newSheetIndex));
	}

	@Override
	public List<Map<Integer, Object>> readSheet(InputStream inputStream, String sheetName) {
		log.warn("--->提示：当前类不支持配置化读取！");
		return List.of();
	}

	@Override
	public List<Map<Integer, Object>> readSheet(InputStream inputStream, int sheetIndex, ReadSheetConfig config) {
		int newSheetIndex = (sheetIndex <= 0) ? sheetIndex : sheetIndex - 1;
		return readSheet(getWorkbook(inputStream).getSheetAt(newSheetIndex), config);
	}

	@Override
	public List<Map<Integer, Object>> readSheet(InputStream inputStream, String sheetName, ReadSheetConfig config) {
		log.warn("--->提示：当前类不支持配置化读取！");
		return List.of();
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Map<Integer, Object>>> readFile(File file) {
		return readSheet(getWorkbook(file));
	}

	@Override
	public List<List<Map<Integer, Object>>> readFile(String filePath) {
		return readSheet(getWorkbook(new File(filePath)));
	}

	@Override
	public List<List<Map<Integer, Object>>> readFile(InputStream stream) {
		return readSheet(getWorkbook(stream));
	}
	
	@Override
	public List<List<Map<Integer, Object>>> readFile(InputStream inputStream, ReadSheetConfig config) {
		log.warn("--->提示：当前类不支持配置化读取！");
		return List.of();
	}

	@Override
	public List<List<Map<Integer, Object>>> readSheet(Workbook workBook) {
		List<List<Map<Integer, Object>>> list = new ArrayList<List<Map<Integer, Object>>>();
		for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
			list.add(readSheet(workBook.getSheetAt(i)));
		}
		return list;
	}

	@Override
	protected List<Map<Integer, Object>> readSheet(Sheet sheet) {
		return readSheet(sheet, this.config);
	}

	@Override
	protected List<Map<Integer, Object>> readSheet(Sheet sheet, ReadSheetConfig config) {
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
			log.warn(">>>批注:sheet数据集过大可能有内存OOM问题，可以借用PoiEventStrategy提供的内部类来优化性解决。");
		}

		List<Map<Integer, Object>> data = new LinkedList<>();
		// 记录：这里没有采用【for (Row row : sheet)】的形式的原因是于foreach方式无法迭代出空行的数据，且在提示语上也无法定位行标位置。
		for (int index = 0; index <= rows; index++) {
			Row row = sheet.getRow(index);
			if (isEmptyRow(row)) {
				log.info(">>>提示：检测到空行，行标位置{}！", (index + 1));
				continue;
			}
			data.add(readRow(row, 0, 0));
			// 当设置过max_read_size参数且符合参数条件时，终止循环。
			if (config.getMaxReadSize() > 0 && config.getMaxReadSize() == data.size()) {
				break;
			}
		}

		log.info(">>>the sheet(" + sheet.getSheetName() + ")处理完毕!---");
		log.info("-------------------------------------------------------");

		return data;
	}

	@Override
	public List<Map<Integer, Object>> readSheet(Workbook workBook, int sheetIndex) {
		int newSheetIndex = (sheetIndex <= 0) ? sheetIndex : sheetIndex - 1;
		return readSheet(workBook.getSheetAt(newSheetIndex));
	}

	@Override
	public List<Map<Integer, Object>> readSheet(Workbook workBook, int sheetIndex, ReadSheetConfig config) {
		log.warn("--->提示：当前类不支持配置化读取！");
		return List.of();
	}

	@Override
	public List<Map<Integer, Object>> readSheet(Workbook workBook, String sheetName) {
		return readSheet(workBook.getSheet(sheetName));
	}

	@Override
	public List<Map<Integer, Object>> readSheet(Workbook workBook, String sheetName, ReadSheetConfig config) {
		log.warn("--->提示：当前类不支持配置化读取！");
		return List.of();
	}
}