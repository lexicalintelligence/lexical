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

package com.lexicalintelligence.add;

import java.util.Collection;
import java.util.Collections;

import com.lexicalintelligence.request.LexicalRequest;

public class AddRequest extends LexicalRequest {
	public AddRequest(RequestType type) {
		super(type);
	}
	
	private Collection<String> items = Collections.emptyList();
	
	public AddRequest setItems(Collection<String> items) {
		if (items != null) {
			this.items = items;
		}
		return this;
	}
	
	public final Collection<String> getItems() {
		return items;
	}
}