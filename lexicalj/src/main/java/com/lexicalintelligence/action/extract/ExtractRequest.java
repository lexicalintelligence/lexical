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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
	private NameValuePair params;
	private boolean include = false;
	private Set<String> projection;
	
	public ExtractRequest(LexicalClient client) {
		if (client == null) {
			throw new RuntimeException();
		}
		this.client = client;
	}
	
	public ExtractResponse execute() {
		ExtractResponse lexicalResponse = new ExtractResponse();
		HttpPost post = new HttpPost(client.getUrl() + PATH);
		Reader reader = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(Collections.singletonList(params), StandardCharsets.UTF_8));
			HttpResponse response = client.getHttpClient().execute(post);
			reader = new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8);
			Map<String, List<LexicalEntry>> result = client.getObjectMapper().readValue(reader, new TypeReference<Map<String, List<LexicalEntry>>>() {
			});
			
			if (projection == null) {
				lexicalResponse.setEntries(result.get("entries").stream().collect(Collectors.toList()));
			}
			else {
				lexicalResponse.setEntries(result.get("entries").stream().filter(x -> {
					for (String type : x.getType()) {
						boolean contains = projection.contains(type);
						if (include) {
							if (!contains) {
								return false;
							}
							return true;
						}
						else {
							if (contains) {
								return false;
							}
							return true;
						}

					}
					return false;
				}).collect(Collectors.toList()));
			}
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
	
	public ExtractRequest setText(String text) {
		if (text != null) {
			params = new BasicNameValuePair("text", text);
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
