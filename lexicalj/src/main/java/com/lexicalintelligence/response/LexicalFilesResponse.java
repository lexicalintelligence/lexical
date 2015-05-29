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

package com.lexicalintelligence.response;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class LexicalFilesResponse {
	private Collection<String> coordinations = Collections.emptyList();
	private Collection<String> negations = Collections.emptyList();
	private Collection<String> stopwords = Collections.emptyList();
	private Map<String, String> spellings = Collections.emptyMap();
	
	public Collection<String> getCoordinations() {
		return coordinations;
	}

	public void setCoordinations(Collection<String> coordinations) {
		if (coordinations != null) {
			this.coordinations = coordinations;
		}
	}
	
	public void setNegations(Collection<String> negations) {
		if (negations != null) {
			this.negations = negations;
		}
	}
	
	public void setStopwords(Collection<String> stopwords) {
		if (stopwords != null) {
			this.stopwords = stopwords;
		}
	}
	
	public Collection<String> getNegations() {
		return negations;
	}

	public Map<String, String> getSpellings() {
		return spellings;
	}

	public void setSpellings(Map<String, String> spellings) {
		if (spellings != null) {
			this.spellings = spellings;
		}
	}

	public Collection<String> getStopwords() {
		return stopwords;
	}
}
