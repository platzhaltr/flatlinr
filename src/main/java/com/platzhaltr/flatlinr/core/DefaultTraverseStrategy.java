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
import com.platzhaltr.flatlinr.api.TraverseStrategy;

/**
 * Determines the selection of the next {@link Node}. It applies the following
 * biases until a node has been found:
 * 
 * <ol>
 * <li>Bias towards the next child node, if it starts with a constant leaf and
 * the current node starts with a delimited leaf</li>
 * <li>Bias towards another instance of the current node</li>
 * <li>Bias towards the children</li>
 * <li>Bias towards the ancestors</li>
 * <ol>
 * <li>Bias towards next siblings</li>
 * <li>Bias towards the previuos siblings</li>
 * <li>Bias towards the parent</li>
 * </ol>
 * <li>Traverse uo the tree until a valid node has been found using step 4.</li>
 * </ol>
 * 
 * If no valid node has been found return <code>null</code>, effectively ending
 * the iteration over the flat file. If this happens changes to the node
 * hierarchy or even the file itself have to be made.
 * 
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class DefaultTraverseStrategy extends BaseTraverseStrategy {

	/** The ancestor traverse strategy. */
	private final TraverseStrategy ancestorTraverseStrategy = new AncestorTraverseStrategy();

	/**
	 * Instantiates a new default traverse strategy.
	 */
	protected DefaultTraverseStrategy() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.platzhaltr.flatlinr.core.TraverseStrategy#getNextNode(java.util.Stack
	 * , com.platzhaltr.flatlinr.core.Node, java.lang.String)
	 */
	@Override
	public Node getNextNode(final Node currentNode, final String nextLine) {

		// bias towards the child if it starts with a constant leaf and the
		// current node starts with a delimited leaf
		if (!currentNode.getLeafs().isEmpty()) {
			final Leaf firstLeafOfCurrentNode = currentNode.getLeafs().get(0);
			for (final Node childNode : currentNode.getChildren()) {
				if (!childNode.getLeafs().isEmpty()) {
					final Leaf firstLeafOfChildNode = childNode.getLeafs().get(
							0);
					if ((firstLeafOfCurrentNode instanceof DelimitedLeaf)
							&& (firstLeafOfChildNode instanceof ConstantLeaf)) {
						if (isMatchingLeaf(firstLeafOfChildNode, childNode
								.getLeafs().size() == 1, nextLine)) {
							return childNode;
						}
					}
				}
			}
		}

		// bias towards the current node
		if (!currentNode.getLeafs().isEmpty()) {
			if (isMatchingLeaf(currentNode.getLeafs().get(0), currentNode
					.getLeafs().size() == 1, nextLine)) {
				return currentNode;
			}
		}

		// then towards the children
		for (final Node childNode : currentNode.getChildren()) {
			if (!childNode.getLeafs().isEmpty()) {
				if (isMatchingLeaf(childNode.getLeafs().get(0), childNode
						.getLeafs().size() == 1, nextLine)) {
					return childNode;
				}
			}
		}

		// default to ancestor traverse strategy
		return ancestorTraverseStrategy.getNextNode(currentNode, nextLine);
	}
}
