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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.type.TypeReference;

import com.lexicalintelligence.LexicalClient;
import com.lexicalintelligence.LexicalEntry;
import com.lexicalintelligence.LexicalType;

public class ExtractRequest {
	private Log log = LogFactory.getLog(ExtractRequest.class);
	private static String PATH = "/extract";
	
	private LexicalClient client;
	private Map<String, List<NameValuePair>> fields;
	//private NameValuePair params;
	private boolean include = false;
	private Set<String> projection;
	
	public ExtractRequest(LexicalClient client) {
		if (client == null) {
			throw new RuntimeException();
		}
		this.client = client;
		fields = new HashMap<>();
	}
	
	@SuppressWarnings("unchecked")
	public ExtractResponse execute() {
		ExtractResponse extractResponse = new ExtractResponse();
		HttpPost post = new HttpPost(client.getUrl() + PATH);
		Reader reader = null;
		try {
			List<NameValuePair> params = new ArrayList<>();
			for (List<NameValuePair> val : fields.values()) {
				params.addAll(val);
			}
			post.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));
			HttpResponse response = client.getHttpClient().execute(post);
			reader = new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8);
			
			Map<String, Object> result = client.getObjectMapper().readValue(reader, new TypeReference<Map<String, Object>>() {
			});
			
			extractResponse.setId((String) result.get("id"));
			extractResponse.setEntries((Map<String, List<LexicalEntry>>) result.get("entries"));
			
			//	if (projection == null) {
			//		
			//		extractResponse.setEntries(result.get("entries").stream().collect(Collectors.toList()));
			//	}
			/*else {
				System.out.println(result);
				extractResponse.setEntries(result.get("text").stream().filter(x -> {
								for (String type : x.getType()) {
									boolean contains = projection.contains(type);
									
									if (contains) {
										return include;
									}
								}
								return !include;
							}).collect(Collectors.toList()));
			}*/
		}
		catch (Exception e) {
			e.printStackTrace();
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
		return extractResponse;
	}
	
	public ExtractRequest setTextField(String key, String value) {
		if (key != null && value != null) {
			List<NameValuePair> existing = new ArrayList<>();
			existing.add(new BasicNameValuePair(key, value));
			fields.put(key, existing);
		}
		else if (value == null) {
			fields.remove(key);
		}
		return this;
	}
	
	public ExtractRequest addTextField(String key, String value) {
		if (key != null && value != null) {
			List<NameValuePair> existing = fields.get(key);
			if (existing == null) {
				existing = new ArrayList<>();
				fields.put(key, existing);
			}
			existing.add(new BasicNameValuePair(key, value));
		}
		return this;
	}
	
	public ExtractRequest include(LexicalType... lexicalTypes) {
		projection = new HashSet<String>(lexicalTypes.length);
		include = true;
		for (LexicalType type : lexicalTypes) {
			projection.add(type.toString());
		}
		return this;
	}
	
	public ExtractRequest exclude(LexicalType... lexicalTypes) {
		projection = new HashSet<String>(lexicalTypes.length);
		include = false;
		for (LexicalType type : lexicalTypes) {
			projection.add(type.toString());
		}
		return this;
	}
}
