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
import com.lexicalintelligence.action.extract.ExtractResponse;

public class BatchExample {
	public static void main(String[] args) {
		LexicalClient lexical = new LexicalClient("http://localhost:9898/rcdc");
		
		ExtractResponse extractResponse = lexical.prepareBatch().setId(1).setText("Brain").execute();
		
		extractResponse.getEntries().stream().forEach(System.out::println);
	}
}
