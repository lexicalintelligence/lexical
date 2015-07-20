package com.lexicalintelligence;

import java.io.IOException;
import java.util.List;

/**
 * @author Kirk Baker
 *
 */
public interface SpreadsheetReader extends AutoCloseable {
	List<String> readHeaders() throws IOException;
	
	boolean readRow() throws IOException;

	String get(String col) throws IOException;

	String get(int index) throws IOException;

	List<String> getRow() throws IOException;
}
