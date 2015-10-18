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

package com.lexicalintelligence.admin.add;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import com.lexicalintelligence.admin.LexicalAdminClient;

public abstract class AddRequest {
	protected final Log log = LogFactory.getLog(AddRequest.class);
	protected static final String PATH = "/add";
	
	protected LexicalAdminClient client;
	protected Collection<String> items;
	
	public AddRequest(LexicalAdminClient client) {
		this.client = client;
	}
	
	public AddRequest items(String... items) {
		if (items != null) {
			for (String item : items) {
				this.items.add(item);
			}
		}
		return this;
	}
	
	public AddResponse execute() {
		List<BasicNameValuePair> params = Collections.singletonList(new BasicNameValuePair(getName(), StringUtils.join(items, ",")));
		AddResponse addResponse;
		Reader reader = null;
		try {
			HttpResponse response = client.getHttpClient().execute(
							new HttpGet(client.getUrl() + PATH + getPath() + "?" + URLEncodedUtils.format(params, StandardCharsets.UTF_8)));
			reader = new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8);
			addResponse = new AddResponse(Boolean.valueOf(IOUtils.toString(reader)));
		}
		catch (Exception e) {
			addResponse = new AddResponse(false);
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
	
	public abstract String getName();
	
	public abstract String getPath();
}
