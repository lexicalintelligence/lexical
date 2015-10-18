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

package com.lexicalintelligence.admin.search;

import java.util.Collections;
import java.util.Map;

public class MapSearchResponse extends SearchResponse {
	private Map<String, String> items = Collections.emptyMap();
	
	public MapSearchResponse(boolean success) {
		super(success);
	}
	
	public final void setItems(Map<String, String> items) {
		if (items != null) {
			this.items = items;
		}
	}
	
	public final Map<String, String> getItems() {
		return items;
	}
}
