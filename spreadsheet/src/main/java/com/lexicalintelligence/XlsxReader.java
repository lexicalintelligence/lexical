package com.lexicalintelligence;

import java.io.IOException;
import java.util.List;

/**
 * @author Kirk Baker
 *
 */
public class XlsxReader implements SpreadsheetReader {
	XlsxReader(String file) {

	}

	/* (non-Javadoc)
	 * @see java.lang.AutoCloseable#close()
	 */
	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.lexicalintelligence.SpreadsheetReader#readHeaders()
	 */
	@Override
	public List<String> readHeaders() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.lexicalintelligence.SpreadsheetReader#readRow()
	 */
	@Override
	public boolean readRow() throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.lexicalintelligence.SpreadsheetReader#get(java.lang.String)
	 */
	@Override
	public String get(String col) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.lexicalintelligence.SpreadsheetReader#get(int)
	 */
	@Override
	public String get(int index) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.lexicalintelligence.SpreadsheetReader#getRow()
	 */
	@Override
	public List<String> getRow() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
