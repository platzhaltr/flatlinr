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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.platzhaltr.flatlinr.api.Leaf;

/**
 * A basic node
 * 
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class Node {

	/** The id. */
	private final String id;

	private Node parent = null;

	/** The children. */
	private final List<Node> children;

	/** The leafs. */
	private final List<Leaf> leafs;

	/**
	 * Instantiates a new flat node.
	 * 
	 * @param id
	 *            the id
	 */
	public Node(final String id) {
		this.id = id;
		children = new ArrayList<Node>();
		leafs = new LinkedList<Leaf>();
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
	 * Get the children.
	 * 
	 * @return the child
	 */
	public List<Node> getChildren() {
		return children;
	}

	/**
	 * Add a child node.
	 * 
	 * @param child
	 *            the child
	 * @return the node
	 */
	public Node addChild(final Node child) {
		this.children.add(child);
		child.setParent(this);
		return this;
	}

	/**
	 * Sets the parent.
	 *
	 * @param node the new parent
	 */
	private void setParent(Node node) {
		parent = node;
	}

	/**
	 * Gets the parent.
	 * 
	 * @return the parent
	 */
	public Node getParent() {
		return parent;
	}

	/**
	 * Gets the leafs.
	 * 
	 * @return the leafs
	 */
	public List<Leaf> getLeafs() {
		return leafs;
	}

	/**
	 * Adds the.
	 * 
	 * @param leaf
	 *            the leaf
	 * @return the node
	 */
	public Node add(final Leaf leaf) {
		leafs.add(leaf);
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((leafs == null) ? 0 : leafs.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Node other = (Node) obj;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (leafs == null) {
			if (other.leafs != null)
				return false;
		} else if (!leafs.equals(other.leafs))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", leafs=" + leafs + ", children="
				+ children + "]";
	}

}
