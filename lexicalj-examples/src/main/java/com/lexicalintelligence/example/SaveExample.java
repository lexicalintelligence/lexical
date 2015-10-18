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

import com.lexicalintelligence.admin.LexicalAdminClient;
import com.lexicalintelligence.admin.save.SaveResponse;

public class SaveExample {
	public static void main(String[] args) {
		LexicalAdminClient lexical = new LexicalAdminClient("http://localhost:8080/lexicon/mesh");
		
		SaveResponse response = lexical.saveLexicon().execute();
		System.out.println(response.success());
		
		response = lexical.saveCoordinations().execute();
		System.out.println(response.success());
		
		response = lexical.saveIdioms().execute();
		System.out.println(response.success());
		
		response = lexical.saveNegations().execute();
		System.out.println(response.success());
		
		response = lexical.saveSpellings().execute();
		System.out.println(response.success());
		
		response = lexical.saveStopwords().execute();
		System.out.println(response.success());
		
	}
}
