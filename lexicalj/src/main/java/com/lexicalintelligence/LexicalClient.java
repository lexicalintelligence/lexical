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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.lexicalintelligence.get.GetRequest.GetType;
import com.lexicalintelligence.get.GetRequestBuilder;
import com.lexicalintelligence.query.AddQuery;
import com.lexicalintelligence.query.AddStopwordsRequest;
import com.lexicalintelligence.query.EntityQuery;
import com.lexicalintelligence.query.LexicalFilesQuery;
import com.lexicalintelligence.response.AddResponse;
import com.lexicalintelligence.response.LexicalEntityResponse;
import com.lexicalintelligence.response.LexicalFilesResponse;
import com.lexicalintelligence.response.RemoveResponse;

public class LexicalClient {
	protected final Log log = LogFactory.getLog(LexicalClient.class);
	private static HttpClient httpClient = HttpClientBuilder.create().build();
	private static Type type = new TypeToken<Map<String, List<Map<String, Object>>>>() {
	}.getType();
	
	private String url;
	private Gson gson;
	private JsonParser parser;
	
	private String extract = "/extract";
	private String files = "/files/";
	private String add = "/add?";
	private String save = "/save";
	private String delete = "/remove?";
	
	public LexicalClient(String url) {
		System.setProperty("jsse.enableSNIExtension", "false");
		this.url = url;
		gson = new Gson();
		parser = new JsonParser();
	}
	
	public GetRequestBuilder prepareGet(GetType type) {
		return new GetRequestBuilder(type);
	}
	
	public AddResponse addStopwords(AddStopwordsRequest request) {
		AddResponse addResponse = new AddResponse();
		if (request == null) {
			return addResponse;
		}
		
		List<NameValuePair> params = new ArrayList<NameValuePair>(6);
		
		request.getStopwords().stream().forEach(stopword -> {
			params.add(new BasicNameValuePair("stopword", stopword));
		});
		
		HttpGet get = new HttpGet(url + add + URLEncodedUtils.format(params, StandardCharsets.UTF_8));
		Reader reader = null;
		try {
			HttpResponse response = httpClient.execute(get);
			reader = new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8);
			boolean added = Boolean.valueOf(IOUtils.toString(reader));
			addResponse.setAdded(added);
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		finally {
			try {
				reader.close();
			}
			catch (Exception e) {
				
			}
		}
		return addResponse;
	}
	
	public boolean save() {
		HttpGet get = new HttpGet(url + save);
		Reader reader = null;
		try {
			HttpResponse response = httpClient.execute(get);
			reader = new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8);
			return Boolean.valueOf(IOUtils.toString(reader));
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		finally {
			try {
				reader.close();
			}
			catch (Exception e) {
				
			}
		}
		return false;
	}
	
	public RemoveResponse remove(String name, String synonym) {
		RemoveResponse removeResponse = new RemoveResponse();
		
		if (name == null && synonym == null) {
			return removeResponse;
		}
		
		List<NameValuePair> params = new ArrayList<NameValuePair>(6);
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("synonym", synonym));
		
