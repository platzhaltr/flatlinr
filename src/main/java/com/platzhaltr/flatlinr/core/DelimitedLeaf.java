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

import com.platzhaltr.flatlinr.api.Leaf;

/**
 * The Class DelimitedLeaf.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class DelimitedLeaf implements Leaf {

	/** The Constant DEFAULT_DELIMITER. */
	private static final String DEFAULT_DELIMITER = ";";

	/** The name. */
	private final String name;

	/** The delimiter. */
	private final String delimiter;

	/**
	 * Instantiates a new delimited leaf with the <b>default delimiter</b>
	 * <code>;</code>
	 *
	 * @param name
	 *            the name
	 */
	public DelimitedLeaf(final String name) {
		this(name, DEFAULT_DELIMITER);
	}

	/**
	 * Instantiates a new delimited leaf with the given delimiter
	 *
	 * @param name
	 *            the name
	 * @param delimiter
	 *            the delimiter
	 */
	public DelimitedLeaf(final String name, final char delimiter) {
		this(name, delimiter + "");
	}

	/**
	 * Instantiates a new delimited leaf.
	 *
	 * @param name
	 *            the name
	 * @param delimiter
	 *            the delimiter
	 */
	public DelimitedLeaf(final String name, final String delimiter) {
		this.name = name;
		this.delimiter = delimiter;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.platzhaltr.flatlinr.api.Leaf#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Gets the delimiter.
	 *
	 * @return the delimiter
	 */
	public String getDelimiter() {
		return delimiter;
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
		result = prime * result
				+ ((delimiter == null) ? 0 : delimiter.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		final DelimitedLeaf other = (DelimitedLeaf) obj;
		if (delimiter == null) {
			if (other.delimiter != null)
				return false;
		} else if (!delimiter.equals(other.delimiter))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
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
		return "DelimitedLeaf [name=" + name + ", delimiter=" + delimiter + "]";
	}

}
