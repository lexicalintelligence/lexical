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
import java.util.Collections;
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

public class AddRequestHandler {
	private Log log = LogFactory.getLog(AddRequestHandler.class);
	
	private static AddRequestHandler instance = new AddRequestHandler();
	
	private AddRequestHandler() {
		
	}
	
	public static AddRequestHandler get() {
		return instance;
	}
	
	public AddResponse handleAddRequest(HttpClient client, String url, AddRequest request) {
		AddResponse addResponse = new AddResponse();
		if (request == null) {
			return addResponse;
		}
		List<NameValuePair> params = Collections.singletonList(new BasicNameValuePair(request.getType().toString().toLowerCase(), StringUtils.join(
						request.getItems(), ",")));
		Reader reader = null;
		try {
			HttpResponse response = client.execute(new HttpGet(url + URLEncodedUtils.format(params, StandardCharsets.UTF_8)));
			reader = new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8);
			addResponse.setAdded(Boolean.valueOf(IOUtils.toString(reader)));
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
