package com.platzhaltr.flatlinr.io;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.platzhaltr.flatlinr.core.Record;

public class ComplexHierarchySimpleTest extends ComplexHierarchyBaseTest {

	/** The Constant PATH_SIMPLE. */
	private static final String PATH = "/flatfile.simple.txt";

	
	/**
	 * Test.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void test() throws IOException {
		parse(PATH, ROOT);
		final List<Record> records = new LinkedList<Record>();
		while (iterator.hasNext()) {
			final Record record = iterator.next();
			System.out.println(record.getName());
			if (record.get("culture") != null) {
				System.out.println(record.get("culture"));
			}
			records.add(record);
		}
		assertNotNull(records);
		assertTrue(records.size() == 3);
	}
	
}
