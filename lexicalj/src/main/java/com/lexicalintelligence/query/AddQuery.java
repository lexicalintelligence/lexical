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

package com.lexicalintelligence.query;

public class AddQuery {
	private String name = "";
	private String synonym = "";
	private int id = -1;
	private boolean orderSensitive;
	private boolean stemmed;
	private boolean caseSensitive;
	private boolean matchStopwords;

	public AddQuery(String name) {
		if (name != null) {
			this.name = name;
		}
	}
	
	public AddQuery synonym(String synonym) {
		if (synonym != null) {
			this.synonym = synonym;
		}
		return this;
	}

	public AddQuery id(int id) {
		if (id > 0) {
			this.id = id;
		}
		return this;
	}

	public AddQuery orderSensitive(boolean orderSensitive) {
		this.orderSensitive = orderSensitive;
		return this;
	}

	public AddQuery stemmed(boolean stemmed) {
		this.stemmed = stemmed;
		return this;
	}

	public AddQuery caseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
		return this;
	}

	public AddQuery matchStopwords(boolean matchStopwords) {
		this.matchStopwords = matchStopwords;
		return this;
	}

	public final String getName() {
		return name;
	}

	public final String getSynonym() {
		return synonym;
	}

	public final int getId() {
		return id;
	}

	public final boolean isOrderSensitive() {
		return orderSensitive;
	}

	public final boolean isStemmed() {
		return stemmed;
	}

	public final boolean isCaseSensitive() {
		return caseSensitive;
	}

	public final boolean isMatchStopwords() {
		return matchStopwords;
	}
}
