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

import com.platzhaltr.flatlinr.api.Record;

/**
 * The Class FlatRecord.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class FlatRecord implements Record {

	/** The partials. */
	private final Map<String, String> partials;

	/** The name. */
	private final String name;

	/**
	 * Instantiates a new flat record.
	 *
	 * @param name
	 *            the name
	 */
	public FlatRecord(final String name) {
		this.name = name;
		partials = new HashMap<String, String>();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.platzhaltr.flatlinr.api.Record#getPartials()
	 */
	@Override
	public Map<String, String> getPartials() {
		return partials;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.platzhaltr.flatlinr.api.Record#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.platzhaltr.flatlinr.api.Record#addPartial(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void put(final String name, final String value) {
		partials.put(name, value);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.platzhaltr.flatlinr.api.Record#getPartial(java.lang.String)
	 */
	@Override
	public String get(final String name) {
		return partials.get(name);
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
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((partials == null) ? 0 : partials.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final FlatRecord other = (FlatRecord) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (partials == null) {
			if (other.partials != null)
				return false;
		} else if (!partials.equals(other.partials))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (partials.isEmpty()) {
			return "FlatRecord [name=" + name + "]";
		}
		return "FlatRecord [name=" + name + ", partials=" + partials + "]";
	}
}
