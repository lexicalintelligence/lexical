/* COPYRIGHT (C) 2016 Lexical Intelligence, LLC. All Rights Reserved. */

package com.lexicalintelligence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kirk Baker
 *
 */
public class LexicalDocument {
	private String id = "";;
	private Map<String, List<String>> fields;

	public LexicalDocument() {
		fields = new HashMap<>();
	}
	
	public LexicalDocument setId(String id) {
		if (id != null) {
			this.id = id;
		}
		return this;
	}

	public String getId() {
		return id;
	}

	public Collection<String> getFields() {
		return fields.keySet();
	}

	public List<String> getTextField(String key) {
		List<String> values = fields.get(key);
		return values == null ? Collections.emptyList() : values;
	}
	
	public LexicalDocument setTextField(String key, String value) {
		if (key != null && value != null) {
			List<String> values = new ArrayList<>(1);
			values.add(value);
			fields.put(key, values);
		}
		return this;
	}
	
	public LexicalDocument addTextField(String key, String value) {
		if (key != null && value != null) {
			List<String> values = fields.get(key);
			if (values == null) {
				values = new ArrayList<>(1);
				fields.put(key, values);
			}
			values.add(value);
		}
		return this;
	}
}
