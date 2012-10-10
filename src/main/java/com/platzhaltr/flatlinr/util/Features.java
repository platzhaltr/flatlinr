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
 * Default Features. A feature transforms the string of a single record entry.
 * 
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class Features {

	/** The trim. */
	public static Feature TRIM = new TrimFeature();

	/** The lower case. */
	public static Feature LOWER_CASE = new LowerCaseFeature();

	/** The upper case. */
	public static Feature UPPER_CASE = new UpperCaseFeature();

	/**
	 * Replace.
	 * 
	 * @param pattern
	 *            the pattern
	 * @param replacement
	 *            the replacement
	 * @return the feature
	 */
	public static Feature REPLACE(final String pattern, final String replacement) {
		return new ReplaceFeature(pattern, replacement);
	}

}
