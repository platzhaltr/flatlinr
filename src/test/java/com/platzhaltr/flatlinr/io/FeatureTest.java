package com.platzhaltr.flatlinr.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.platzhaltr.flatlinr.core.ConstantLeaf;
import com.platzhaltr.flatlinr.core.DelimitedLeaf;
import com.platzhaltr.flatlinr.core.Node;
import com.platzhaltr.flatlinr.core.Record;
import com.platzhaltr.flatlinr.util.Features;

public class FeatureTest extends SimpleHierarchyBaseTest {

	/** The Constant CATEGORY. */
	// @formatter:off
	private static final Node CATEGORY =
			new Node("category")
	.add(new ConstantLeaf("1-"))
	.add(new DelimitedLeaf("name", Features.TRIM, Features.REPLACE(" Spells", ""), Features.LOWER_CASE));
	// @formatter:on

	/** The Constant SKILL. */
	// @formatter:off
	private static final Node SPELL = new Node("spell")
	.add(new ConstantLeaf("2-* "))
	.add(new DelimitedLeaf("name", "1|", Features.TRIM))
	.add(new DelimitedLeaf("source", '|', Features.TRIM))
	.add(new DelimitedLeaf("type", "|", Features.TRIM))
	.add(new DelimitedLeaf("target", "|", Features.TRIM))
	.add(new DelimitedLeaf("duration", "|", Features.TRIM))
	.add(new DelimitedLeaf("range", "|", Features.TRIM, Features.LOWER_CASE))
	.add(new DelimitedLeaf("drain", "|", Features.TRIM))
	.add(new DelimitedLeaf("class", "|", Features.TRIM))
	.add(new DelimitedLeaf("notes", "|", Features.TRIM))
	;
	// @formatter:on

	/** The Constant ROOT. */
	protected static final Node ROOT_NODE = CATEGORY
			.addChild(SPELL);

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
				assertEquals("combat", categoryName);

			} else if (record.getId().equals("spell")) {
				final String name = record.get("name");
				final String source = record.get("source");
				final String type = record.get("type");
				final String target = record.get("target");
				final String duration = record.get("duration");
				final String range = record.get("range");
				final String drain = record.get("drain");
				final String rawClass = record.get("class");
				final String notes = record.get("notes");

				assertFalse(endsOrBeginsWithWhitespace(name));
				assertFalse(endsOrBeginsWithWhitespace(source));
				assertFalse(endsOrBeginsWithWhitespace(type));
				assertFalse(endsOrBeginsWithWhitespace(target));
				assertFalse(endsOrBeginsWithWhitespace(duration));
				assertFalse(endsOrBeginsWithWhitespace(range));
				assertTrue(isAllLower(range));
				assertFalse(endsOrBeginsWithWhitespace(drain));
				assertFalse(endsOrBeginsWithWhitespace(rawClass));
				assertFalse(endsOrBeginsWithWhitespace(notes));
			}
		}
	}

	private boolean endsOrBeginsWithWhitespace(final String s) {
		if (Character.isWhitespace(s.charAt(0))) {
			return true;
		}
		if (Character.isWhitespace(s.charAt(s.length() - 1))) {
			return true;
		}
		return false;
	}

	private boolean isAllLower(final String s) {
		for (final char c : s.toCharArray()) {
			if (Character.isLetter(c) && Character.isUpperCase(c)) {
				return false;
			}
		}
		return true;
	}
}
