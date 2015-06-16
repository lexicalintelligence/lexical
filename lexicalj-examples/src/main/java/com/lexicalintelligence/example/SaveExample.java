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

import com.lexicalintelligence.LexicalClient;
import com.lexicalintelligence.add.AddCoordinationsRequest;
import com.lexicalintelligence.add.AddResponse;
import com.lexicalintelligence.get.GetCoordinationsResponse;
import com.lexicalintelligence.get.GetRequestType;
import com.lexicalintelligence.save.SaveRequestType;
import com.lexicalintelligence.save.SaveResponse;

public class SaveExample {
	public static void main(String[] args) {
		LexicalClient lexical = new LexicalClient("http://localhost:8080/lexicon/mesh");
		
		AddResponse addResponse = lexical.submit(new AddCoordinationsRequest(Arrays.asList("night owl,barn owl")));
		System.out.println(addResponse.isAdded());
		
		SaveResponse saveResponse = lexical.submit(SaveRequestType.Coordinations);
		System.out.println(saveResponse.isSaved());
		
		GetCoordinationsResponse gcr = lexical.submit(GetRequestType.Coordinations);
		System.out.println(gcr.getCoordinations());
	}
}
