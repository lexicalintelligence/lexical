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

package com.lexicalintelligence.remove;

import java.util.ArrayList;
import java.util.List;

public class RemoveEntityRequest extends RemoveRequest {
	public RemoveEntityRequest() {
		super(RemoveRequest.Type.Entity);
		items = new ArrayList<>(2);
	}
	
	public void setName(String name) {
		((List<String>) items).set(0, name);
	}
	
	public void setSynonym(String synonym) {
		((List<String>) items).set(1, synonym);
	}
	
	public String getName() {
		return ((List<String>) items).get(0);
	}
	
	public String getSynonym() {
		return ((List<String>) items).get(1);
	}
	
}