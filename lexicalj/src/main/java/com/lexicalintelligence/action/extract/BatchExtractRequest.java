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

package com.lexicalintelligence.action.extract;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.codehaus.jackson.type.TypeReference;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lexicalintelligence.LexicalClient;
import com.lexicalintelligence.LexicalDocument;
import com.lexicalintelligence.LexicalEntry;

public class BatchExtractRequest {
	private Log log = LogFactory.getLog(BatchExtractRequest.class);
	private static Gson gson = new GsonBuilder().create();
	private static String PATH = "/extract/batch";

	private LexicalClient client;
	private List<LexicalDocument> params;

	public BatchExtractRequest(LexicalClient client) {
		if (client == null) {
			throw new RuntimeException();
		}
		this.client = client;
		params = new ArrayList<>();
	}

	@SuppressWarnings("unchecked")
	public List<ExtractResponse> execute() {
		HttpPost post = new HttpPost(client.getUrl() + PATH);
		Reader reader = null;
		try {
			StringEntity postingString = new StringEntity(gson.toJson(params));
			post.setEntity(postingString);
			post.setHeader("Content-type", "application/json");
			//post.setHeader("Accept", "application/json");
			//post.setHeader("X-Stream", "true");
			//System.out.println(gson.toJson(params));
			//post.setEntity(new UrlEncodedFormEntity(params.values()));
			HttpResponse response = client.getHttpClient().execute(post);
			//System.out.println(">> " + IOUtils.toString(response.getEntity().getContent()));

			reader = new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8);

			List<Map<String, Object>> result = client.getObjectMapper().readValue(reader, new TypeReference<List<Map<String, Object>>>() {
			});
			
			return result.stream().map(r -> {
				ExtractResponse extractResponse = new ExtractResponse();
				extractResponse.setId((String) r.get("id"));
				extractResponse.setEntries((Map<String, List<LexicalEntry>>) r.get("entries"));
				return extractResponse;
			}).collect(Collectors.toList());
		}
		catch (Exception e) {
			log.error(e);
		}
		finally {
			if (reader != null) {
				try {
					reader.close();
				}
				catch (IOException e) {
					log.error(e);
				}
			}
		}
		return Collections.emptyList();
	}

	public BatchExtractRequest add(LexicalDocument doc) {
		params.add(doc);
		return this;
	}
}
