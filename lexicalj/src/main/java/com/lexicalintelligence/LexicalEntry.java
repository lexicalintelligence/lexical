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

import org.codehaus.jackson.annotate.JsonIgnore;

public class LexicalEntry {
	private String name = "";
	private String synonym = "";
	private int id;
	private int start;
	private int end;
	private List<String> type = Collections.emptyList();
	
	// Indexing properties
	@JsonIgnore
	private boolean orderSensitive = false;
	@JsonIgnore
	private boolean caseSensitive = false;
	@JsonIgnore
	private boolean matchStopwords = false;
	@JsonIgnore
	private boolean matchPunctuation = false;
	@JsonIgnore
	private boolean stemmed = false;
	
	public LexicalEntry() {
		
	}
	
	public List<String> getType() {
		return type;
	}
	
	public void setType(List<String> type) {
		this.type = type;
	}
	
	public boolean isOrderSensitive() {
		return orderSensitive;
	}
	
	public void setOrderSensitive(boolean orderSensitive) {
		this.orderSensitive = orderSensitive;
	}
	
	public boolean isCaseSensitive() {
		return caseSensitive;
	}
	
	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}
	
	public boolean isMatchStopwords() {
		return matchStopwords;
	}
	
	public void setMatchStopwords(boolean matchStopwords) {
		this.matchStopwords = matchStopwords;
	}
	
	public boolean isMatchPunctuation() {
		return matchPunctuation;
	}
	
	public void setMatchPunctuation(boolean matchPunctuation) {
		this.matchPunctuation = matchPunctuation;
	}
	
	public boolean isStemmed() {
		return stemmed;
	}
	
	public void setStemmed(boolean stemmed) {
		this.stemmed = stemmed;
	}
	
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
	
	public List<String> getTypes() {
		return type;
	}
	
	public String getSynonym() {
		return synonym;
	}
	
	public LexicalEntry setSynonym(String synonym) {
		if (synonym != null) {
			this.synonym = synonym;
		}
		return this;
	}
	
	@Override
	public String toString() {
		return "<name:" + name + ">";
	}
	
	@Override
	public boolean equals(Object o) {
		return ((LexicalEntry) o).id == id;
	}
	
	@Override
	public int hashCode() {
		return Integer.hashCode(id);
	}
	
	public LexicalEntry setName(String name) {
		if (name != null) {
			this.name = name;
		}
		return this;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
	public void setEnd(int end) {
		this.end = end;
	}
}
