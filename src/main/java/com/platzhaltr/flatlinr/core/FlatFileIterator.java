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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import com.platzhaltr.flatlinr.api.Feature;
import com.platzhaltr.flatlinr.api.Leaf;
import com.platzhaltr.flatlinr.api.Node;
import com.platzhaltr.flatlinr.api.Record;

/**
 * The Class FlatFileReader.
 * 
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class FlatFileIterator implements Iterator<Record> {

	/** The reader. */
	private final BufferedReader reader;

	/** The stack. */
	private final Stack<Node> stack = new Stack<Node>();

	/** The current line. */
	private String currentLine;

	/** The next line. */
	private String nextLine;

	/**
	 * Instantiates a new flat file reader.
	 * 
	 * @param flatNode
	 *            the node
	 * @param reader
	 *            the reader
	 */
	public FlatFileIterator (final Node flatNode, final Reader reader) {
		if (reader instanceof BufferedReader) {
			this.reader = (BufferedReader) reader;
		} else {
			this.reader = new BufferedReader(reader);
		}

		stack.add(flatNode);
	}

	/**
	 * Returns the next {@link Record}
	 * 
	 * @return the record
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public Record next() {
		final Node currentNode = stack.pop();
		if (currentLine == null) {
			try {
				currentLine = (nextLine != null) ? nextLine : reader.readLine();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		final Record record = new FlatRecord(currentNode.getName());

		for (final Leaf leaf : currentNode.getLeafs()) {
			if (leaf instanceof ConstantLeaf) {
				final ConstantLeaf constantLeaf = (ConstantLeaf) leaf;
				final String constant = constantLeaf.getConstant();
				if (currentLine.startsWith(constant)) {
					currentLine = currentLine.substring(constant.length());
				}
			} else if (leaf instanceof DelimitedLeaf) {
				final DelimitedLeaf delimitedLeaf = (DelimitedLeaf) leaf;
				final String delimiter = delimitedLeaf.getDelimiter();

				boolean last = false;
				int indexOf = currentLine.indexOf(delimiter);
				if (indexOf == -1) {
					indexOf = currentLine.length();
					last = true;
				}
				final String value = applyFeatures(currentLine.substring(0, indexOf), delimitedLeaf.getFeatures());
				record.put(leaf.getName(), value);

				// we reached the end of the line and leave the loop
				if (last) {
					break;
				}
				currentLine = currentLine.substring(indexOf
						+ delimiter.length());

			}
		}
		try {
			getNextLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		if (hasNext()) {
			stack.push(getNextNode(stack, currentNode, nextLine));
		}

		return record;
	}

	/**
	 * Checks if the reader can return another {@link Record} (another line)
	 * 
	 * @return true, if successful
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	public boolean hasNext()  {
		try {
			return nextLine != null || reader.ready();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Gets the next line.
	 * 
	 * @return the next line
	 */
	private String getNextLine() throws IOException {
		currentLine = null;
		nextLine = reader.readLine();
		return nextLine;
	}

	/**
	 * Gets the next node.
	 * 
	 * @param stack
	 *            the stack
	 * @param currentNode
	 *            the current node
	 * @param nextLine
	 *            the next line
	 * @return the next node
	 */
	private Node getNextNode(final Stack<Node> stack, final Node currentNode,
			final String nextLine) {

		// bias towards the child if current node has delimited leaf and the
		// first leaf of the child is constant
		if (!currentNode.getLeafs().isEmpty()) {
			final Leaf currentLeaf = currentNode.getLeafs().get(0);
			if (currentNode.getChild() != null
					&& !currentNode.getChild().getLeafs().isEmpty()) {
				final Leaf childLeaf = currentNode.getChild().getLeafs().get(0);
				if ((currentLeaf instanceof DelimitedLeaf)
						&& (childLeaf instanceof ConstantLeaf)) {
					if (isMatchingLeaf(childLeaf, currentNode.getChild()
							.getLeafs().size() == 1, nextLine)) {
						// remember ancestor
						stack.push(currentNode);
						return currentNode.getChild();
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

		// then towards the child
		if (currentNode.getChild() != null) {
			if (!currentNode.getChild().getLeafs().isEmpty()) {
				if (isMatchingLeaf(currentNode.getChild().getLeafs().get(0),
						currentNode.getChild().getLeafs().size() == 1, nextLine)) {
					// remember ancestor
					stack.push(currentNode);
					return currentNode.getChild();
				}
			}
		}

		// then towards the parent
		while (!stack.isEmpty() && stack.peek() != null) {
			final Node pop = stack.pop();
			if (!pop.getLeafs().isEmpty()) {
				if (isMatchingLeaf(pop.getLeafs().get(0),
						pop.getLeafs().size() == 1, nextLine)) {
					return pop;
				}
			}
		}

		throw new IllegalStateException("No viable node found");
	}

	/**
	 * Checks if the leaf matches the start of the line
	 * 
	 * @param leaf
	 *            the leaf
	 * @param line
	 *            the line
	 * @return true, if leaf is matching
	 */
	private boolean isMatchingLeaf(final Leaf leaf, final boolean onlyLeaf,
			final String line) {
		if (leaf instanceof ConstantLeaf) {
			final ConstantLeaf constantLeaf = (ConstantLeaf) leaf;
			return line.startsWith(constantLeaf.getConstant());
		} else if (leaf instanceof DelimitedLeaf) {
			final DelimitedLeaf delimitedLeaf = (DelimitedLeaf) leaf;
			return onlyLeaf
					|| (line.indexOf(delimitedLeaf.getDelimiter()) >= 0);
		}

		throw new IllegalStateException("No matching leaf found");
	}

	private String applyFeatures(String s, List<Feature> features) {

		for (Feature feature : features) {
			s = feature.convert(s);
		}

		return s;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
