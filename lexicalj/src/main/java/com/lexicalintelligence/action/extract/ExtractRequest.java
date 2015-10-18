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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.lexicalintelligence.filter.PostFilter;
import com.lexicalintelligence.filter.PreFilter;

public class ExtractRequest {
	private Log log = LogFactory.getLog(ExtractRequest.class);
	private static String PATH = "/extract";
	
	private LexicalClient client;
	private Map<String, NameValuePair> params;
	
	public ExtractRequest(LexicalClient client) {
		if (client == null) {
			throw new RuntimeException();
		}
		this.client = client;
		params = new HashMap<>();
	}
	
	public ExtractResponse execute() {
		ExtractResponse lexicalResponse = new ExtractResponse();
		HttpPost post = new HttpPost(client.getUrl() + PATH);
		Reader reader = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(params.values()));
			HttpResponse response = client.getHttpClient().execute(post);
			reader = new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8);
			Map<String, List<LexicalEntry>> result = client.getObjectMapper().readValue(reader, new TypeReference<Map<String, List<LexicalEntry>>>() {
			});
			lexicalResponse.setEntries(result.get(PostFilter.EXTRACT_ENTITIES.toString()));
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
			params.put("text", new BasicNameValuePair("text", text));
		}
		return this;
	}
	
	public ExtractRequest setDetectNegations(boolean detectNegations) {
		params.put(PreFilter.DETECT_NEGATION.toString(), new BasicNameValuePair(PreFilter.DETECT_NEGATION.toString(), Boolean.FALSE.toString()));
		return this;
	}
	
	public ExtractRequest setExpandAbbreviations(boolean expandAbbreviations) {
		params.put(PreFilter.EXPAND_ABBREVIATIONS.toString(), new BasicNameValuePair(PreFilter.EXPAND_ABBREVIATIONS.toString(), Boolean.FALSE.toString()));
		return this;
	}
	
	public ExtractRequest setExpandCoordinations(boolean expandCoordinations) {
		params.put(PreFilter.EXPAND_COORDINATIONS.toString(), new BasicNameValuePair(PreFilter.EXPAND_COORDINATIONS.toString(), Boolean.FALSE.toString()));
		return this;
	}
	
	public ExtractRequest setCheckSpelling(boolean checkSpelling) {
		params.put(PreFilter.CORRECT_SPELLING.toString(), new BasicNameValuePair(PreFilter.CORRECT_SPELLING.toString(), Boolean.FALSE.toString()));
		return this;
	}
}
