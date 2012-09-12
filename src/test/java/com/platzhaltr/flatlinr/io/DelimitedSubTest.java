package com.platzhaltr.flatlinr.io;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.platzhaltr.flatlinr.api.Node;
import com.platzhaltr.flatlinr.api.Record;
import com.platzhaltr.flatlinr.core.ConstantLeaf;
import com.platzhaltr.flatlinr.core.DelimitedLeaf;
import com.platzhaltr.flatlinr.core.FlatNode;

public class DelimitedSubTest extends BaseTest {

	/** The Constant GROUP. */
	//@formatter:off
	private static final Node GROUP = 
			new FlatNode("group")
			.add(new ConstantLeaf("#"))
			.add(new DelimitedLeaf("name", ";"));
	//@formatter:on

	/** The Constant SUB. */
	//@formatter:off
	private static final Node SUB = 
			new FlatNode("sub")
			.add(new DelimitedLeaf("contentA", ";"))
			.add(new DelimitedLeaf("contentB", ";"));
	//@formatter:on

	/** The Constant SUBSUB. */
	//@formatter:off
	private static final Node SUBSUB = 
			new FlatNode("subsub")
			.add(new ConstantLeaf("2"))
			.add(new DelimitedLeaf("content", ";"));
	//@formatter:on

	{
		SUB.setChild(SUBSUB);
		GROUP.setChild(SUB);
	}

	/** The Constant ROOT. */
	private static final Node ROOT = GROUP;

	/** The Constant PATH_DELIMITED_SUB. */
	private static final String PATH = "/flatfile.multiple.delimited.sub.txt";

	@Test
	public void testDelimitedSub() throws IOException {

		parse(PATH, ROOT);
		final List<Record> records = new LinkedList<Record>();
		while (iterator.hasNext()) {
			final Record record = iterator.next();
			records.add(record);
		}
		assertNotNull(records);
		assertTrue(records.size() == 7);
	}

}
