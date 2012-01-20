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

import java.util.LinkedList;
import java.util.List;

import com.platzhaltr.flatlinr.api.Leaf;
import com.platzhaltr.flatlinr.api.Node;

/**
 * A basic {@link Node} implementation
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class FlatNode implements Node {

	/** The child. */
	private Node child;

	/** The name. */
	private final String name;

	/** The leafs. */
	private final List<Leaf> leafs;

	/**
	 * Instantiates a new flat node.
	 *
	 * @param name
	 *            the name
	 */
	public FlatNode(final String name) {
		this.name = name;
		leafs = new LinkedList<Leaf>();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.platzhaltr.flatlinr.api.Node#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.platzhaltr.flatlinr.api.Node#getChild()
	 */
	@Override
	public Node getChild() {
		return child;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.platzhaltr.flatlinr.api.Node#setChild(com.platzhaltr.flatlinr.api
	 * .FlatNode)
	 */
	@Override
	public Node setChild(final Node child) {
		this.child = child;
		return this;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.platzhaltr.flatlinr.api.Node#getLeafs()
	 */
	@Override
	public List<Leaf> getLeafs() {
		return leafs;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.platzhaltr.flatlinr.api.Node#addLeaf(com.platzhaltr.flatlinr.api.
	 * Leaf)
	 */
	@Override
	public FlatNode add(final Leaf leaf) {
		leafs.add(leaf);
		return this;
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
		result = prime * result + ((child == null) ? 0 : child.hashCode());
		result = prime * result + ((leafs == null) ? 0 : leafs.hashCode());
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
		final FlatNode other = (FlatNode) obj;
		if (child == null) {
			if (other.child != null)
				return false;
		} else if (!child.equals(other.child))
			return false;
		if (leafs == null) {
			if (other.leafs != null)
				return false;
		} else if (!leafs.equals(other.leafs))
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
		return "FlatNode [" + (name != null ? "name=" + name + ", " : "")
				+ (child != null ? "child=" + child + ", " : "") + "leafs="
				+ leafs + "]";
	}

}
