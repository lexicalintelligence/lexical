/*
 * Copyright 2015 Lexical Intelligence, LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lexicalintelligence.example;

import java.util.HashMap;
import java.util.Map;

import com.lexicalintelligence.LexicalClient;
import com.lexicalintelligence.action.extract.BatchRequest;
import com.lexicalintelligence.action.extract.ExtractResponse;

public class BatchExample {
	public static void main(String[] args) {
		LexicalClient lexical = new LexicalClient("http://localhost:9898/RCDCv2014.10");
		
		BatchRequest request = lexical.prepareBatch().setExpandAbbreviations(true);

		Map<String, Object> doc = new HashMap<>();
		doc.put("id", 1);
		doc.put("text", "brain, kidney and lung cancer");
		request.add(doc);

		doc = new HashMap<>();
		doc.put("id", 2);
		doc.put("text", "heart and lungs");
		request.add(doc);

		ExtractResponse response = request.execute();
		
		//extractResponse.getEntries().stream().forEach(System.out::println);
	}
}
