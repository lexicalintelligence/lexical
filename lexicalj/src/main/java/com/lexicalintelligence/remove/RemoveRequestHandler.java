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

package com.lexicalintelligence.remove;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import com.lexicalintelligence.LexicalEntry;
import com.lexicalintelligence.add.AddEntryRequest;
import com.lexicalintelligence.add.AddIdiomsRequest;
import com.lexicalintelligence.add.AddNegationsRequest;
import com.lexicalintelligence.add.AddResponse;
import com.lexicalintelligence.add.AddSpellingRequest;
import com.lexicalintelligence.add.AddStopwordsRequest;

public class RemoveRequestHandler {
	private Log log = LogFactory.getLog(RemoveRequestHandler.class);
	
	private static RemoveRequestHandler instance = new RemoveRequestHandler();
	
	private RemoveRequestHandler() {
		
	}
	
	public static RemoveRequestHandler get() {
		return instance;
	}
	
	public RemoveResponse handleRemoveRequest(HttpClient client, String url, RemoveRequest request) {
		RemoveResponse removeResponse = new RemoveResponse();
		if (request == null) {
			return removeResponse;
		}
		RemoveRequest.Type type = request.getType();
		url += "/remove/" + request.getType().toString().toLowerCase() + "?";
		List<NameValuePair> params = new ArrayList<NameValuePair>(1);
		switch (type) {
			case Coordinations:
				params.add(new BasicNameValuePair("coordinations", StringUtils.join(request.getItems(), ",")));
				break;
			case Idioms:
				params.add(new BasicNameValuePair("idioms", StringUtils.join(request.getItems(), ",")));
				break;
			case Negations:
				params.add(new BasicNameValuePair("negations", StringUtils.join(request.getItems(), ",")));
				break;
			case Spelling:
				params.add(new BasicNameValuePair("spellings", StringUtils.join(request.getItems(), ",")));
				break;
			case Stopwords:
				params.add(new BasicNameValuePair("stopwords", StringUtils.join(request.getItems(), ",")));
				break;
			case Entity:
				RemoveEntityRequest rer = (RemoveEntityRequest) request;
				params.add(new BasicNameValuePair("name", rer.getName()));
				params.add(new BasicNameValuePair("synonym", rer.getSynonym()));
				break;
		}
		
		Reader reader = null;
		try {
			HttpResponse response = client.execute(new HttpGet(url + URLEncodedUtils.format(params, StandardCharsets.UTF_8)));
			reader = new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8);
			boolean added = Boolean.valueOf(IOUtils.toString(reader));
			removeResponse.setRemoved(added);
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
		return removeResponse;
	}
	
	public AddResponse handleAddNegationsRequest(HttpClient httpClient, String url, AddNegationsRequest request) {
		AddResponse addResponse = new AddResponse();
		if (request == null) {
			return addResponse;
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>(1);
		params.add(new BasicNameValuePair("negations", StringUtils.join(request.getNegations(), ",")));
		Reader reader = null;
		try {
			HttpResponse response = httpClient.execute(new HttpGet(url + "/add/negations?" + URLEncodedUtils.format(params, StandardCharsets.UTF_8)));
			
			reader = new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8);
			boolean added = Boolean.valueOf(IOUtils.toString(reader));
			addResponse.setAdded(added);
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
		return addResponse;
	}
	
	public AddResponse handleAddIdiomsRequest(HttpClient httpClient, String url, AddIdiomsRequest request) {
		AddResponse addResponse = new AddResponse();
		if (request == null) {
			return addResponse;
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>(1);
		params.add(new BasicNameValuePair("idioms", StringUtils.join(request.getIdioms(), ",")));
		Reader reader = null;
		try {
			HttpResponse response = httpClient.execute(new HttpGet(url + "/add/idioms?" + URLEncodedUtils.format(params, StandardCharsets.UTF_8)));
			reader = new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8);
			boolean added = Boolean.valueOf(IOUtils.toString(reader));
			addResponse.setAdded(added);
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
		return addResponse;
	}
	
	public AddResponse handleAddStopwordsRequest(HttpClient httpClient, String url, AddStopwordsRequest request) {
		AddResponse addResponse = new AddResponse();
		if (request == null) {
			return addResponse;
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>(1);
		params.add(new BasicNameValuePair("stopwords", StringUtils.join(request.getStopwords(), ",")));
		Reader reader = null;
		try {
			HttpResponse response = httpClient.execute(new HttpGet(url + "/add/stopwords?" + URLEncodedUtils.format(params, StandardCharsets.UTF_8)));
			reader = new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8);
			boolean added = Boolean.valueOf(IOUtils.toString(reader));
			addResponse.setAdded(added);
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
		return addResponse;
	}
	
	public AddResponse handleAddSpellingRequest(HttpClient httpClient, String url, AddSpellingRequest request) {
		AddResponse addResponse = new AddResponse();
		if (request == null) {
			return addResponse;
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>(1);
		List<String> spellings = new ArrayList<String>(request.getSpellings().size() * 2);
		request.getSpellings().entrySet().stream().forEach(entry -> {
			spellings.add(entry.getKey());
			spellings.add(entry.getValue());
		});
		params.add(new BasicNameValuePair("spellings", StringUtils.join(spellings, ",")));
		Reader reader = null;
		try {
			HttpResponse response = httpClient.execute(new HttpGet(url + "/add/spelling?" + URLEncodedUtils.format(params, StandardCharsets.UTF_8)));
			reader = new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8);
			boolean added = Boolean.valueOf(IOUtils.toString(reader));
			addResponse.setAdded(added);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			}
			catch (Exception e) {
				log.error(e);
			}
		}
		return addResponse;
	}
	
	public AddResponse handleAddEntryRequest(HttpClient httpClient, String url, AddEntryRequest request) {
		AddResponse addResponse = new AddResponse();
		LexicalEntry entry = request.getEntry();
		if (request == null || entry == null) {
			return addResponse;
		}
		
		List<NameValuePair> params = new ArrayList<NameValuePair>(6);
		params.add(new BasicNameValuePair("name", entry.getName()));
		params.add(new BasicNameValuePair("synonym", entry.getSynonym()));
		if (entry.getId() > 0) {
			params.add(new BasicNameValuePair("id", entry.getId() + ""));
		}
		
		params.add(new BasicNameValuePair("matchWordOrder", String.valueOf(entry.isOrderSensitive())));
		params.add(new BasicNameValuePair("caseSensitive", String.valueOf(entry.isCaseSensitive())));
		params.add(new BasicNameValuePair("matchStopwords", String.valueOf(entry.isMatchStopwords())));
		
		// Not used by the lexicon
		// params.add(new BasicNameValuePair("matchPunctuation", String.valueOf(addQuery.isMatchPunctuation())));
		
		HttpGet get = new HttpGet(url + "/add/concept?" + URLEncodedUtils.format(params, StandardCharsets.UTF_8));
		Reader reader = null;
		try {
			HttpResponse response = httpClient.execute(get);
			reader = new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8);
			boolean added = Boolean.valueOf(IOUtils.toString(reader));
			addResponse.setAdded(added);
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
		return addResponse;
	}
}
