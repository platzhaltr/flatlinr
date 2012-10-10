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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import com.platzhaltr.flatlinr.api.Feature;
import com.platzhaltr.flatlinr.api.Leaf;
import com.platzhaltr.flatlinr.api.TraverseStrategy;

/**
 * Consume a hierarchical flat file.The hierarchy of the flat file can be
 * descibed using {@link Node}, attaching {@link Leaf} and other child nodes to
 * them, viewing the flat file as a graph.
 * 
 * The iterator uses the graph structure to decide how toi break down each line
 * into important {@link Record}s. In order to decise what nodes or leafs take
 * precedence a {@link TraverseStrategy} can be supplied. The default strategy
 * uses various biases to determine the next node is defined in the
 * {@link DefaultTraverseStrategy}.
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

	/** The traverse strategy. */
	private final TraverseStrategy traverseStrategy;

	/**
	 * Instantiates a new flat file iterator.
	 * 
	 * @param rootNode
	 *            the root node
	 * @param file
	 *            the file
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	public FlatFileIterator(final Node rootNode, final File file)
			throws FileNotFoundException {
		this(rootNode, new FileReader(file));
	}

	/**
	 * Instantiates a new flat file iterator.
	 * 
	 * @param rootNode
	 *            the root node
	 * @param file
	 *            the file
	 * @param traverseStrategy
	 *            the traverse strategy
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	public FlatFileIterator(final Node rootNode, final File file,
			final TraverseStrategy traverseStrategy)
					throws FileNotFoundException {
		this(rootNode, new FileReader(file), traverseStrategy);
	}

	/**
	 * Instantiates a new flat file iterator.
	 * 
	 * @param rootNode
	 *            the flat node
	 * @param reader
	 *            the reader
	 */
	public FlatFileIterator(final Node rootNode, final Reader reader) {
		this(rootNode, reader, new DefaultTraverseStrategy());
	}

	/**
	 * Instantiates a new flat file reader.
	 * 
	 * @param rootNode
	 *            the node
	 * @param reader
	 *            the reader
	 * @param traverseStrategy
	 *            the traverse strategy
	 */
	public FlatFileIterator(final Node rootNode, final Reader reader,
			final TraverseStrategy traverseStrategy) {
		this.traverseStrategy = traverseStrategy;
		if (reader instanceof BufferedReader) {
			this.reader = (BufferedReader) reader;
		} else {
			this.reader = new BufferedReader(reader);
		}

		stack.add(rootNode);
	}

	/**
	 * Returns the next {@link Record}.
	 * 
	 * @return the record
	 */
	@Override
	public Record next() {
		final Node currentNode = stack.pop();
		if (currentLine == null) {
			try {
				currentLine = (nextLine != null) ? nextLine : reader.readLine();
			} catch (final IOException e) {
				throw new RuntimeException(e);
			}
		}

		final Record record = new Record(currentNode.getId());

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
				final String value = apply(currentLine.substring(0, indexOf),
						delimitedLeaf.getFeatures());
				record.put(leaf.getId(), value);

				// we reached the end of the line and leave the loop
				if (last) {
					break;
				}
				currentLine = currentLine.substring(indexOf
						+ delimiter.length());

			} else if (leaf instanceof LineLeaf) {
				final LineLeaf lineLeaf = (LineLeaf) leaf;

				final String value = apply(currentLine, lineLeaf.getFeatures());
				record.put(leaf.getId(), value);

				// we reached the end of the line and leave the loop
				break;
			}

		}
		try {
			getNextLine();
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
		if (hasNext()) {
			stack.push(traverseStrategy.getNextNode(currentNode, nextLine));
		}

		return record;
	}

	/**
	 * Checks if the reader can return another {@link Record} (another line).
	 * 
	 * @return true, if successful
	 */
	@Override
	public boolean hasNext() {
		try {
			return nextLine != null || reader.ready();
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Gets the next line.
	 * 
	 * @return the next line
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private String getNextLine() throws IOException {
		currentLine = null;
		nextLine = reader.readLine();
		return nextLine;
	}

	/**
	 * Apply features.
	 * 
	 * @param s
	 *            the s
	 * @param features
	 *            the features
	 * @return the string
	 */
	private String apply(String s, final List<Feature> features) {

		for (final Feature feature : features) {
			s = feature.convert(s);
		}

		return s;
	}

	/**
	 * Operation not supported.
	 * 
	 * @throws UnsupportedOperationException
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
