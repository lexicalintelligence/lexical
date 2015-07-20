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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.csvreader.CsvWriter;

public class UnicodeCsvWriter implements SpreadsheetWriter {
	private CsvWriter csv;
	
	public UnicodeCsvWriter(String path) {
		try {
			csv = new CsvWriter(new FileOutputStream(new File(path)), ',', StandardCharsets.UTF_8);
		}
		catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void write(String cell) throws IOException {
		csv.write(cell);
	}
	
	@Override
	public void endRecord() throws IOException {
		csv.endRecord();
	}

	@Override
	public void writeRecord(List<String> row) throws IOException {
		csv.writeRecord(row.toArray(new String[row.size()]));
	}

	@Override
	public void close() throws Exception {
		csv.close();
	}
}