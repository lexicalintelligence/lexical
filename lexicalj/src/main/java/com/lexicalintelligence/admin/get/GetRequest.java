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

package com.lexicalintelligence.admin.get;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lexicalintelligence.LexicalResponse;
import com.lexicalintelligence.admin.LexicalAdminClient;

public abstract class GetRequest {
	protected final Log log = LogFactory.getLog(GetRequest.class);
	protected static final String PATH = "/get";
	
	protected LexicalAdminClient client;
	
	public GetRequest(LexicalAdminClient client) {
		this.client = client;
	}
	
	public abstract <T extends LexicalResponse> T execute();
	
	public abstract String getPath();
	
}
