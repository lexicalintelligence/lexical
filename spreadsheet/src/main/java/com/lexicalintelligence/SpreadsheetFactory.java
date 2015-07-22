package com.lexicalintelligence;

/**
 * @author Kirk Baker
 *
 */
public class SpreadsheetFactory {
	public static SpreadsheetWriter writer(String file) throws Exception {
		if (file.toLowerCase().endsWith(".csv")) {
			return new UnicodeCsvWriter(file);
		}
		else if (file.toLowerCase().endsWith(".xls")) {
			return new XlsWriter(file);
		}
		else if (file.toLowerCase().endsWith(".xlsx")) {
			return new XlsxWriter(file);
		}
		throw new RuntimeException("Not supported " + file);
	}
	
	public static SpreadsheetReader reader(String file) throws Exception {
		if (file.toLowerCase().endsWith(".csv")) {
			return new UnicodeCsvReader(file);
		}
		else if (file.toLowerCase().endsWith(".xls")) {
			return new XlsReader(file);
		}
		else if (file.toLowerCase().endsWith(".xlsx")) {
			return new XlsxReader(file);
		}
		throw new RuntimeException("Not supported " + file);
	}
	
	public static void main(String[] args) {
		try (SpreadsheetWriter writer = SpreadsheetFactory.writer("c.csv"); SpreadsheetReader reader = SpreadsheetFactory.reader("x.xlsx")) {
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
