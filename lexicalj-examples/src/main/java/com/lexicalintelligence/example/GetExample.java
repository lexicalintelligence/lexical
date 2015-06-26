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

import static com.lexicalintelligence.get.GetRequestBuilders.getCoordinationsRequest;
import static com.lexicalintelligence.get.GetRequestBuilders.getIdiomsRequest;
import static com.lexicalintelligence.get.GetRequestBuilders.getNegationsRequest;
import static com.lexicalintelligence.get.GetRequestBuilders.getSpellingsRequest;
import static com.lexicalintelligence.get.GetRequestBuilders.getStopwordsRequest;

import com.lexicalintelligence.LexicalClient;
import com.lexicalintelligence.get.GetResponse;
import com.lexicalintelligence.get.GetSpellingsResponse;

public class GetExample {
	public static void main(String[] args) {
		LexicalClient lexical = new LexicalClient("http://localhost:8080/lexicon/mesh");
		
		GetSpellingsResponse gsr = lexical.submit(getSpellingsRequest());
		System.out.println(gsr.getSpellings());
		
		GetResponse gr = lexical.submit(getCoordinationsRequest());
		System.out.println(gr.getItems());
		
		gr = lexical.submit(getIdiomsRequest());
		System.out.println(gr.getItems());
		gr = lexical.submit(getNegationsRequest());
		System.out.println(gr.getItems());
		
		gr = lexical.submit(getStopwordsRequest());
		System.out.println(gr.getItems());
	}
}
