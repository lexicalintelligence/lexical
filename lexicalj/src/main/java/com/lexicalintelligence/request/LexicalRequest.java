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

package com.lexicalintelligence.request;

import com.lexicalintelligence.LexicalClient;

public abstract class LexicalRequest {
	public static enum RequestType {
		Coordinations,
		Negations,
		Idioms,
		Stopwords,
		Spellings,
		Entity;
		
		public String toString() {
			return super.toString().toLowerCase();
		}
	}
	
	protected LexicalClient client;
	protected RequestType type;
	
	public LexicalRequest(LexicalClient client) {
		this.client = client;
	}
	
	public LexicalRequest setType(RequestType type) {
		this.type = type;
		return this;
	}
	
	public final RequestType getType() {
		return type;
	}
}
