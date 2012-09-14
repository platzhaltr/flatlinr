package com.platzhaltr.flatlinr.example;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.platzhaltr.flatlinr.core.ConstantLeaf;
import com.platzhaltr.flatlinr.core.DelimitedLeaf;
import com.platzhaltr.flatlinr.core.FlatFileIterator;
import com.platzhaltr.flatlinr.core.LineLeaf;
import com.platzhaltr.flatlinr.core.Node;
import com.platzhaltr.flatlinr.core.Record;
import com.platzhaltr.flatlinr.io.BaseTest;
import com.platzhaltr.flatlinr.util.Features;

/**
 * Flatlinr interprets files as tree hierarchical structures.
 * 
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 * 
 */
public class UsageExample extends BaseTest {

	/** The Constant PATH. */
	private static final String PATH = "/flatfile.example.txt";

	@Test
	public void test() throws IOException {

		parse(PATH, getRoot());
		final List<Record> records = new LinkedList<Record>();
		while (iterator.hasNext()) {
			final Record record = iterator.next();
			records.add(record);
			System.out.println(record);
		}
		assertNotNull(records);
		assertTrue(records.size() == 7);
	}

	// define the structure of the file
	private final Node getRoot() {
		//@formatter:off
		final Node root = 
				new Node("library")
				.add(new ConstantLeaf("#"))
				.add(new LineLeaf("name"));
			
		final Node room = 
			new Node("room")
			.add(new ConstantLeaf("\t- "))
			.add(new DelimitedLeaf("name", ';'));
		
		// you can also auto-convert delimited leafs via Features
		final Node shelf = 
			new Node("shelf")
			.add(new ConstantLeaf("\t\t- "))
			.add(new DelimitedLeaf("name", ';',
				Features.TRIM));
		//@formatter:on

		room.addChild(shelf);
		root.addChild(room);

		return root;
	}

	public final void read(final File file) throws IOException {
		final FlatFileIterator iterator = new FlatFileIterator(getRoot(),
				new FileReader(file));

		while (iterator.hasNext()) {
			final Record record = iterator.next();
			if (record.getId().equals("library")) {
				System.out.println(record.get("name"));
			} else if (record.getId().equals("room")) {
				System.out.println(record.get("name"));
			} else if (record.getId().equals("shelf")) {
				System.out.println(record.get("name"));
			}
		}
	}
}
