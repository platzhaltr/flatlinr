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

import java.util.Arrays;
import java.util.List;

import com.platzhaltr.flatlinr.api.Feature;
import com.platzhaltr.flatlinr.api.Leaf;

/**
 * A leaf of variable length delimited using a delimter.
 * 
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class DelimitedLeaf implements Leaf {

	/** The Constant DEFAULT_DELIMITER. */
	private static final String DEFAULT_DELIMITER = ";";

	/** The id. */
	private final String id;

	/** The delimiter. */
	private final String delimiter;

	/** The features. */
	private final List<Feature> features;

	/**
	 * Instantiates a new delimited leaf using default delimiter <code>;</code>
	 * 
	 * @param id
	 *            the id
	 */
	public DelimitedLeaf(final String id) {
		this(id, DEFAULT_DELIMITER);
	}

	/**
	 * Instantiates a new delimited leaf using default delimiter <code>;</code>
	 * 
	 * @param id
	 *            the id
	 * @param features
	 *            the features
	 */
	public DelimitedLeaf(final String id, final Feature... features) {
		this(id, DEFAULT_DELIMITER, features);
	}

	/**
	 * Instantiates a new delimited leaf.
	 * 
	 * @param id
	 *            the id
	 * @param delimiter
	 *            the delimiter
	 */
	public DelimitedLeaf(final String id, final char delimiter) {
		this(id, delimiter + "");
	}

	/**
	 * Instantiates a new delimited leaf.
	 * 
	 * @param id
	 *            the id
	 * @param delimiter
	 *            the delimiter
	 * @param features
	 *            the features
	 */
	public DelimitedLeaf(final String id, final char delimiter,
			final Feature... features) {
		this(id, delimiter + "", features);
	}

	/**
	 * Instantiates a new delimited leaf.
	 * 
	 * @param id
	 *            the id
	 * @param delimiter
	 *            the delimiter
	 */
	public DelimitedLeaf(final String id, final String delimiter) {
		this(id, delimiter, new Feature[] {});
	}

	/**
	 * Instantiates a new delimited leaf.
	 * 
	 * @param id
	 *            the id
	 * @param delimiter
	 *            the delimiter
	 * @param features
	 *            the features
	 */
	public DelimitedLeaf(final String id, final String delimiter,
			final Feature... features) {
		this.id = id;
		this.delimiter = delimiter;
		this.features = Arrays.asList(features);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.platzhaltr.flatlinr.api.Leaf#getName()
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * Gets the delimiter.
	 * 
	 * @return the delimiter
	 */
	public String getDelimiter() {
		return delimiter;
	}

	/**
	 * Gets the features.
	 * 
	 * @return the features
	 */
	public List<Feature> getFeatures() {
		return features;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((delimiter == null) ? 0 : delimiter.hashCode());
		result = prime * result
				+ ((features == null) ? 0 : features.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

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
		final DelimitedLeaf other = (DelimitedLeaf) obj;
		if (delimiter == null) {
			if (other.delimiter != null) {
				return false;
			}
		} else if (!delimiter.equals(other.delimiter)) {
			return false;
		}
		if (features == null) {
			if (other.features != null) {
				return false;
			}
		} else if (!features.equals(other.features)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "DelimitedLeaf [id=" + id + ", delimiter=" + delimiter
				+ ", features=" + features + "]";
	}

}
