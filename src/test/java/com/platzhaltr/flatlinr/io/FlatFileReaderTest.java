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
package com.platzhaltr.flatlinr.io;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.platzhaltr.flatlinr.api.Record;
import com.platzhaltr.flatlinr.core.ConstantLeaf;
import com.platzhaltr.flatlinr.core.DelimitedLeaf;
import com.platzhaltr.flatlinr.core.FlatNode;

/**
 * The Class FlatFileReaderTest.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class FlatFileReaderTest {

	/** The Constant PATH_SIMPLE. */
	private static final String PATH_SIMPLE = "/flatfile.simple.txt";

	/** The Constant PATH_MULTIPLE_GROUP. */
	private static final String PATH_MULTIPLE_GROUP = "/flatfile.multiple.group.txt";

	/** The Constant PATH_MULTIPLE_SUB. */
	private static final String PATH_MULTIPLE_SUB = "/flatfile.multiple.sub.txt";

	/** The Constant PATH_MULTIPLE_SUB_SUB. */
	private static final String PATH_MULTIPLE_SUB_SUB = "/flatfile.multiple.subsub.txt";

	/** The Constant PATH_DELIMITED_SUB. */
	private static final String PATH_DELIMITED_SUB = "/flatfile.multiple.delimited.sub.txt";

	/** The root. */
	private static FlatNode root;

	/** The flat file reader. */
	private FlatFileReader flatFileReader;

	/**
	 * Sets the up.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@BeforeClass
	public static void setUp() throws Exception {
		root = new FlatNode("group").addLeaf(new ConstantLeaf("#")).addLeaf(
				new DelimitedLeaf("name", ";"));
		final FlatNode sub = new FlatNode("sub").addLeaf(new ConstantLeaf("1"))
				.addLeaf(new DelimitedLeaf("content", ";"));
		final FlatNode subSub = new FlatNode("subsub").addLeaf(
				new ConstantLeaf("2")).addLeaf(
				new DelimitedLeaf("content", ";"));
		sub.setChild(subSub);
		root.setChild(sub);
	}

	/**
	 * Parses the.
	 *
	 * @param path
	 *            the path
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	private void parse(final String path) throws FileNotFoundException {
		final File input = new File(this.getClass().getResource(path).getFile());
		final FileReader inputReader = new FileReader(input);

		flatFileReader = new FlatFileReader(root, inputReader);
	}

	/**
	 * Test.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testSimple() throws IOException {
		parse(PATH_SIMPLE);
		final List<Record> records = new LinkedList<Record>();
		while (flatFileReader.hasNext()) {
			final Record record = flatFileReader.next();
			System.out.println(record.getName());
			if (record.get("culture") != null) {
				System.out.println(record.get("culture"));
			}
			records.add(record);
		}
		assertNotNull(records);
		assertTrue(records.size() == 3);
	}

	/**
	 * Test multiple group.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testMultipleGroup() throws IOException {
		parse(PATH_MULTIPLE_GROUP);
		final List<Record> records = new LinkedList<Record>();
		while (flatFileReader.hasNext()) {
			final Record record = flatFileReader.next();
			records.add(record);
		}
		assertNotNull(records);
		assertTrue(records.size() == 4);
	}

	/**
	 * Test multiple sub.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testMultipleSub() throws IOException {
		parse(PATH_MULTIPLE_SUB);
		final List<Record> records = new LinkedList<Record>();
		while (flatFileReader.hasNext()) {
			final Record record = flatFileReader.next();
			records.add(record);
		}
		assertNotNull(records);
		assertTrue(records.size() == 5);
	}

	/**
	 * Test multiple sub sub.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testMultipleSubSub() throws IOException {
		parse(PATH_MULTIPLE_SUB_SUB);
		final List<Record> records = new LinkedList<Record>();
		while (flatFileReader.hasNext()) {
			final Record record = flatFileReader.next();
			records.add(record);
		}
		assertNotNull(records);
		assertTrue(records.size() == 6);
	}

	/**
	 * Test delimited sub.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testDelimitedSub() throws IOException {
		root = new FlatNode("group").addLeaf(new ConstantLeaf("#")).addLeaf(
				new DelimitedLeaf("name", ";"));
		final FlatNode sub = new FlatNode("sub").addLeaf(
				new DelimitedLeaf("contentA", ";")).addLeaf(
				new DelimitedLeaf("contentB", ";"));
		final FlatNode subSub = new FlatNode("subsub").addLeaf(
				new ConstantLeaf("2")).addLeaf(
				new DelimitedLeaf("content", ";"));
		sub.setChild(subSub);
		root.setChild(sub);

		parse(PATH_DELIMITED_SUB);
		final List<Record> records = new LinkedList<Record>();
		while (flatFileReader.hasNext()) {
			final Record record = flatFileReader.next();
			records.add(record);
		}
		assertNotNull(records);
		assertTrue(records.size() == 7);
	}
}
