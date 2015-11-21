/* Copyright (c) 2015 Lexical Intelligence, LLC - All Rights Reserved
 * Redistribution and use in source and binary forms, with or without modification, are not permitted.
 * Kirk Baker
 */

package com.lexicalintelligence.admin;

import com.lexicalintelligence.LexicalClient;
import com.lexicalintelligence.LexicalEntry;
import com.lexicalintelligence.admin.add.AddCoordinationsRequest;
import com.lexicalintelligence.admin.add.AddEntryRequest;
import com.lexicalintelligence.admin.add.AddIdiomsRequest;
import com.lexicalintelligence.admin.add.AddNegationsRequest;
import com.lexicalintelligence.admin.add.AddRequest;
import com.lexicalintelligence.admin.add.AddSpellingsRequest;
import com.lexicalintelligence.admin.add.AddStopwordsRequest;
import com.lexicalintelligence.admin.get.GetRequest;
import com.lexicalintelligence.admin.get.ListGetRequest;
import com.lexicalintelligence.admin.get.MapGetRequest;
import com.lexicalintelligence.admin.remove.RemoveCoordinationsRequest;
import com.lexicalintelligence.admin.remove.RemoveEntryRequest;
import com.lexicalintelligence.admin.remove.RemoveIdiomsRequest;
import com.lexicalintelligence.admin.remove.RemoveNegationsRequest;
import com.lexicalintelligence.admin.remove.RemoveRequest;
import com.lexicalintelligence.admin.remove.RemoveSpellingsRequest;
import com.lexicalintelligence.admin.remove.RemoveStopwordsRequest;
import com.lexicalintelligence.admin.save.SaveRequest;
import com.lexicalintelligence.search.SearchRequest;

public class LexicalAdminClient extends LexicalClient {
	public LexicalAdminClient(String url) {
		super(url);
	}
	
	public SaveRequest saveLexicon() {
		return new SaveRequest(this) {
			@Override
			public String getPath() {
				return "/lexicon";
			}
		};
	}
	
	public SaveRequest saveCoordinations() {
		return new SaveRequest(this) {
			@Override
			public String getPath() {
				return "/coordinations";
			}
		};
	}
	
	public SaveRequest saveIdioms() {
		return new SaveRequest(this) {
			@Override
			public String getPath() {
				return "/idioms";
			}
		};
	}
	
	public SaveRequest saveSpellings() {
		return new SaveRequest(this) {
			@Override
			public String getPath() {
				return "/spellings";
			}
		};
	}
	
	public SaveRequest saveStopwords() {
		return new SaveRequest(this) {
			@Override
			public String getPath() {
				return "/stopwords";
			}
		};
	}
	
	public SaveRequest saveNegations() {
		return new SaveRequest(this) {
			@Override
			public String getPath() {
				return "/negations";
			}
		};
	}
	
	public AddRequest addEntry(LexicalEntry entry) {
		return new AddEntryRequest(this).item(entry);
	}
	
	public AddRequest addStopwords(String... items) {
		return new AddStopwordsRequest(this).items(items);
	}
	
	public AddRequest addCoordinations(String... items) {
		return new AddCoordinationsRequest(this).items(items);
	}
	
	public AddRequest addNegations(String... items) {
		return new AddNegationsRequest(this).items(items);
	}
	
	public AddRequest addIdioms(String... items) {
		return new AddIdiomsRequest(this).items(items);
	}
	
	public AddRequest addSpellings(String... items) {
		return new AddSpellingsRequest(this).items(items);
	}
	
	public RemoveRequest removeCoordinations(String... items) {
		return new RemoveCoordinationsRequest(this).items(items);
	}
	
	public RemoveRequest removeStopwords(String... items) {
		return new RemoveStopwordsRequest(this).items(items);
	}
	
	public RemoveRequest removeNegations(String... items) {
		return new RemoveNegationsRequest(this).items(items);
	}
	
	public RemoveRequest removeIdioms(String... items) {
		return new RemoveIdiomsRequest(this).items(items);
	}
	
	public RemoveRequest removeSpellings(String... items) {
		return new RemoveSpellingsRequest(this).items(items);
	}
	
	public RemoveRequest removeEntry(LexicalEntry entry) {
		return new RemoveEntryRequest(this).item(entry);
	}
	
	public GetRequest searchSpellings() {
		return new MapGetRequest(this) {
			
			@Override
			public String getPath() {
				return "/spellings";
			}
		};
	}
	
	public GetRequest searchStopwords() {
		return new ListGetRequest(this) {
			@Override
			public String getPath() {
				return "/stopwords";
			}
		};
	}
	
	public SearchRequest prepareSearch(String query) {
		return new SearchRequest(this).setQuery(query);
	}
}
