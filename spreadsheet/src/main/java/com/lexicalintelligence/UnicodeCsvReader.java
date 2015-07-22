/*
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA
 */
package com.lexicalintelligence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.csvreader.CsvReader;

public class UnicodeCsvReader implements SpreadsheetReader {
	private CsvReader csv;
	private Reader reader;
	private List<String> headers = Collections.emptyList();

	UnicodeCsvReader(String path) {
		try {
			reader = new InputStreamReader(new FileInputStream(new File(path)), StandardCharsets.UTF_8);
			csv = new CsvReader(reader);
		}
		catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<String> readHeaders() throws IOException {
		if (headers.isEmpty()) {
			csv.readHeaders();
			headers = new ArrayList<>(Arrays.asList(csv.getHeaders()));
		}
		return headers;
	}

	@Override
	public void close() throws Exception {
		reader.close();
		csv.close();
	}

	@Override
	public String get(String col) throws IOException {
		return csv.get(col);
	}
	
	@Override
	public boolean readRow() throws IOException {
		return csv.readRecord();
	}

	@Override
	public String get(int index) throws IOException {
		return csv.get(index);
	}
	
	@Override
	public List<String> getRow() throws IOException {
		return Arrays.asList(csv.getValues());
	}
}