package com.lexicalintelligence;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Kirk Baker
 *
 */
public class XlsxWriter implements SpreadsheetWriter {
	private FileOutputStream os = null;
	private XSSFSheet sheet = null;
	private XSSFWorkbook writer = null;
	private int i = 0, j = 0;
	private Row row = null;

	XlsxWriter(String path) {
		
		try {
			writer = new XSSFWorkbook();
			sheet = writer.createSheet();
			os = new FileOutputStream(new File(path));
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void close() throws Exception {
		writer.write(os);
		os.close();
	}
	
	@Override
	public void write(String cell) throws Exception {
		if (row == null) {
			row = sheet.createRow(i);
		}
		Cell c = row.createCell(j++, Cell.CELL_TYPE_STRING);
		c.setCellValue(cell);
	}
	
	@Override
	public void writeRecord(List<String> row) throws Exception {
		if (this.row == null) {
			this.row = sheet.createRow(i);
		}
		row.stream().forEach(s -> {
			Cell cell = this.row.createCell(j++, Cell.CELL_TYPE_STRING);
			cell.setCellValue(s);
		});
		endRecord();
	}
	
	@Override
	public void endRecord() {
		i++;
		j = 0;
		row = null;
	}
	
}
