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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.codehaus.jackson.map.ObjectMapper;

import com.lexicalintelligence.action.extract.BatchExtractRequest;
import com.lexicalintelligence.action.extract.ExtractRequest;

public class LexicalClient {
	protected final Log log = LogFactory.getLog(LexicalClient.class);
	private static final PoolingHttpClientConnectionManager cm;
	
	static {
		cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(16);
	}
	
	private static CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
	private static ObjectMapper mapper = new ObjectMapper();
	
	private String url;
	
	public LexicalClient(String url) {
		System.setProperty("jsse.enableSNIExtension", "false");
		this.url = url;
	}
	
	public ExtractRequest prepareExtract() {
		return new ExtractRequest(this);
	}
	
	public String getUrl() {
		return url;
	}
	
	public CloseableHttpClient getHttpClient() {
		return httpClient;
	}
	
	public ObjectMapper getObjectMapper() {
		return mapper;
	}
	
	public BatchExtractRequest prepareBatch() {
		return new BatchExtractRequest(this);
	}
}
