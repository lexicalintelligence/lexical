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

import java.util.List;

import com.lexicalintelligence.LexicalClient;
import com.lexicalintelligence.LexicalDocument;
import com.lexicalintelligence.action.extract.BatchExtractRequest;
import com.lexicalintelligence.action.extract.ExtractResponse;

public class BatchExample {
	public static void main(String[] args) {
		LexicalClient lexical = new LexicalClient("http://localhost:9898/small");
		LexicalDocument doc = new LexicalDocument();
		doc.setId("1");
		doc.setTextField("title", "Effect of Butea");
		doc.setTextField("text", "Hosta");
		
		BatchExtractRequest request = lexical.prepareBatch();
		request.add(doc);
		
		doc = new LexicalDocument();
		doc.setId("2");
		doc.setTextField("title", "Effect Butea Graphs");
		doc.setTextField("text", "Grapes is known to have Butea effects.");
		request.add(doc);
		
		List<ExtractResponse> responses = request.execute();
		responses.stream().forEach(System.out::println);
	}
}