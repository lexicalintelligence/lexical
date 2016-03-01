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
import java.util.List;
import java.util.Map;
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
	private List<LexicalType> lexicalTypes = Collections.emptyList();
	
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
			
			lexicalResponse.setEntries(result.get("entries").stream().filter(x -> {
				for (String type : x.getType()) {
					for (LexicalType lexicalType : lexicalTypes) {
						if (type.equals(lexicalType.toString())) {
							return true;
						}
					}
				}
				return false;
			}).collect(Collectors.toList()));
			
			//lexicalResponse.setEntries(result.get("entries"));
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
	
	public ExtractRequest projection(List<LexicalType> lexicalTypes) {
		if (lexicalTypes != null && !lexicalTypes.isEmpty()) {
			this.lexicalTypes = lexicalTypes;
		}
		return this;
	}
	
}
