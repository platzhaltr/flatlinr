package com.platzhaltr.flatlinr.io;

import com.platzhaltr.flatlinr.core.ConstantLeaf;
import com.platzhaltr.flatlinr.core.DelimitedLeaf;
import com.platzhaltr.flatlinr.core.Node;

/**
 * The Class ComplexHierarchy.
 */
public class ComplexHierarchyBaseTest extends BaseTest {

	/** The Constant GROUP. */
	//@formatter:off
	private static final Node GROUP = 
			new Node("group")
			.add(new ConstantLeaf("#"))
			.add(new DelimitedLeaf("name", ";"));
	//@formatter:on

	/** The Constant SUB. */
	//@formatter:off
	private static final Node SUB = 
			new Node("sub")
			.add(new ConstantLeaf("1"))
			.add(new DelimitedLeaf("content", ";"));
	//@formatter:on

	/** The Constant SUBSUB. */
	//@formatter:off
	private static final Node SUBSUB = 
			new Node("subsub")
			.add(new ConstantLeaf("2"))
			.add(new DelimitedLeaf("content", ";"));
	//@formatter:on

	{
		SUB.setChild(SUBSUB);
		GROUP.setChild(SUB);
	}

	/** The Constant ROOT. */
	protected static final Node ROOT = GROUP;

}
