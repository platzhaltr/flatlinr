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
package com.platzhaltr.flatlinr.api;

import java.util.Map;

/**
 * The Interface Record.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public interface Record {

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	String getName();

	/**
	 * Gets the partials.
	 *
	 * @return the partials
	 */
	Map<String, String> getPartials();

	/**
	 * Put.
	 *
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	void put(final String key, final String value);

	/**
	 * Gets the.
	 *
	 * @param key
	 *            the key
	 * @return the string
	 */
	String get(final String key);
}
