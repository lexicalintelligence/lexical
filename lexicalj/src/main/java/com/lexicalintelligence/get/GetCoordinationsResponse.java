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

package com.lexicalintelligence.get;

import java.util.Collections;
import java.util.List;

public class GetCoordinationsResponse extends GetResponse {
	private List<String> coordinations = Collections.emptyList();
	
	public GetCoordinationsResponse(List<String> coordinations) {
		if (coordinations != null) {
			this.coordinations = coordinations;
		}
	}
	
	public List<String> getCoordinations() {
		return coordinations;
	}
}
