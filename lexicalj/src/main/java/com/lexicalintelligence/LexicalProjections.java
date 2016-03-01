/*
 * Copyright 2016 Lexical Intelligence, LLC.
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class LexicalProjections {
	private LexicalProjections() {
	}
	
	/**
	 * Creates a projection that includes all of the lexical types.
	 *
	 * @param lexicalTypes the lexical types
	 * @return the projection
	 */
	public static List<LexicalType> include(final LexicalType... lexicalTypes) {
		return lexicalTypes == null ? Collections.emptyList() : Arrays.asList(lexicalTypes);
	}
}
