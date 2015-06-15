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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.lexicalintelligence.LexicalClient;
import com.lexicalintelligence.add.AddCoordinationsRequest;
import com.lexicalintelligence.add.AddIdiomsRequest;
import com.lexicalintelligence.add.AddNegationsRequest;
import com.lexicalintelligence.add.AddResponse;
import com.lexicalintelligence.add.AddSpellingRequest;
import com.lexicalintelligence.add.AddStopwordsRequest;

public class AddExample {
	public static void main(String[] args) {
		LexicalClient lexical = new LexicalClient("http://localhost:8080/lexicon/mesh");
		
		AddResponse response = lexical.submit(new AddCoordinationsRequest(Arrays.asList("nigh owl")));
		System.out.println(response.isAdded());
		
		response = lexical.submit(new AddNegationsRequest(Arrays.asList("nyet")));
		System.out.println(response.isAdded());

		response = lexical.submit(new AddIdiomsRequest(Arrays.asList("head over heels")));
		System.out.println(response.isAdded());

		response = lexical.submit(new AddStopwordsRequest(Arrays.asList("boat")));
		System.out.println(response.isAdded());

		Map<String, String> dictionary = new HashMap<>();
		dictionary.put("tpyo", "typo");
		response = lexical.submit(new AddSpellingRequest(dictionary));
		System.out.println(response.isAdded());

	}
}
