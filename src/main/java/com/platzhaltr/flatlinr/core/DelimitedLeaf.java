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

	/** The features. */
	private final List<Feature> features;

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
	 * Instantiates a new delimited leaf.
	 * 
	 * @param name
	 *            the name
	 * @param features
	 *            the features
	 */
	public DelimitedLeaf(final String name, Feature... features) {
		this(name, DEFAULT_DELIMITER, features);
	}

	/**
	 * Instantiates a new delimited leaf.
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
	 * @param features
	 *            the features
	 */
	public DelimitedLeaf(final String name, final char delimiter,
			Feature... features) {
		this(name, delimiter + "", features);
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
		this(name, delimiter, new Feature[] {});
	}

	/**
	 * Instantiates a new delimited leaf.
	 * 
	 * @param name
	 *            the name
	 * @param delimiter
	 *            the delimiter
	 * @param features
	 *            the features
	 */
	public DelimitedLeaf(final String name, final String delimiter,
			Feature... features) {
		this.name = name;
		this.delimiter = delimiter;
		this.features = Arrays.asList(features);
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
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DelimitedLeaf other = (DelimitedLeaf) obj;
		if (delimiter == null) {
			if (other.delimiter != null)
				return false;
		} else if (!delimiter.equals(other.delimiter))
			return false;
		if (features == null) {
			if (other.features != null)
				return false;
		} else if (!features.equals(other.features))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DelimitedLeaf [name=" + name + ", delimiter=" + delimiter
				+ ", features=" + features + "]";
	}

}
