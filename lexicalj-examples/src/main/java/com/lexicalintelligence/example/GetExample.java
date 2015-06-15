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
import com.lexicalintelligence.get.GetCoordinationsResponse;
import com.lexicalintelligence.get.GetIdiomsResponse;
import com.lexicalintelligence.get.GetNegationsResponse;
import com.lexicalintelligence.get.GetRequestType;
import com.lexicalintelligence.get.GetSpellingsResponse;
import com.lexicalintelligence.get.GetStopwordsResponse;

public class GetExample {
	public static void main(String[] args) {
		LexicalClient lexical = new LexicalClient("http://localhost:8080/lexicon/mesh");

		GetSpellingsResponse gsr = lexical.submit(GetRequestType.Spelling);
		System.out.println(gsr.getSpellings());

		GetCoordinationsResponse gcr = lexical.submit(GetRequestType.Coordinations);
		System.out.println(gcr.getCoordinations());

		GetIdiomsResponse gir = lexical.submit(GetRequestType.Idioms);
		System.out.println(gir.getIdioms());

		GetNegationsResponse gnr = lexical.submit(GetRequestType.Negations);
		System.out.println(gnr.getNegations());

		GetStopwordsResponse gtr = lexical.submit(GetRequestType.Stopwords);
		System.out.println(gtr.getStopwords());
	}
}
