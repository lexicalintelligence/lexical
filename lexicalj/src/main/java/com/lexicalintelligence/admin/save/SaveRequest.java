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

package com.lexicalintelligence.admin.save;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import com.lexicalintelligence.admin.LexicalAdminClient;

public abstract class SaveRequest {
	protected final Log log = LogFactory.getLog(SaveRequest.class);
	protected static final String PATH = "/save";
	
	protected LexicalAdminClient client;
	
	public SaveRequest(LexicalAdminClient client) {
		this.client = client;
	}
	
	public SaveResponse execute() {
		SaveResponse saveResponse;
		Reader reader = null;
		try {
			HttpResponse response = client.getHttpClient().execute(new HttpGet(client.getUrl() + PATH + getPath()));
			reader = new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8);
			saveResponse = new SaveResponse(Boolean.valueOf(IOUtils.toString(reader)));
		}
		catch (Exception e) {
			saveResponse = new SaveResponse(false);
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
	
	public abstract String getPath();
}
