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

/**
 * The Class AncestorTraverseStrategy.
 * 
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class AncestorTraverseStrategy extends BaseTraverseStrategy {

	/**
	 * Instantiates a new ancestor traverse strategy.
	 */
	protected AncestorTraverseStrategy() {
		// block from instantiating
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.platzhaltr.flatlinr.api.TraverseStrategy#getNextNode(com.platzhaltr
	 * .flatlinr.core.Node, java.lang.String)
	 */
	@Override
	public Node getNextNode(Node currentNode, String nextLine) {

		Node parent = currentNode.getParent();

		if (parent == null) {
			throw new IllegalStateException("No viable node found");
		}

		// traverse up the tree until node found
		Node nextNode = getNextNode(currentNode, parent, nextLine);

		if (nextNode == null) {
			return getNextNode(parent, nextLine);
		}

		return nextNode;

	}

	/**
	 * Gets the next node.
	 * 
	 * @param currentNode
	 *            the current node
	 * @param parent
	 *            the parent
	 * @param nextLine
	 *            the next line
	 * @return the next node
	 */
	private Node getNextNode(Node currentNode, Node parent, String nextLine) {

		// bias towards siblings
		if (parent.getChildren().size() > 1) {

			// bias towards next siblings
			// remember index of current node, we can use it to later iterate
			// over prior siblings
			int indexOfCurrentNode = -1;
			boolean foundCurrentNode = false;
			for (Node sibling : parent.getChildren()) {
				// important to check for found here, so that code block
				// doesn't execute if node is last in the list
				if (foundCurrentNode) {
					if (!sibling.getLeafs().isEmpty()) {
						if (isMatchingLeaf(sibling.getLeafs().get(0), sibling
								.getLeafs().size() == 1, nextLine)) {
							return sibling;
						}
					}
				}

				if (!sibling.equals(currentNode)) {
					indexOfCurrentNode = indexOfCurrentNode + 1;
				} else {
					foundCurrentNode = true;
				}

			}

			// TODO optimize this loop
			// then towards prior siblings
			for (int i = 0; i < indexOfCurrentNode; i++) {
				Node sibling = parent.getChildren().get(i);
				if (!sibling.getLeafs().isEmpty()) {
					if (isMatchingLeaf(sibling.getLeafs().get(0), sibling
							.getLeafs().size() == 1, nextLine)) {
						return sibling;
					}
				}
			}
		}

		// then toward the parent
		if (!parent.getLeafs().isEmpty()) {
			if (isMatchingLeaf(parent.getLeafs().get(0), parent.getLeafs()
					.size() == 1, nextLine)) {
				return parent;
			}
		}

		return null;
	}
}
