package com.javapai.framework.fileparse.excel.strategy;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javapai.framework.config.TableFormat;
import com.javapai.framework.fileparse.excel.POIExcelReader;
import com.javapai.framework.fileparse.excel.config.ReadSheetConfig;

/**
 * 普通模式解析Excel内容。<br>
 * 
 * @author pooja
 *
 */
public class POINormalReaderStrategy extends POIExcelReader {
	protected static Logger log = LoggerFactory.getLogger(POINormalReaderStrategy.class);

	protected ReadSheetConfig config;

	public POINormalReaderStrategy() {
		this.config = new ReadSheetConfig();
	}

	public POINormalReaderStrategy(ReadSheetConfig config) {
		this.config = config;
	}

	@Override
	public List<TableFormat> readFile(File file) {
		return readSheet(getWorkbook(file));
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
		int rows = sheet.getPhysicalNumberOfRows();
		log.info("正在处理sheet表单[" + sheet.getSheetName() + "],表单数据总行数[" + rows + "].");

		int firstRow = sheet.getFirstRowNum();
		int lastRow = sheet.getLastRowNum();// 当本行数据为空，格式还存在时，也算最后行。
		log.info("批注：sheet表单数据开始于[" + firstRow + "]行结束于[" + lastRow + "]行.");

		/**/
		TableFormat table = new TableFormat();
		if (rows <= 20000) {
			for (Row row : sheet) {
				if (emptyRow(row)) {
					continue;
				}
				Map<Integer, Object> data = readRow(row);
				if (null != config.getNoteIndex() && config.getNoteIndex().contains(row.getRowNum())) {
					table.addRemark(data);					
				} else if (row.getRowNum() == config.getTitleIndex() - 1) {
					table.setTableTitle(data.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> String.valueOf(entry.getValue()))));
				} else if (row.getRowNum() >= config.getDataIndex() - 1) {
					table.addContent(data);
				}
			}
		} else {
			/* sheet数据集过大有可能报内存问题， 可以借用PoiEventStrategy提供的内部类来优化性解决。 */
			log.info(">>>批注:sheet数据集过大可能有内存OOM问题.可以借用PoiEventStrategy提供的内部类来优化性解决。");
			sheet.getWorkbook();
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
		return readSheet(getWorkbook(strean).getSheetAt(sheetIndex));
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
	
	private boolean emptyRow(Row row) {
		if (null == row) {
			log.info(">>>发现一个空行数据对象!");
			return true;
		}
		
		/* 
		int cells = 0;// 空单元格记数器。
		Iterator<Cell> iter = row.cellIterator();
		while (iter.hasNext() && iter.next().getCellType() == Cell.CELL_TYPE_BLANK) {
			cells++;
		}
		return cells == (row.getLastCellNum() - row.getFirstCellNum());
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

	// @Override
	protected Map<Integer, Object> readRow(Row row) {
//		short lastCell = row.getLastCellNum(); //获取最后一个不为空的列是第几个.
		short firstCell = row.getFirstCellNum();
		int totalCells = row.getPhysicalNumberOfCells(); // 获取不为空的列的个数。
		log.info(">>>批注：正在处理Excel表单第" + row.getRowNum() + "行数据!");
		log.info(">>>本行数据开始于[" + firstCell + "]列结束于[" + row.getLastCellNum() + "]列，其中有效数据列共计[" + totalCells + "]列!");

		// java.text.DecimalFormat df = new DecimalFormat("#");
		Map<Integer, Object> datamap = new LinkedHashMap<Integer, Object>();

		/* 如果是第一行，我们当表头行处理，其它行当数据行处理 */
		if (row.getRowNum() <= 0 || row.getRowNum() == 0) {
			for (int index = firstCell; index < totalCells - firstCell; index++) {
				// if (row.getCell(index).getStringCellValue().trim().length()
				// == 0) {
				if (null == row.getCell(index) || null == row.getCell(index).getStringCellValue()) {
					// throw new IOException("列名不允许为空");
					datamap.put(index, "列[" + index + "]");
				} else {
					datamap.put(index, row.getCell(index).getStringCellValue());
				}
			}
		} else if (row.getRowNum() >= 1 && firstCell >= 0) {// 加条件是因为有些情况下，firstCell为-1.
			for (int index = firstCell; index < row.getLastCellNum(); index++) {
				Object fieldValue = readCell(row.getCell(index));
				datamap.put(index, fieldValue);
			}
		}

		return datamap;
	}
	
	/**
	 * 返回单元格内容.<br>
	 * 
	 * @param cell
	 * @return
	 */
	private Object readCell(Cell cell) {
		if (null == cell) {
			// row.getLastCellNum()与row.getPhysicalNumberOfCells()不相等时，会出现此类情况.
			return null;
		}
		
		Object cellValue = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				cellValue = cell.getDateCellValue();
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

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TableFormat> readFile(String filePath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableFormat readSheet(File file, String sheetName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableFormat readSheet(File file, String sheetName, ReadSheetConfig config) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TableFormat> readSheet(File file, ReadSheetConfig config) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TableFormat> readSheet(InputStream inputStream, ReadSheetConfig config) {
		// TODO Auto-generated method stub
		return null;
	}

}
