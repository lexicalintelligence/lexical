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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.lexicalintelligence.add.AddCoordinationsRequest;
import com.lexicalintelligence.add.AddEntryRequest;
import com.lexicalintelligence.add.AddIdiomsRequest;
import com.lexicalintelligence.add.AddNegationsRequest;
import com.lexicalintelligence.add.AddRequestHandler;
import com.lexicalintelligence.add.AddResponse;
import com.lexicalintelligence.add.AddSpellingRequest;
import com.lexicalintelligence.add.AddStopwordsRequest;
import com.lexicalintelligence.extract.ExtractRequest;
import com.lexicalintelligence.extract.ExtractResponse;
import com.lexicalintelligence.get.GetCoordinationsResponse;
import com.lexicalintelligence.get.GetIdiomsResponse;
import com.lexicalintelligence.get.GetNegationsResponse;
import com.lexicalintelligence.get.GetRequestType;
import com.lexicalintelligence.get.GetResponse;
import com.lexicalintelligence.get.GetSpellingsResponse;
import com.lexicalintelligence.get.GetStopwordsResponse;
import com.lexicalintelligence.remove.RemoveRequest;
import com.lexicalintelligence.remove.RemoveRequestHandler;
import com.lexicalintelligence.remove.RemoveResponse;
import com.lexicalintelligence.save.SaveRequestType;
import com.lexicalintelligence.save.SaveResponse;

public class LexicalClient {
	protected final Log log = LogFactory.getLog(LexicalClient.class);
	private static HttpClient httpClient = HttpClientBuilder.create().build();
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	private String url;
	
	private String extract = "/extract";
	private String get = "/get/";
	private String delete = "/remove?";
	
	public LexicalClient(String url) {
		System.setProperty("jsse.enableSNIExtension", "false");
		this.url = url;
	}
	
	public AddResponse submit(AddEntryRequest request) {
		return AddRequestHandler.get().handleAddEntryRequest(httpClient, url, request);
	}
	
	public AddResponse submit(AddCoordinationsRequest request) {
		return AddRequestHandler.get().handleAddCoordinationRequest(httpClient, url, request);
	}
	
	public AddResponse submit(AddNegationsRequest request) {
		return AddRequestHandler.get().handleAddNegationsRequest(httpClient, url, request);
	}
	
	public AddResponse submit(AddIdiomsRequest request) {
		return AddRequestHandler.get().handleAddIdiomsRequest(httpClient, url, request);
	}
	
	public AddResponse submit(AddStopwordsRequest request) {
		return AddRequestHandler.get().handleAddStopwordsRequest(httpClient, url, request);
	}
	
	public AddResponse submit(AddSpellingRequest request) {
		return AddRequestHandler.get().handleAddSpellingRequest(httpClient, url, request);
	}
	
	public SaveResponse submit(SaveRequestType type) {
		SaveResponse saveResponse = new SaveResponse();
		HttpGet get = new HttpGet(url + "/save/" + type.toString().toLowerCase());
		Reader reader = null;
		try {
			HttpResponse response = httpClient.execute(get);
			reader = new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8);
			saveResponse.setSaved(Boolean.valueOf(IOUtils.toString(reader)));
		}
		catch (Exception e) {
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
		return saveResponse;
	}
	
	public RemoveResponse submit(RemoveRequest removeRequest) {
		return RemoveRequestHandler.get().handleRemoveRequest(httpClient, url, removeRequest);
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
			removeResponse.setRemoved(Boolean.valueOf(IOUtils.toString(reader)));
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
	
	@SuppressWarnings("unchecked")
	public <T extends GetResponse> T submit(GetRequestType type) {
		Reader reader = null;
		try {
			HttpResponse response = httpClient.execute(new HttpGet(url + get + type.toString().toLowerCase()));
			reader = new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8);
			switch (type) {
				case Coordinations:
					return (T) new GetCoordinationsResponse(mapper.readValue(reader, new TypeReference<List<String>>() {
					}));
				case Negations:
					return (T) new GetNegationsResponse(mapper.readValue(reader, new TypeReference<List<String>>() {
					}));
				case Idioms:
					return (T) new GetIdiomsResponse(mapper.readValue(reader, new TypeReference<List<String>>() {
					}));
				case Spelling:
					return (T) new GetSpellingsResponse(mapper.readValue(reader, new TypeReference<Map<String, String>>() {
					}));
				case Stopwords:
					return (T) new GetStopwordsResponse(mapper.readValue(reader, new TypeReference<List<String>>() {
					}));
			}
		}
		catch (Exception e) {
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
		return null;
	}
	
	public ExtractResponse submit(ExtractRequest entityRequest) {
		ExtractResponse lexicalResponse = new ExtractResponse();
		if (entityRequest == null) {
			return lexicalResponse;
		}
		
		HttpPost post = new HttpPost(url + extract);
		
		List<NameValuePair> params = new ArrayList<NameValuePair>(6);
		params.add(new BasicNameValuePair("text", entityRequest.getText()));
		params.add(new BasicNameValuePair("expandAbbreviations", String.valueOf(entityRequest.isExpandAbbreviations())));
		params.add(new BasicNameValuePair("expandCoordinations", String.valueOf(entityRequest.isExpandCoordinations())));
		params.add(new BasicNameValuePair("detectNegation", String.valueOf(entityRequest.isDetectNegations())));
		params.add(new BasicNameValuePair("checkSpelling", String.valueOf(entityRequest.isCheckSpelling())));
		params.add(new BasicNameValuePair("extractEntitites", String.valueOf(entityRequest.isExtractEntities())));
		
		Reader reader = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = httpClient.execute(post);
			reader = new InputStreamReader(response.getEntity().getContent(), "UTF8");
			
			Map<String, List<LexicalEntry>> result = mapper.readValue(reader, new TypeReference<Map<String, List<LexicalEntry>>>() {
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
}
