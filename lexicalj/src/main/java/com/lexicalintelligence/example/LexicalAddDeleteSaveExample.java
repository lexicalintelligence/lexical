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

import com.lexicalintelligence.LexicalClient;
import com.lexicalintelligence.query.AddQuery;
import com.lexicalintelligence.query.EntityQuery;
import com.lexicalintelligence.response.AddResponse;

public class LexicalAddDeleteSaveExample {
	public static void main(String[] args) {
		// Connection to lexical server
		LexicalClient lexical = new LexicalClient("http://localhost:8080/lexicon/rehab");
		
		// Add new entry to lexicon
		AddQuery addQuery = new AddQuery("Kirk");
		addQuery.synonym("kirk");
		addQuery.caseSensitive(true);
		addQuery.orderSensitive(true);
		addQuery.matchStopwords(true);
		addQuery.stemmed(false);
		
		AddResponse addResponse = lexical.add(addQuery);
		
		System.out.println(addResponse.isAdded());
		//System.out.println(lexical.save());

		System.out.println(lexical.process(new EntityQuery("Lexical")).getEntries());

		lexical.remove("Kirk", "kirk");

		System.out.println(lexical.process(new EntityQuery("kirk")).getEntries());
		
		//System.out.println(lexical.save());
	}
}
