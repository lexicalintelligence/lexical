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

import static com.lexicalintelligence.add.AddRequestBuilders.addCoordinationsRequest;
import static com.lexicalintelligence.add.AddRequestBuilders.addIdiomsRequest;
import static com.lexicalintelligence.add.AddRequestBuilders.addNegationsRequest;
import static com.lexicalintelligence.add.AddRequestBuilders.addSpellingsRequest;
import static com.lexicalintelligence.add.AddRequestBuilders.addStopwordsRequest;

import java.util.Arrays;

import com.lexicalintelligence.LexicalClient;
import com.lexicalintelligence.add.AddResponse;
import com.lexicalintelligence.extract.ExtractRequest;

public class AddExample {
	public static void main(String[] args) {
		LexicalClient lexical = new LexicalClient("http://localhost:8080/lexicon/mesh");
		
		AddResponse response = lexical.submit(addCoordinationsRequest(Arrays.asList("night owl,barn owl")));
		System.out.println(response.isAdded());
		
		response = lexical.submit(addNegationsRequest(Arrays.asList("nyet")));
		System.out.println(response.isAdded());
		
		response = lexical.submit(addIdiomsRequest(Arrays.asList("head over heels")));
		System.out.println(response.isAdded());
		
		response = lexical.submit(addStopwordsRequest(Arrays.asList("moreoverthanwhich")));
		System.out.println(response.isAdded());
		
		response = lexical.submit(addSpellingsRequest(Arrays.asList("cacner", "cancer")));
		System.out.println(response.isAdded());
		
		System.out.println(lexical.submit(new ExtractRequest("cacner").setCheckSpelling(true)).getEntries());
		
	}
}
