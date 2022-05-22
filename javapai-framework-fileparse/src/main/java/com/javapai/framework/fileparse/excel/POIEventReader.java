package com.javapai.framework.fileparse.excel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.javapai.framework.config.TableFormat;
import com.javapai.framework.fileparse.excel.config.SheetConfig;

/**
 * 事件模式解析Excel内容。<br>
 * 
 * @author pooja
 *
 */
public final class POIEventReader implements IExcelReader {
	protected static Logger log = LoggerFactory.getLogger(POIEventReader.class);

	protected SheetConfig config;

	public POIEventReader() {
		this.config = new SheetConfig();
	}

	public POIEventReader(SheetConfig config) {
		this.config = config;
	}

	@Override
	public TableFormat readSheet(File file, int sheetIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableFormat readSheet(File file, int sheetIndex, SheetConfig config) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableFormat readSheet(InputStream strean, int sheetIndex) {
		// TODO Auto-generated method stub
		/*
		 * SXSSFWorkbook只是对XSSFWorkbook作了一层分页处理，实际的数据记录存在于XSSFWorkbook对象中；<br>
		 * 所以在进表单数据操作时，我们还是需要用sxssfWorkbook.getXSSFWorkbook()方法去获取初始模板的行数据。
		 */
		// getWorkBook(strean).getXSSFWorkbook().getSheetAt(sheetIndex);
//		return readSheet(getWorkBook(strean).getXSSFWorkbook().getSheetAt(sheetIndex));
		return readSheet(strean, getDefaultSheetIndex(sheetIndex), this.config);
	}

	@Override
	public TableFormat readSheet(InputStream strean, int sheetIndex, SheetConfig config) {
		// TODO Auto-generated method stub
		InputStream sheetStream = null;
		try {
			/* 初始化 */
			OPCPackage pkg = OPCPackage.open(strean);
			XSSFReader r = new XSSFReader(pkg);

			/* 指定xml文件解析器 */
			// XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
			XMLReader parser = XMLReaderFactory.createXMLReader("com.sun.org.apache.xerces.internal.parsers.SAXParser");
			
			/* 设置xml内容处理器 */
			SharedStringsTable sst = r.getSharedStringsTable();
			Excel07EventHandler handler = new Excel07EventHandler(sst, config);
			parser.setContentHandler(handler);
			
			/* 解析sheet表单内容 */
			sheetStream = r.getSheet("rId" + getDefaultSheetIndex(sheetIndex));
			InputSource sheetSource = new InputSource(sheetStream);
			parser.parse(sheetSource);
			
			/* 返回表单内容 */
			return handler.getTableFormat();
		} catch (IOException | OpenXML4JException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(null != sheetStream) {
				try {
					sheetStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}

	@Override
	public TableFormat readSheet(InputStream strean, String sheetName) {
		// TODO Auto-generated method stub
		return readSheet(strean, sheetName, this.config);
	}

	@Override
	public TableFormat readSheet(InputStream strean, String sheetName, SheetConfig config) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TableFormat> readFile(File file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TableFormat> readFile(InputStream stream) {
		// TODO Auto-generated method stub
//		Iterator<InputStream> sheets = r.getSheetsData(); 
		return null;
	}

//	@Override
//	public List<TableFormat> readSheet(Workbook workBook) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	protected TableFormat readSheet(Sheet sheet) {
//		// TODO Auto-generated method stub
//		return readSheet(sheet, this.config);
//	}

//	@Override
//	protected TableFormat readSheet(Sheet sheet, SheetConfig config) {
//		// TODO Auto-generated method stub
//		
////		SXSSFWorkbook wb = (SXSSFWorkbook) sheet.getWorkbook();
////		
////		sheet = wb.getSheetAt(1);
//		
//		int rows = sheet.getPhysicalNumberOfRows();
//		log.info("正在处理sheet表单[" + sheet.getSheetName() + "],表单数据总行数[" + rows + "].");
//
//		int firstRow = sheet.getFirstRowNum();
//		int lastRow = sheet.getLastRowNum();// 当本行数据为空，格式还存在时，也算最后行。
//		log.info("批注：sheet表单数据开始于[" + firstRow + "]行结束于[" + lastRow + "]行.");
//
//		/**/
//		TableFormat table = new TableFormat();
//
////		if (rows <= 20000) {
//			for (Row row : sheet) {
//				if (isEmptyRow(row)) {
//					continue;
//				}
//				Map<Integer, Object> data = readRow(row);
//				if (null != config.getNoteIndex() && config.getNoteIndex().contains(row.getRowNum())) {
//					/*
//					data.forEach((k, v) -> {
//						System.out.println(v.toString());
//					});
//					*/
//					table.addRemark(data);					
//				} else if (row.getRowNum() == config.getTitleIndex()) {
//					table.setTableTitle(data.entrySet().stream()
//							.collect(Collectors.toMap(Map.Entry::getKey, entry -> String.valueOf(entry.getValue()))));
//				} else if (row.getRowNum() >= config.getTitleIndex()) {
//					table.addContent(data);
//				} else {
//					// TODO when:row.getRowNum() >= config.getDataIndex()
//					// dataList.add(data);
////					table.addContent(data);
//				}
//			}
//
////			 table.setNoteList(noteList);
//			// table.setTableConent(dataList);
////		} else {
////			/* sheet数据集过大有可能报内存问题， 可以借用PoiEventStrategy提供的内部类来优化性解决。 */
////			log.info(">>>批注:sheet数据集过大可能有内存OOM问题.可以借用PoiEventStrategy提供的内部类来优化性解决。");
////			sheet.getWorkbook();
////		}
//
//		log.info(">>>the sheet(" + sheet.getSheetName() + ")处理完毕!---");
//		log.info("-------------------------------------------------------");
//
//		return table;
//	}

//	@Override
//	public TableFormat readSheet(Workbook workBook, int sheetIndex) {
//		// TODO Auto-generated method stub
//		return readSheet(workBook, sheetIndex, this.config);
//	}

//	@Override
//	public TableFormat readSheet(Workbook workBook, int sheetIndex, SheetConfig config) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public TableFormat readSheet(Workbook workBook, String sheetName) {
//		// TODO Auto-generated method stub
//		return readSheet(workBook, sheetName, this.config);
//	}

//	@Override
//	public TableFormat readSheet(Workbook workBook, String sheetName, SheetConfig config) {
//		// TODO Auto-generated method stub
//		return readSheet(workBook, workBook.getSheetIndex(sheetName), config);
//	}
	
//	private static SXSSFWorkbook getWorkBook(InputStream input) {
//		try {
//			return new SXSSFWorkbook(new XSSFWorkbook(input), 1000);
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}

	/**
	 * Excel2007+以上的版本是使用xml格式来存储的，把一个excel文件后缀改为.zip，直接打开zip包就可以看到一个excel文件对应的xml格式的文件清单了。<br>
	 * Excel07的XML格式说明。<br>
	 * 一个Excel07文件相当于一个zip包。<br>
	 * 其中：<br>
	 * /xl/workbook.xml文件描述了excel文件的sheet信息。<br>
	 * /xl/sharedStrings.xml文件描述了sheet文件内容。<br>
	 * /xl/styles.xml文件描述了sheet内容的样式。<br>
	 * 
	 * @author pooja
	 *
	 */
	private class Excel07EventHandler extends DefaultHandler {
		private TableFormat table = new TableFormat();

		private SheetConfig config;
		/* 共享字符串表 */
		private SharedStringsTable sst;
		/* 上一次内容 */
		private String lastContents;
		private boolean nextIsString;

		/* 当前数据行标 */
		private Integer rowIndex;
		/* 当前数据列标 */
		private int colIndex = 1;
		/* 当前行数据集 */
		private Map<Integer, Object> rowData;

		private boolean t_element;

		private Excel07EventHandler(SharedStringsTable sst) {
			this.sst = sst;
			this.config = new SheetConfig();
		}

		private Excel07EventHandler(SharedStringsTable sst, SheetConfig config) {
			this.sst = sst;
			this.config = config;
		}

		// 元素开始时的handler
		public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
			// 当element元素为<cols>标签时 。
			// if (name.equals("cols")) {
			// log.info("--->正在处理表头行数据!", attributes.getValue("r"));
			// }
			
			// 1:当element元素为<row>标签时 (说明已在处理=>行数据)。
			if (name.equals("row")) {
				rowIndex = Integer.valueOf(attributes.getValue("r"));
				log.info("--->正在处理第{}行数据!", rowIndex);
				if (rowIndex == 12) {
					System.out.println("x");
				}
				if (rowIndex.equals(config.getTitleIndex()) || rowIndex.equals(config.getDataIndex())) {
					rowData = new LinkedHashMap<Integer, Object>();
				}
			}

			// 2:当element元素为<c>标签时(说明已在处理 =>单元格).
			if (name.equals("c") && null != rowData) {
				//log.info("------>正在处理第{}列数据!", attributes.getValue("r"));
				// 获取单元格类型
				String cellType = attributes.getValue("t");
				if (cellType != null && cellType.equals("s")) {
					nextIsString = true;
				} else {
					nextIsString = false;
				}
			}
			
			// 当元素为t时
			if ("t".equals(name)) {
				t_element = true;
			} else {
				t_element = false;
			}

			lastContents = "";
		}

		// 元素结束时的handler
		public void endElement(String uri, String localName, String name) throws SAXException {
			if (nextIsString) {
				int idx = Integer.parseInt(lastContents);
				lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
				nextIsString = false;
			}

			// t元素也包含字符串
			if (t_element) {
				String value = lastContents.trim();
				// rowlist.add(curCol, value);
				t_element = false;
				// v => 单元格的值，
				// 将单元格内容加入rowlist中，在这之前先去掉字符串前后的空白符
			} else if (name.equals("v")) {
				// v => 单元格内容(如果单元格是字符串则v标签的值为该字符串在SST中的索引).
				log.info("------>当前列值：{}", lastContents);
				if (rowIndex >= config.getDataIndex()) {
					rowData.put(colIndex, lastContents);
					colIndex = colIndex + 1;
				} else if (rowIndex.equals(config.getTitleIndex())) {
					table.addTitle(colIndex, lastContents);
					colIndex = colIndex + 1;
				} else {
					log.warn("未分类数居，暂时无法将数据写入到table!");
				}
			} else {
				// end_element元素为</row>标签时 ，说明已到行尾。
				if (name.equals("row")) {
					colIndex = 1;
					log.info("--->完成处理第{}行数据!", rowIndex);
					if (rowIndex >= config.getDataIndex()) {
						table.addContent(rowData);
					}
				}
			}
		}

		// 读取元素间内容时的handler
		public void characters(char[] ch, int start, int length) throws SAXException {
			lastContents += new String(ch, start, length);
		}

		public TableFormat getTableFormat() {
			return this.table;
		}
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

}
