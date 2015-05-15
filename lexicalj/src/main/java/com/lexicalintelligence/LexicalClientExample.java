/* Copyright (c) 2015 Lexical Intelligence, LLC - All Rights Reserved
 * Redistribution and use in source and binary forms, with or without modification, are not permitted.
 * Kirk Baker
 */

package com.lexicalintelligence;

/**
 * @author Kirk Baker
 * May 14, 2015
 *
 */
public class LexicalClientExample {
	public static void main(String[] args) {
		LexicalClient lexical = new LexicalClient("http://localhost:8080/indexer/mesh");
		System.out.println(lexical.index(null, "lung and brain cancer"));
		
	}
	
}
