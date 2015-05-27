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

package com.lexicalintelligence.query;

public class EntityQuery {
	private String text = "";
	private boolean expandAbbreviations = true;
	private boolean expandCoordinations = true;
	private boolean detectNegations = true;
	private boolean checkSpelling = true;
	private boolean extractEntities = true;
	
	public EntityQuery(String text) {
		if (text != null) {
			this.text = text;
		}
	}
	
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * @return the expandAbbreviations
	 */
	public boolean isExpandAbbreviations() {
		return expandAbbreviations;
	}
	
	/**
	 * @return the expandCoordinations
	 */
	public boolean isExpandCoordinations() {
		return expandCoordinations;
	}
	
	/**
	 * @return the detectNegations
	 */
	public boolean isDetectNegations() {
		return detectNegations;
	}
	
	/**
	 * @return the checkSpelling
	 */
	public boolean isCheckSpelling() {
		return checkSpelling;
	}
	
	/**
	 * @return the extractConcepts
	 */
	public boolean isExtractEntities() {
		return extractEntities;
	}
	
	/**
	 * @param text the text to set
	 * @return this
	 */
	public EntityQuery setText(String text) {
		if (text != null) {
			this.text = text;
		}
		return this;
	}
	
	/**
	 * @param expandAbbreviations the expandAbbreviations to set
	 * @return this
	 */
	public EntityQuery setExpandAbbreviations(boolean expandAbbreviations) {
		this.expandAbbreviations = expandAbbreviations;
		return this;
	}
	
	/**
	 * @param expandCoordinations the expandCoordinations to set
	 * @return this
	 */
	public EntityQuery setExpandCoordinations(boolean expandCoordinations) {
		this.expandCoordinations = expandCoordinations;
		return this;
	}
	
	/**
	 * @param detectNegations the detectNegations to set
	 * @return this
	 */
	public EntityQuery setDetectNegations(boolean detectNegations) {
		this.detectNegations = detectNegations;
		return this;
	}
	
	/**
	 * @param checkSpelling the checkSpelling to set
	 * @return this
	 */
	public EntityQuery setCheckSpelling(boolean checkSpelling) {
		this.checkSpelling = checkSpelling;
		return this;
	}
	
	/**
	 * @param extractEntities the extractEntities to set
	 * @return this
	 */
	public EntityQuery setExtractEntities(boolean extractEntities) {
		this.extractEntities = extractEntities;
		return this;
	}
}
