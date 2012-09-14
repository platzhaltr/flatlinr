package com.platzhaltr.flatlinr.io;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.platzhaltr.flatlinr.core.Record;

public class ComplexHierarchyMultipleGroupTest extends ComplexHierarchyBaseTest {

	/** The Constant PATH_MULTIPLE_GROUP. */
	private static final String PATH = "/flatfile.multiple.group.txt";

	/**
	 * Test multiple group.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testMultipleGroup() throws IOException {
		parse(PATH, ROOT);
		final List<Record> records = new LinkedList<Record>();
		while (iterator.hasNext()) {
			final Record record = iterator.next();
			records.add(record);
		}
		assertNotNull(records);
		assertTrue(records.size() == 4);
	}

}
