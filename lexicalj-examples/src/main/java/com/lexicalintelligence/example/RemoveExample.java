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

import java.util.ArrayList;
import java.util.Collection;

import com.lexicalintelligence.LexicalClient;
import com.lexicalintelligence.extract.ExtractRequest;
import com.lexicalintelligence.remove.RemoveRequest;
import com.lexicalintelligence.remove.RemoveResponse;

public class RemoveExample {
	public static void main(String[] args) {
		LexicalClient lexical = new LexicalClient("http://localhost:8080/lexicon/mesh");
		Collection<String> items = new ArrayList<>();
		items.add("night owl");
		
		RemoveResponse removeResponse = lexical.submit(new RemoveRequest(RemoveRequest.Type.Coordinations).setItems(items));
		
		System.out.println(lexical.submit(new ExtractRequest("cacner").setCheckSpelling(true)).getEntries());
		
		items.clear();
		items.add("cacner");
		items.add("cancer");
		
		removeResponse = lexical.submit(new RemoveRequest(RemoveRequest.Type.Spellings).setItems(items));
		
		System.out.println(removeResponse.isRemoved());
		
		System.out.println(lexical.submit(new ExtractRequest("cacner").setCheckSpelling(true)).getEntries());
		
	}
}
