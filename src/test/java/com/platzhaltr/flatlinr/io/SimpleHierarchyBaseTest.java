package com.platzhaltr.flatlinr.io;

import com.platzhaltr.flatlinr.core.ConstantLeaf;
import com.platzhaltr.flatlinr.core.DelimitedLeaf;
import com.platzhaltr.flatlinr.core.Node;

public abstract class SimpleHierarchyBaseTest extends BaseTest {

	/** The Constant CATEGORY. */
	// @formatter:off
	private static final Node CATEGORY = 
			new Node("category")
			.add(new ConstantLeaf("1-"))
			.add(new DelimitedLeaf("name"));
	// @formatter:on

	/** The Constant SKILL. */
	// @formatter:off
	private static final Node SPELL = new Node("spell")
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
	protected static final Node ROOT_NODE = ((Node) CATEGORY)
			.setChild(SPELL);

}
