package com.platzhaltr.flatlinr.api;

import java.util.List;

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
	public abstract Node setChild(final Node child);

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
	public abstract Node add(final Leaf leaf);

}