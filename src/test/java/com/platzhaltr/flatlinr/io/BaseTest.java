package com.platzhaltr.flatlinr.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.platzhaltr.flatlinr.api.Node;

public abstract class BaseTest {

	/** The flat file reader. */
	protected FlatFileReader reader;

	/**
	 * Parses the.
	 * 
	 * @param path
	 *            the path
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	protected void parse(final String path, Node root)
			throws FileNotFoundException {
		final File input = new File(this.getClass().getResource(path).getFile());
		final FileReader inputReader = new FileReader(input);

		reader = new FlatFileReader(root, inputReader);
	}

}
