package com.lexicalintelligence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * @author Kirk Baker
 *
 */
public class XlsReader implements SpreadsheetReader {
	private Workbook workbook = null;
	private Sheet sheet = null;
	private int i = 0, rows = 0;
	private Map<String, Integer> headers = new LinkedHashMap<>();
	private List<String> row = new ArrayList<>();
	
	XlsReader(String path) {
		try {
			workbook = Workbook.getWorkbook(new File(path));
			sheet = workbook.getSheet(0);
			rows = sheet.getRows();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void close() {
		workbook.close();
	}
	
	@Override
	public List<String> readHeaders() {
		Cell[] row = sheet.getRow(i);
		int j = 0;
		for (Cell cell : row) {
			String contents = cell.getContents();
			headers.put(contents, j++);
		}
		i++;
		return new ArrayList<>(headers.keySet());
	}
	
	@Override
	public boolean readRow() {
		if (i < rows) {
			row.clear();
			Arrays.stream(sheet.getRow(i)).forEach(c -> row.add(c.getContents()));
			return i++ < rows;
		}
		return false;
	}
	
	@Override
	public String get(String col) throws IOException {
		return row.get(headers.get(col));
	}
	
	@Override
	public String get(int index) throws IOException {
		return row.get(index);
	}
	
	@Override
	public List<String> getRow() {
		return row;
	}
}
