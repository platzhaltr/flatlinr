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
 * The Class LineLeaf.
 * 
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class LineLeaf implements Leaf {

	/** The id. */
	private final String id;

	/** The features. */
	private final List<Feature> features;

	/**
	 * Instantiates a new line leaf.
	 * 
	 * @param id
	 *            the id
	 */
	public LineLeaf(String id) {
		this(id, new Feature[] {});
	}

	/**
	 * Instantiates a new line leaf.
	 * 
	 * @param id
	 *            the id
	 * @param features
	 *            the features
	 */
	public LineLeaf(String id, Feature... features) {
		super();
		this.id = id;
		this.features = Arrays.asList(features);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.platzhaltr.flatlinr.api.Leaf#getId()
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * Gets the features.
	 * 
	 * @return the features
	 */
	public List<Feature> getFeatures() {
		return features;
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
				+ ((features == null) ? 0 : features.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineLeaf other = (LineLeaf) obj;
		if (features == null) {
			if (other.features != null)
				return false;
		} else if (!features.equals(other.features))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		return "LineLeaf [" + (id != null ? "id=" + id : "") + "]";
	}

}
