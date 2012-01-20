package com.platzhaltr.flatlinr.api;

import java.util.List;

import com.platzhaltr.flatlinr.core.FlatNode;

public interface Node {

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public abstract String getName();

	/**
	 * Gets the child.
	 *
	 * @return the child
	 */
	public abstract Node getChild();

	/**
	 * Sets the child.
	 *
	 * @param child
	 *            the new child
	 * @return the node
	 */
	public abstract Node setChild(final FlatNode child);

	/**
	 * Gets the leafs.
	 *
	 * @return the leafs
	 */
	public abstract List<Leaf> getLeafs();

	/**
	 * Adds the leaf.
	 *
	 * @param leaf
	 *            the leaf
	 * @return the node
	 */
	public abstract Node addLeaf(final Leaf leaf);

}