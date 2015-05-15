package com.lexicalintelligence;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
	private static Type type = new TypeToken<Map<String, List<Map<String, String>>>>() {
	}.getType();
	
	private String url;
	private Gson gson;
	
	public LexicalClient(String url) {
		this.url = url; // + "/process";
		gson = new Gson();
	}
	
	public Map<Integer, Double> index(String title, String text) {
		Map<Integer, Double> result = new HashMap<>();
		HttpPost post = new HttpPost(url);
		
		List<NameValuePair> params = new ArrayList<NameValuePair>(1);
		if (title != null) {
			//params.add(new BasicNameValuePair("title", title));
		}
		if (text != null) {
			params.add(new BasicNameValuePair("text", text));
			params.add(new BasicNameValuePair("text", "what the"));
		}
		params.add(new BasicNameValuePair("extractConcepts", "true"));
		params.add(new BasicNameValuePair("expandAbbreviations", "true"));
		params.add(new BasicNameValuePair("expandCoordinations", "true"));
		params.add(new BasicNameValuePair("filterNegations", "true"));
		
		Reader reader = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = httpClient.execute(post);
			reader = new InputStreamReader(response.getEntity().getContent(), "UTF8");
			
			JsonParser parser = new JsonParser();
			JsonObject obj = parser.parse(reader).getAsJsonObject();
			Map<String, List<Map<String, String>>> jsmap = gson.fromJson(obj, type);
			
			if (jsmap != null) {
				List<Map<String, String>> concepts = jsmap.get("text");
				double maxCount = 0.0;
				if (concepts != null) {
					for (Map<String, String> concept : concepts) {
						Integer cui = Integer.valueOf(concept.get("cui"));
						Double count = result.get(cui);
						count = count == null ? 1 : count + 1;
						maxCount = Math.max(maxCount, count);
						result.put(cui, count);
					}
				}
				else {
					maxCount = 1.0;
				}
				concepts = jsmap.get("title");
				if (concepts != null) {
					for (Map<String, String> concept : concepts) {
						Integer cui = Integer.valueOf(concept.get("cui"));
						result.put(cui, maxCount);
					}
				}
				if (maxCount > 1) {
					maxCount = Math.sqrt(maxCount);
					for (Entry<Integer, Double> entry : result.entrySet()) {
						result.put(entry.getKey(), Math.sqrt(entry.getValue()) / maxCount);
					}
				}
			}
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
				}
			}
		}
		return result;
	}
}
