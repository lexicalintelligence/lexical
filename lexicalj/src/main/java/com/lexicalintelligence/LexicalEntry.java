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

package com.lexicalintelligence;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LexicalEntry {
	private String name = "";
	private int id;
	private int start;
	private int end;
	private List<String> semanticTypes = Collections.emptyList();
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	
	public int getStart() {
		return start;
	}
	
	public int getEnd() {
		return end;
	}
	
	public List<String> getSemanticTypes() {
		return semanticTypes;
	}
	
	public String toString() {
		return "<name:" + name + ">";
	}
	
	private LexicalEntry() {
		
	}
	
	@SuppressWarnings("unchecked")
	public static LexicalEntry create(Map<String, Object> map) {
		if (map == null) {
			return null;
		}
		LexicalEntry lexicalEntry = new LexicalEntry();
		if (map.get("name") != null) {
			lexicalEntry.name = (String) map.get("name");
		}
		lexicalEntry.id = (int) Double.parseDouble(String.valueOf(map.get("id"))); // this is ugly
		lexicalEntry.start = (int) Double.parseDouble(String.valueOf(map.get("start")));
		lexicalEntry.end = (int) Double.parseDouble(String.valueOf(map.get("end")));
		if (map.get("semanticTypes") != null) {
			lexicalEntry.semanticTypes = (List<String>) map.get("semanticTypes");
		}
		return lexicalEntry;
	}
}
