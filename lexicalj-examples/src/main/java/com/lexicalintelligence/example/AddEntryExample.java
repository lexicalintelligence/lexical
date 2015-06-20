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
import com.lexicalintelligence.LexicalEntry;
import com.lexicalintelligence.add.AddEntryRequest;
import com.lexicalintelligence.add.AddResponse;

public class AddEntryExample {
	public static void main(String[] args) {
		LexicalClient lexical = new LexicalClient("http://localhost:8080/lexicon/mesh");
		
		LexicalEntry entry = new LexicalEntry();
		entry.setName("Lexical");
		entry.setSynonym("Lexical Intelligence");
		entry.setCaseSensitive(true);
		entry.setOrderSensitive(true);
		entry.setMatchPunctuation(true);
		entry.setMatchStopwords(true);
		entry.setStemmed(false);
		
		AddResponse response = lexical.submit(new AddEntryRequest(entry));
		
		System.out.println(response.isAdded());
		
	}
}