package com.javapai.framework.fileparse.excel.strategy;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
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
import com.javapai.framework.fileparse.excel.AbstractExcelHandler;
import com.javapai.framework.fileparse.excel.ExcelReader;
import com.javapai.framework.fileparse.excel.config.SheetConfig;

public final class PoiStrategy extends AbstractExcelHandler implements ExcelReader {
	/**/
	protected static Logger log = LoggerFactory.getLogger(PoiStrategy.class);

	protected SheetConfig config;

	public PoiStrategy() {
		this.config = new SheetConfig();
	}

	public PoiStrategy(SheetConfig config) {
		this.config = config;
	}

	@Override
	public List<TableFormat> readFile(File file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TableFormat> readFile(InputStream stream) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TableFormat> readSheet(Workbook workBook) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableFormat readSheet(Sheet sheet) {
		// TODO Auto-generated method stub
		return readSheet(sheet, this.config);
	}

	@Override
	public TableFormat readSheet(Sheet sheet, SheetConfig config) {
		// TODO Auto-generated method stub

		int rows = sheet.getPhysicalNumberOfRows();
		log.info("正在处理sheet表单[" + sheet.getSheetName() + "],表单数据总行数[" + rows + "].");

		int firstRow = sheet.getFirstRowNum();
		int lastRow = sheet.getLastRowNum();// 当本行数据为空，格式还存在时，也算最后行。
		log.info("批注：sheet表单数据开始于[" + firstRow + "]行结束于[" + lastRow + "]行.");

		/**/
		TableFormat table = new TableFormat();
		List<String> noteList = new ArrayList<>();
		// List<Map<Integer, Object>> dataList = new ArrayList<>();

		if (rows <= 20000) {
			for (Row row : sheet) {
				Map<Integer, Object> data = readRow(row);
				if (null != config.getNoteIndex() && config.getNoteIndex().equals(Integer.valueOf(row.getRowNum()))) {
					data.forEach((k, v) -> {
						noteList.add(v.toString());
					});
				} else if (row.getRowNum() == config.getTitleIndex()) {
					// report.setTitleMap(map);
					table.setTableTitle(data.entrySet().stream()
							.collect(Collectors.toMap(Map.Entry::getKey, entry -> String.valueOf(entry.getValue()))));
				} else if (row.getRowNum() >= config.getTitleIndex()) {
					table.addContent(data);
				} else {
					// TODO when:row.getRowNum() >= config.getDataIndex()
					// dataList.add(data);
//					table.addContent(data);
				}
			}

//			 table.setNoteList(noteList);
			// table.setTableConent(dataList);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableFormat readSheet(Workbook workBook, int sheetIndex, SheetConfig config) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableFormat readSheet(Workbook workBook, String sheetName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableFormat readSheet(Workbook workBook, String sheetName, SheetConfig config) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableFormat readSheet(InputStream strean, int sheetIndex) {
		// TODO Auto-generated method stub
		return readSheet(getWorkbook(strean).getSheetAt(sheetIndex));
	}

	@Override
	public TableFormat readSheet(InputStream strean, int sheetIndex, SheetConfig config) {
		// TODO Auto-generated method stub
		return readSheet(getWorkbook(strean).getSheetAt(sheetIndex), config);
	}

	@Override
	public TableFormat readSheet(InputStream strean, String sheetName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableFormat readSheet(InputStream strean, String sheetName, SheetConfig config) {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	protected Map<Integer, Object> readRow(Row row) {
		if (null == row) {
			log.info(">>>发现一个空行数据对象!");
			return null;
		}

		short firstCell = row.getFirstCellNum();
		int totalCells = row.getPhysicalNumberOfCells(); // totalCells =
															// row.getLastCellNum()-
															// firstCell
		log.info(">>>批注：正在处理Excel表单第" + row.getRowNum() + "行数据!.");
		// log.info(">>>本行数据开始于[" + firstCell + "]列结束于[" + lastCell + "]列,共[" +
		// totalCells + "]列.");

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
			for (int index = firstCell; index < totalCells; index++) {
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
	private String readCell(Cell cell) {
		String cellValue = "";
		if (cell == null || "null".equals(cell)) {
			return cellValue;
		}

		switch (cell.getCellType()) {
		// if numeric(cell.getCellType() == 0)
		case Cell.CELL_TYPE_NUMERIC:
			// cellValue = cell.getNumericCellValue();
			if (DateUtil.isCellDateFormatted(cell)) {
				cellValue = String.valueOf(cell.getDateCellValue());
			} else {
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
				cellValue = cellValue.replaceAll("#N/A", "").trim();
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
			// cell空数据检查。
			break;
		case Cell.CELL_TYPE_ERROR:
			cellValue = "";
			break;
		default:
			break;
		}
		return cellValue;
	}

}
