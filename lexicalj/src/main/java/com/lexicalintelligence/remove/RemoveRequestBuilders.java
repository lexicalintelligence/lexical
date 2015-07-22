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

import java.util.Collection;

public interface RemoveRequestBuilders {
	public static RemoveRequest removeCoordinationsRequest(Collection<String> items) {
		return new RemoveRequest(RemoveRequest.Type.Coordinations).setItems(items);
	}
	
	public static RemoveRequest removeIdiomsRequest(Collection<String> items) {
		return new RemoveRequest(RemoveRequest.Type.Idioms).setItems(items);
	}
	
	public static RemoveRequest removeStopwordsRequest(Collection<String> items) {
		return new RemoveRequest(RemoveRequest.Type.Stopwords).setItems(items);
	}
	
	public static RemoveRequest removeNegationsRequest(Collection<String> items) {
		return new RemoveRequest(RemoveRequest.Type.Negations).setItems(items);
	}
	
	public static RemoveRequest removeSpellingsRequest(Collection<String> items) {
		return new RemoveRequest(RemoveRequest.Type.Spellings).setItems(items);
	}
	
	public static RemoveRequest removeEntityRequest(Collection<String> items) {
		return new RemoveEntityRequest().setItems(items);
	}
}
