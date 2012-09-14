package com.platzhaltr.flatlinr.io;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.platzhaltr.flatlinr.core.ConstantLeaf;
import com.platzhaltr.flatlinr.core.DelimitedLeaf;
import com.platzhaltr.flatlinr.core.Node;
import com.platzhaltr.flatlinr.core.Record;
import com.platzhaltr.flatlinr.util.Features;

public class MultipleChildrenTest extends BaseTest {

	/** The Constant CATEGORY. */
	// @formatter:off
	private static final Node CATEGORY = 
		new Node("category")
		.add(new ConstantLeaf("1-"))
		.add(new DelimitedLeaf("name", ';', 
				Features.TRIM));
	// @formatter:on

	/** The Constant VARIANT. */
	// @formatter:off
	private static final Node VARIANT = 
		new Node("variant")
		.add(new ConstantLeaf("2-"))
		.add(new DelimitedLeaf("name", ';', 
				Features.TRIM));
	// @formatter:on

	/** The Constant SKILL. */
	// @formatter:off
	private static final Node BIOWARE = 
		new Node("bioware")
		.add(new ConstantLeaf("2-* "))
		.add(new DelimitedLeaf("name", "1|", Features.TRIM))
		.add(new DelimitedLeaf("source", '|', Features.TRIM))
		.add(new DelimitedLeaf("availability", "|", Features.TRIM, Features.LOWER_CASE))
		.add(new DelimitedLeaf("bioindex", "|", Features.TRIM))
		.add(new DelimitedLeaf("cost", "|", Features.TRIM, Features.LOWER_CASE))
		.add(new DelimitedLeaf("mods", "|", 
				Features.TRIM,
				Features.LOWER_CASE))
		.add(new DelimitedLeaf("type", "|",
				Features.TRIM,
				Features.LOWER_CASE))
		.add(new DelimitedLeaf("street-index", "|", Features.TRIM))
		.add(new DelimitedLeaf("notes", "|", Features.TRIM));
	// @formatter:on

	/** The Constant SKILL. */
	// @formatter:off
	private static final Node BIOWARE_VARIANT = 
		new Node("bioware-variant")
		.add(new ConstantLeaf("3-* "))
		.add(new DelimitedLeaf("name", "1|", Features.TRIM))
		.add(new DelimitedLeaf("source", '|', Features.TRIM))
		.add(new DelimitedLeaf("availability", "|", Features.TRIM, Features.LOWER_CASE))
		.add(new DelimitedLeaf("bioindex", "|", Features.TRIM))
		.add(new DelimitedLeaf("cost", "|", Features.TRIM, Features.LOWER_CASE))
		.add(new DelimitedLeaf("mods", "|", 
				Features.TRIM,
				Features.LOWER_CASE))
		.add(new DelimitedLeaf("type", "|",
				Features.TRIM,
				Features.LOWER_CASE))
		.add(new DelimitedLeaf("street-index", "|", Features.TRIM))
		.add(new DelimitedLeaf("notes", "|", Features.TRIM));
	// @formatter:on

	/** The Constant ROOT. */
	private static final Node ROOT = CATEGORY.addChild(BIOWARE).addChild(
			VARIANT.addChild(BIOWARE_VARIANT));

	/** The Constant PATH. */
	private static final String PATH = "/flatfile.complex.multiple-childs.txt";

	@Test
	public void test() throws IOException {

		parse(PATH, ROOT);
		final List<Record> records = new LinkedList<Record>();
		while (iterator.hasNext()) {
			final Record record = iterator.next();
			records.add(record);
			System.out.println(record);
		}
		assertNotNull(records);
		assertTrue(records.size() == 6);
	}

}
