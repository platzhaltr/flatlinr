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
 * The Class ConstantLeaf.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class ConstantLeaf implements Leaf {

	/** The constant. */
	private final String constant;

	/** The id. */
	private final String id;

	/**
	 * Instantiates a new constant leaf.
	 *
	 * @param constant
	 *            the constant
	 */
	public ConstantLeaf(final String constant) {
		this(constant, constant);
	}

	/**
	 * Instantiates a new constant leaf.
	 *
	 * @param id
	 *            the id
	 * @param constant
	 *            the constant
	 */
	public ConstantLeaf(final String name, final String constant) {
		super();
		this.id = name;
		this.constant = constant;
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
	 * Gets the constant.
	 *
	 * @return the constant
	 */
	public String getConstant() {
		return constant;
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
				+ ((constant == null) ? 0 : constant.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		final ConstantLeaf other = (ConstantLeaf) obj;
		if (constant == null) {
			if (other.constant != null)
				return false;
		} else if (!constant.equals(other.constant))
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
		return "ConstantLeaf [id=" + id + ", constant=" + constant + "]";
	}

}
