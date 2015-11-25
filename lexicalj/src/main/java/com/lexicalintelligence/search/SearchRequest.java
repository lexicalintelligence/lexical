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

package com.lexicalintelligence.search;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.codehaus.jackson.type.TypeReference;

import com.lexicalintelligence.admin.LexicalAdminClient;

public class SearchRequest {
	protected final Log log = LogFactory.getLog(SearchRequest.class);
	protected static final String PATH = "/search";
	
	protected LexicalAdminClient client;
	protected String query;
	
	public SearchRequest(LexicalAdminClient client) {
		this.client = client;
	}
	
	public SearchRequest setQuery(String query) {
		this.query = query;
		return this;
	}
	
	public SearchResponse execute() {
		Reader reader = null;
		SearchResponse searchResponse = new SearchResponse(true);
		try {
			HttpResponse response = client.getHttpClient().execute(new HttpGet(client.getUrl() + PATH + "?prefix=" + URLEncoder.encode(query, "UTF-8")));
			reader = new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8);
			List<String> items = client.getObjectMapper().readValue(reader, new TypeReference<List<String>>() {
			});
			searchResponse.setItems(items);
		}
		catch (Exception e) {
			searchResponse = new SearchResponse(false);
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
		return searchResponse;
	}
}
