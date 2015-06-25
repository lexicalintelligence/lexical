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

import com.lexicalintelligence.request.RequestType;

public class AddRequest {
	
	private RequestType type;
	protected Collection<String> items = Collections.emptyList();
	
	public AddRequest(RequestType type) {
		this.type = type;
	}
	
	public AddRequest setItems(Collection<String> items) {
		this.items = items;
		return this;
	}
	
	public Collection<String> getItems() {
		return items;
	}
	
	public final RequestType getType() {
		return type;
	}
}
