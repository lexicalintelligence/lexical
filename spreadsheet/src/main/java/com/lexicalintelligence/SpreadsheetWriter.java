package com.lexicalintelligence;

import java.io.IOException;
import java.util.List;

/**
 * @author Kirk Baker
 *
 */
public interface SpreadsheetWriter extends AutoCloseable {
	void write(String cell) throws Exception;

	void writeRecord(List<String> row) throws Exception;

	void endRecord() throws IOException;
}
