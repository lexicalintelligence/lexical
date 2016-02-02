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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.codehaus.jackson.type.TypeReference;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lexicalintelligence.LexicalClient;
import com.lexicalintelligence.LexicalEntry;

public class BatchRequest {
	private Log log = LogFactory.getLog(BatchRequest.class);
	private static Gson gson = new GsonBuilder().create();
	private static String PATH = "/process";
	
	private LexicalClient client;
	private Map<String, Object> flags;
	private List<Map<String, Object>> docs;
	
	public BatchRequest(LexicalClient client) {
		if (client == null) {
			throw new RuntimeException();
		}
		this.client = client;
		flags = new HashMap<>();
		docs = new ArrayList<>();
	}
	
	public BatchRequest add(Map<String, Object> doc) {
		docs.add(doc);
		return this;
	}

	public ExtractResponse execute() {
		ExtractResponse lexicalResponse = new ExtractResponse();
		HttpPost post = new HttpPost(client.getUrl() + PATH);
		Reader reader = null;
		try {
			Map<String, Object> m = new HashMap<>();
			m.put("flags", flags);
			m.put("docs", docs);
			
			StringEntity postingString = new StringEntity(gson.toJson(m));
			System.out.println(getClass() + "\t" + Collections.singletonList(flags.toString()).toString());
			post.setEntity(postingString);
			post.setHeader("Content-type", "application/json");
			post.setHeader("Accept", "application/json");
			post.setHeader("X-Stream", "true");
			//post.setEntity(new UrlEncodedFormEntity(params.values()));
			HttpResponse response = client.getHttpClient().execute(post);
			
			//System.out.println(getClass() + "\t" + IOUtils.toString(response.getEntity().getContent()));
			
			reader = new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8);
			Map<String, List<LexicalEntry>> result = client.getObjectMapper().readValue(reader, new TypeReference<Map<String, List<LexicalEntry>>>() {
			});
			lexicalResponse.setEntries(result.get("entries"));
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
		return lexicalResponse;
	}
	
	public BatchRequest setExpandAbbreviations(boolean expandAbbreviations) {
		flags.put("expandAbbreviations", expandAbbreviations);
		return this;
	}
	
	public BatchRequest setExpandCoordinations(boolean expandCoordinations) {
		flags.put("expandCoordinations", expandCoordinations);
		return this;
	}
	
}