		HttpGet get = new HttpGet(url + delete + URLEncodedUtils.format(params, StandardCharsets.UTF_8));
		Reader reader = null;
		try {
			HttpResponse response = httpClient.execute(get);
			reader = new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8);
			removeResponse.setDeleted(Boolean.valueOf(IOUtils.toString(reader)));
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		finally {
			try {
				reader.close();
			}
			catch (Exception e) {
				
			}
		}
		return removeResponse;
	}
	
	public AddResponse add(AddQuery addQuery) {
		AddResponse addResponse = new AddResponse();
		if (addQuery == null) {
			return addResponse;
		}
		
		List<NameValuePair> params = new ArrayList<NameValuePair>(6);
		params.add(new BasicNameValuePair("name", addQuery.getName()));
		params.add(new BasicNameValuePair("synonym", addQuery.getSynonym()));
		if (addQuery.getId() > 0) {
			params.add(new BasicNameValuePair("id", addQuery.getId() + ""));
		}
		
		params.add(new BasicNameValuePair("matchWordOrder", String.valueOf(addQuery.isOrderSensitive())));
		params.add(new BasicNameValuePair("caseSensitive", String.valueOf(addQuery.isCaseSensitive())));
		params.add(new BasicNameValuePair("matchStopwords", String.valueOf(addQuery.isMatchStopwords())));
		// params.add(new BasicNameValuePair("matchPunctuation", String.valueOf(addQuery.isMatchPunctuation())));
		
		HttpGet get = new HttpGet(url + add + URLEncodedUtils.format(params, StandardCharsets.UTF_8));
		Reader reader = null;
		try {
			HttpResponse response = httpClient.execute(get);
			reader = new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8);
			boolean added = Boolean.valueOf(IOUtils.toString(reader));
			addResponse.setAdded(added);
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		finally {
			try {
				reader.close();
			}
			catch (Exception e) {
				
			}
		}
		
		return addResponse;
	}
	
	public LexicalFilesResponse process(LexicalFilesQuery filesQuery) {
		LexicalFilesResponse filesResponse = new LexicalFilesResponse();
		if (filesQuery == null) {
			return filesResponse;
		}
		HttpGet get = new HttpGet(url + files + filesQuery.type().toString().toLowerCase());
		Reader reader = null;
		try {
			HttpResponse response = httpClient.execute(get);
			response.getEntity().getContent();
			reader = new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8);
			switch (filesQuery.type()) {
				case SPELLINGS:
					JsonObject obj = parser.parse(reader).getAsJsonObject();
					Map<String, String> jsmap = gson.fromJson(obj, new TypeToken<Map<String, String>>() {
					}.getType());
					filesResponse.setSpellings(jsmap);
					break;
				case COORDINATIONS:
					JsonArray jsarray = parser.parse(reader).getAsJsonArray();
					List<String> jslist = gson.fromJson(jsarray, new TypeToken<List<String>>() {
					}.getType());
					filesResponse.setCoordinations(jslist);
					break;
				case NEGATIONS:
					jsarray = parser.parse(reader).getAsJsonArray();
					jslist = gson.fromJson(jsarray, new TypeToken<List<String>>() {
					}.getType());
					filesResponse.setNegations(jslist);
					break;
				case STOPWORDS:
					jsarray = parser.parse(reader).getAsJsonArray();
					jslist = gson.fromJson(jsarray, new TypeToken<List<String>>() {
					}.getType());
					filesResponse.setStopwords(jslist);
					break;
				default:
					break;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		finally {
			try {
				reader.close();
			}
			catch (Exception e) {
				
			}
		}
		return filesResponse;
	}
	
	public LexicalEntityResponse process(EntityQuery lexicalRequest) {
		LexicalEntityResponse lexicalResponse = new LexicalEntityResponse();
		if (lexicalRequest == null) {
			return lexicalResponse;
		}
		
		HttpPost post = new HttpPost(url + extract);
		
		List<NameValuePair> params = new ArrayList<NameValuePair>(6);
		params.add(new BasicNameValuePair("text", lexicalRequest.getText()));
		params.add(new BasicNameValuePair("expandAbbreviations", String.valueOf(lexicalRequest.isExpandAbbreviations())));
		params.add(new BasicNameValuePair("expandCoordinations", String.valueOf(lexicalRequest.isExpandCoordinations())));
		params.add(new BasicNameValuePair("detectNegation", String.valueOf(lexicalRequest.isDetectNegations())));
		params.add(new BasicNameValuePair("checkSpelling", String.valueOf(lexicalRequest.isCheckSpelling())));
		params.add(new BasicNameValuePair("extractEntitites", String.valueOf(lexicalRequest.isExtractEntities())));
		
		Reader reader = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = httpClient.execute(post);
			reader = new InputStreamReader(response.getEntity().getContent(), "UTF8");
			
			//System.out.println(IOUtils.toString(reader));
			
			JsonObject obj = parser.parse(reader).getAsJsonObject();
			
			Map<String, List<Map<String, Object>>> jsmap = gson.fromJson(obj, type);
			
			if (jsmap != null) {
				List<Map<String, Object>> entries = jsmap.get("entries");
				lexicalResponse.setEntries(entries.stream().map(entry -> LexicalEntry.create(entry)).collect(Collectors.toList()));
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
		return lexicalResponse;
	}
	
}
