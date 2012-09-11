/*
 * Copyright 2011 Oliver Schrenk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.platzhaltr.flatlinr.util;

import com.platzhaltr.flatlinr.api.Feature;

/**
 * Replaces each substring of this string that matches the given regular
 * expression with the given replacement.
 * 
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class ReplaceFeature implements Feature {

	/** The pattern. */
	private String pattern;

	/** The replacement. */
	private String replacement;

	/**
	 * Instantiates a new replace feature.
	 * 
	 * @param pattern
	 *            the pattern
	 * @param replacement
	 *            the replacement
	 */
	public ReplaceFeature(String pattern, String replacement) {
		this.pattern = pattern;
		this.replacement = replacement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.platzhaltr.flatlinr.api.Feature#convert(java.lang.String)
	 */
	@Override
	public String convert(String s) {
		return s.replaceAll(pattern, replacement);
	}
}
