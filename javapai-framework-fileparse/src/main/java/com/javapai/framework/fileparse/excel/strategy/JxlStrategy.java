package com.javapai.framework.fileparse.excel.strategy;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.javapai.framework.config.TableFormat;
import com.javapai.framework.fileparse.excel.JXLExcelReader;
import com.javapai.framework.fileparse.excel.config.SheetConfig;

/**
 * JXL策略实现。<br>
 * 
 * @author pooja
 * 
 * @deprecated XL官方在2009年10月已经停止更新。<br>
 *             <p>
 *             批注： Jxl只支持Excel2003以上版本，对Excel 2007及以上的版本格式无法进行解析，所以此解析策略暂停实现。
 *             </p>
 * 
 */
@Deprecated
public final class JxlStrategy implements JXLExcelReader {
	// @Override
	// protected Map<Integer, Object> readRow(Row row) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	@Override
	public TableFormat readSheet(InputStream strean, int sheetIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableFormat readSheet(InputStream strean, int sheetIndex, SheetConfig config) {
		// TODO Auto-generated method stub
		return null;
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
	public TableFormat readSheet(File File, int sheetIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableFormat readSheet(File File, int sheetIndex, SheetConfig config) {
		// TODO Auto-generated method stub
		return null;
	}

}
