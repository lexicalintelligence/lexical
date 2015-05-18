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

package com.lexicalintelligence;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class LexicalClient {
	protected final Log log = LogFactory.getLog(LexicalClient.class);
	private static HttpClient httpClient = HttpClientBuilder.create().build();
	private static Type type = new TypeToken<Map<String, List<Map<String, Object>>>>() {
	}.getType();

	private String url;
	private Gson gson;
	private JsonParser parser;

	public LexicalClient(String url) {
		System.setProperty("jsse.enableSNIExtension", "false");
		this.url = url;
		gson = new Gson();
		parser = new JsonParser();
	}

	public List<LexicalEntry> process(String text) {
		if (text == null) {
			return Collections.emptyList();
		}

		List<LexicalEntry> result = new ArrayList<>();
		HttpPost post = new HttpPost(url);

		List<NameValuePair> params = new ArrayList<NameValuePair>(1);
		if (text != null) {
			params.add(new BasicNameValuePair("text", text));
		}

		Reader reader = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = httpClient.execute(post);
			reader = new InputStreamReader(response.getEntity().getContent(), "UTF8");

			JsonObject obj = parser.parse(reader).getAsJsonObject();

			Map<String, List<Map<String, Object>>> jsmap = gson.fromJson(obj, type);

			if (jsmap != null) {
				List<Map<String, Object>> entries = jsmap.get("entries");
				if (entries != null) {
					return entries.stream().map(entry -> LexicalEntry.create(entry)).collect(Collectors.toList());
				}
			}
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
				}
			}
		}
		return result;
	}
}
