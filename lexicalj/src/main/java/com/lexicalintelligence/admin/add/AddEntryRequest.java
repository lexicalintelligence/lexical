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
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import com.lexicalintelligence.LexicalEntry;
import com.lexicalintelligence.admin.LexicalAdminClient;

public class AddEntryRequest extends AddRequest {
	private LexicalEntry entry;
	
	public AddEntryRequest(LexicalAdminClient client) {
		super(client);
	}
	
	public final LexicalEntry getEntry() {
		return entry;
	}
	
	public AddRequest item(LexicalEntry entry) {
		this.entry = entry;
		return this;
	}
	
	@Override
	public AddRequest items(String... items) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public String getName() {
		return "";
	}
	
	@Override
	public String getPath() {
		return "/entity";
	}
	
	public AddResponse execute() {
		List<BasicNameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("name", entry.getName()));
		params.add(new BasicNameValuePair("synonym", entry.getSynonym()));
		params.add(new BasicNameValuePair("matchWordOrder", Boolean.toString(entry.isOrderSensitive())));
		params.add(new BasicNameValuePair("caseSensitive", Boolean.toString(entry.isCaseSensitive())));
		params.add(new BasicNameValuePair("matchStopwords", Boolean.toString(entry.isMatchStopwords())));
		params.add(new BasicNameValuePair("matchPunctuation", Boolean.toString(entry.isMatchPunctuation())));
		params.add(new BasicNameValuePair("stem", Boolean.toString(entry.isStemmed())));
		
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
}
