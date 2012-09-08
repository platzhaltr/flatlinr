package com.platzhaltr.flatlinr.io;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.platzhaltr.flatlinr.api.Node;
import com.platzhaltr.flatlinr.api.Record;
import com.platzhaltr.flatlinr.core.ConstantLeaf;
import com.platzhaltr.flatlinr.core.DelimitedLeaf;
import com.platzhaltr.flatlinr.core.FlatNode;

public class MulticharDelimiterTest {

	/** The Constant PATH */
	private static final String PATH = "/flatfile.complex.multichar-delimiter.txt";

	/** The Constant CATEGORY. */
	// @formatter:off
	private static final Node CATEGORY = 
			new FlatNode("category")
			.add(new ConstantLeaf("1-"))
			.add(new DelimitedLeaf("name"));
	// @formatter:on

	/** The Constant SKILL. */
	// @formatter:off
	private static final Node SPELL = new FlatNode("spell")
			.add(new ConstantLeaf("2-* "))
			.add(new DelimitedLeaf("name", "1|"))
			.add(new DelimitedLeaf("source", '|'))
			.add(new DelimitedLeaf("type", "|"))
			.add(new DelimitedLeaf("target", "|"))
			.add(new DelimitedLeaf("duration", "|"))
			.add(new DelimitedLeaf("range", "|"))
			.add(new DelimitedLeaf("drain", "|"))
			.add(new DelimitedLeaf("class", "|"))
			.add(new DelimitedLeaf("notes", "|"))
			;
	// @formatter:on
	
	/** The Constant ROOT. */
	private static final Node ROOT_NODE = ((FlatNode) CATEGORY).setChild(SPELL);
	
	/** The flat file reader. */
	private FlatFileReader reader;

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

		reader = new FlatFileReader(ROOT_NODE, inputReader);
	}
	
	@Test
	public void test() throws IOException {
		parse(PATH);
		while (reader.hasNext()) {
			final Record record = reader.next();

			if (record.getName().equals("category")) {

				final String categoryName = record.get("name");

				assertTrue(!categoryName.isEmpty());
			} else if (record.getName().equals("spell")) {
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
