package com.platzhaltr.flatlinr.io;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.platzhaltr.flatlinr.core.Record;

public class SimpleHierarchyMulticharDelimiterTest extends
		SimpleHierarchyBaseTest {

	/** The Constant PATH */
	private static final String PATH = "/flatfile.complex.multichar-delimiter.txt";

	@Test
	public void test() throws IOException {
		parse(PATH, ROOT_NODE);
		while (iterator.hasNext()) {
			final Record record = iterator.next();

			if (record.getId().equals("category")) {

				final String categoryName = record.get("name");

				assertTrue(!categoryName.isEmpty());
			} else if (record.getId().equals("spell")) {
				final String name = record.get("name").trim();
				final String source = record.get("source").trim();
				final String type = record.get("type").trim();
				final String target = record.get("target").trim();
				final String duration = record.get("duration").trim();
				final String range = record.get("range").trim();
				final String drain = record.get("drain").trim();
				final String rawClass = record.get("class").trim();
				final String notes = record.get("notes").trim();

				assertTrue(!name.isEmpty());
				assertTrue(!source.isEmpty());
				assertTrue(!type.isEmpty());
				assertTrue(!target.isEmpty());
				assertTrue(!duration.isEmpty());
				assertTrue(!range.isEmpty());
				assertTrue(!drain.isEmpty());
				assertTrue(!rawClass.isEmpty());
				assertTrue(!notes.isEmpty());
			}
		}

	}

}
