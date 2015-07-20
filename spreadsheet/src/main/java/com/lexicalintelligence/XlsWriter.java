package com.lexicalintelligence;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * @author Kirk Baker
 *
 */
public class XlsWriter implements SpreadsheetWriter {
	WritableWorkbook workbook;
	WritableSheet sheet;
	int i = 0, j = 0;

	public XlsWriter(String path) throws IOException {
		workbook = Workbook.createWorkbook(new File(path));
		sheet = workbook.createSheet("Sheet1", 0);
	}

	@Override
	public void close() throws Exception {
		workbook.write();
		workbook.close();
	}
	
	@Override
	public void write(String cell) throws Exception {
		sheet.addCell(new Label(j++, i, cell));
	}
	
	@Override
	public void writeRecord(List<String> row) throws Exception {
		for (String cell : row) {
			sheet.addCell(new Label(j++, 1, cell));
		}
		endRecord();
	}
	
	@Override
	public void endRecord() {
		i++;
		j = 0;
	}
}
