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

import java.util.List;

public class LexicalEntryBuilder {
	private LexicalEntry _entry;
	
	private LexicalEntryBuilder() {
		_entry = new LexicalEntry();
	}

	public static LexicalEntryBuilder start() {
		return new LexicalEntryBuilder();
	}

	public static LexicalEntryBuilder start(String name) {
		return start().name(name);
	}
	
	public static LexicalEntryBuilder start(String name, String synonym) {
		return start(name).synonym(synonym);
	}
	
	public LexicalEntryBuilder name(String name) {
		_entry.setName(name);
		return this;
	}
	
	public LexicalEntryBuilder synonym(String synonym) {
		_entry.setSynonym(synonym);
		return this;
	}

	public LexicalEntryBuilder id(int id) {
		_entry.setId(id);
		return this;
	}

	public LexicalEntryBuilder type(List<String> type) {
		_entry.setType(type);
		return this;
	}

	public LexicalEntryBuilder start(int start) {
		_entry.setStart(start);
		return this;
	}
	
	public LexicalEntryBuilder end(int end) {
		_entry.setEnd(end);
		return this;
	}
	
	public LexicalEntryBuilder order() {
		return order(true);
	}
	
	public LexicalEntryBuilder order(boolean order) {
		_entry.setOrderSensitive(order);
		return this;
	}
	
	public LexicalEntryBuilder fold() {
		return fold(true);
	}
	
	public LexicalEntryBuilder fold(boolean fold) {
		_entry.setCaseSensitive(!fold);
		return this;
	}
	
	public LexicalEntryBuilder stopwords() {
		return stopwords(true);
	}
	
	public LexicalEntryBuilder stopwords(boolean stopwords) {
		_entry.setMatchStopwords(stopwords);
		return this;
	}

	public LexicalEntryBuilder punctuation() {
		return punctuation(true);
	}
	
	public LexicalEntryBuilder punctuation(boolean punctuation) {
		_entry.setMatchPunctuation(punctuation);
		return this;
	}

	public LexicalEntryBuilder stem() {
		return stem(true);
	}
	
	public LexicalEntryBuilder stem(boolean stem) {
		_entry.setStemmed(stem);
		return this;
	}
}
