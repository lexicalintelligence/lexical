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
	private boolean stemmed = true;
	
	/**
	 * @return the type
	 */
	public List<String> getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(List<String> type) {
		this.type = type;
	}
	
	/**
	 * @return the orderSensitive
	 */
	public boolean isOrderSensitive() {
		return orderSensitive;
	}
	
	/**
	 * @param orderSensitive the orderSensitive to set
	 */
	public void setOrderSensitive(boolean orderSensitive) {
		this.orderSensitive = orderSensitive;
	}
	
	/**
	 * @return the caseSensitive
	 */
	public boolean isCaseSensitive() {
		return caseSensitive;
	}
	
	/**
	 * @param caseSensitive the caseSensitive to set
	 */
	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}
	
	/**
	 * @return the matchStopwords
	 */
	public boolean isMatchStopwords() {
		return matchStopwords;
	}
	
	/**
	 * @param matchStopwords the matchStopwords to set
	 */
	public void setMatchStopwords(boolean matchStopwords) {
		this.matchStopwords = matchStopwords;
	}
	
	/**
	 * @return the matchPunctuation
	 */
	public boolean isMatchPunctuation() {
		return matchPunctuation;
	}
	
	/**
	 * @param matchPunctuation the matchPunctuation to set
	 */
	public void setMatchPunctuation(boolean matchPunctuation) {
		this.matchPunctuation = matchPunctuation;
	}
	
	/**
	 * @return the stemmed
	 */
	public boolean isStemmed() {
		return stemmed;
	}
	
	/**
	 * @param stemmed the stemmed to set
	 */
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
	
	/**
	 * @return the synonym
	 */
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
		if (map.get("type") != null) {
			lexicalEntry.type = (List<String>) map.get("type");
		}
		return lexicalEntry;
	}
	
	public LexicalEntry setName(String name) {
		if (name != null) {
			this.name = name;
		}
		return this;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}
	
	/**
	 * @param end the end to set
	 */
	public void setEnd(int end) {
		this.end = end;
	}
	
}
