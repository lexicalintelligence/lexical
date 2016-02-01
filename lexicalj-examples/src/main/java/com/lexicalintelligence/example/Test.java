/* Copyright (c) 2015 Lexical Intelligence, LLC - All Rights Reserved
 * Redistribution and use in source and binary forms, with or without modification, are not permitted.
 * Kirk Baker
 */

package com.lexicalintelligence.example;

import java.nio.file.Files;
import java.nio.file.Paths;

import com.lexicalintelligence.LexicalClient;
import com.lexicalintelligence.action.extract.ExtractResponse;

/**
 * @author Kirk Baker
 * Dec 31, 2015
 *
 */
public class Test {
	public static void main(String[] args) {
		try {
			LexicalClient lexical = new LexicalClient("http://localhost:8080/lexicon/rcdc");
			String title = new String(Files.readAllBytes(Paths.get("/home/kbaker/Downloads/title.txt")));
			//System.out.println(title);
			String text = new String(Files.readAllBytes(Paths.get("/home/kbaker/Downloads/text.txt")));
			//System.out.println(text);
			
			String full = text; //.substring(4085, 4125);
			
			ExtractResponse extractResponse = lexical.prepareExtract().setExpandAbbreviations(true).setExtractKnown(true).setText(full).execute();
			extractResponse.getEntries().stream().forEach(e -> {
				System.out.println("\t" + e.getName() + " -> '" + full.substring(e.getStart(), e.getEnd()) + "'");
			});
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
