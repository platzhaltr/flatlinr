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
package com.platzhaltr.flatlinr.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains the hierachical flat file entries for a single line
 * 
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class Record {

	/** The entries. */
	private final Map<String, String> entries;

	/** The id. */
	private final String id;

	/**
	 * Instantiates a new flat record.
	 * 
	 * @param id
	 *            the id
	 */
	public Record(final String id) {
		this.id = id;
		entries = new HashMap<String, String>();
	}

	/**
	 * Gets the entries.
	 * 
	 * @return the entries
	 */
	public Map<String, String> getEntries() {
		return entries;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Put.
	 * 
	 * @param id
	 *            the id
	 * @param value
	 *            the value
	 */
	public void put(final String name, final String value) {
		entries.put(name, value);
	}

	/**
	 * Gets the.
	 * 
	 * @param id
	 *            the id
	 * @return the string
	 */
	public String get(final String name) {
		return entries.get(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((entries == null) ? 0 : entries.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Record other = (Record) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (entries == null) {
			if (other.entries != null) {
				return false;
			}
		} else if (!entries.equals(other.entries)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (entries.isEmpty()) {
			return "Record [id=" + id + "]";
		}
		return "Record [id=" + id + ", entries=" + entries + "]";
	}
}
