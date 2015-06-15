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
import com.lexicalintelligence.get.GetRequest.GetType;
import com.lexicalintelligence.get.GetSpellingsResponse;
import com.lexicalintelligence.query.LexicalFilesQuery;
import com.lexicalintelligence.query.LexicalQueryBuilders;
import com.lexicalintelligence.response.LexicalFilesResponse;

public class LexicalClientFilesExample {
	public static void main(String[] args) {
		// Connection to lexical server
		LexicalClient lexicalClient = new LexicalClient("http://localhost:8080/lexicon/mesh");
		GetSpellingsResponse gsr = lexicalClient.prepareGet(GetType.Spellings).execute();
		System.out.println(gsr.getClass());
		System.exit(0);
		
		// Display currently used stopwords
		LexicalFilesQuery filesQuery = LexicalQueryBuilders.stopwordsFileQuery();
		LexicalFilesResponse filesResponse = lexicalClient.process(filesQuery);
		filesResponse.getStopwords().stream().forEach(System.out::println);
		
		// Display currently used negation triggers
		filesQuery = LexicalQueryBuilders.negationsFileQuery();
		filesResponse = lexicalClient.process(filesQuery);
		filesResponse.getNegations().stream().forEach(System.out::println);
		
		// Display currently used coordination triggers
		filesQuery = LexicalQueryBuilders.coordinationsFileQuery();
		filesResponse = lexicalClient.process(filesQuery);
		filesResponse.getCoordinations().stream().forEach(System.out::println);
		
		// Display currently used spelling dictionary
		filesQuery = LexicalQueryBuilders.spellingsFileQuery();
		filesResponse = lexicalClient.process(filesQuery);
		filesResponse.getSpellings().entrySet().stream().forEach(System.out::println);
	}
}
