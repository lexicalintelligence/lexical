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

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.codehaus.jackson.type.TypeReference;

import com.lexicalintelligence.LexicalResponse;
import com.lexicalintelligence.admin.LexicalAdminClient;

public abstract class ListGetRequest extends GetRequest {
	public ListGetRequest(LexicalAdminClient client) {
		super(client);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends LexicalResponse> T execute() {
		ListGetResponse searchResponse;
		Reader reader = null;
		try {
			HttpResponse response = client.getHttpClient().execute(new HttpGet(client.getUrl() + PATH + getPath()));
			reader = new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8);
			List<String> items = client.getObjectMapper().readValue(reader, new TypeReference<List<String>>() {
			});
			searchResponse = new ListGetResponse(true);
			searchResponse.setItems(items);
		}
		catch (Exception e) {
			searchResponse = new ListGetResponse(false);
			log.error(e);
		}
		finally {
			try {
				reader.close();
			}
			catch (Exception e) {
				log.error(e);
			}
		}
		return (T) searchResponse;
	}
	
}
