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

package com.lexicalintelligence.add;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class AddRequestHandler {
	public static AddResponse handleAddCoordinationRequest(HttpClient client, String url, AddCoordinationsRequest request) {
		AddResponse addResponse = new AddResponse();
		if (request == null) {
			return addResponse;
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>(1);
		params.add(new BasicNameValuePair("coordinations", StringUtils.join(request.getCoordinations(), ",")));
		Reader reader = null;
		try {
			HttpResponse response = client.execute(new HttpGet(url + "/add/coordinations?" + URLEncodedUtils.format(params, StandardCharsets.UTF_8)));

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

			}
		}
		return addResponse;
	}
	
	public static AddResponse handleAddNegationsRequest(HttpClient httpClient, String url, AddNegationsRequest request) {
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
			e.printStackTrace();
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

	public static AddResponse handleAddIdiomsRequest(HttpClient httpClient, String url, AddIdiomsRequest request) {
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
			e.printStackTrace();
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

	public static AddResponse handleAddStopwordsRequest(HttpClient httpClient, String url, AddStopwordsRequest request) {
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
			e.printStackTrace();
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

	public static AddResponse handleAddSpellingRequest(HttpClient httpClient, String url, AddSpellingRequest request) {
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

			}
		}
		return addResponse;
	}
}
