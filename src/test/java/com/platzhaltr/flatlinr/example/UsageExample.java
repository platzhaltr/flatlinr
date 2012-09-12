package com.platzhaltr.flatlinr.example;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.platzhaltr.flatlinr.core.ConstantLeaf;
import com.platzhaltr.flatlinr.core.DelimitedLeaf;
import com.platzhaltr.flatlinr.core.FlatFileIterator;
import com.platzhaltr.flatlinr.core.Node;
import com.platzhaltr.flatlinr.core.Record;
import com.platzhaltr.flatlinr.util.Features;

public class UsageExample {

	// define the structure of the file
	private final Node getRoot() {
		//@formatter:off
		final Node root = 
				new Node("library")
				.add(new ConstantLeaf("#"))
				.add(new DelimitedLeaf("name", ";"));
			
			final Node room = 
				new Node("shelf")
				.add(new ConstantLeaf("- "))
				.add(new DelimitedLeaf("room", ";"));
			
			// you can also auto-convert delimited leafs via Features
			final Node shelf = 
				new Node("culture")
				.add(new ConstantLeaf("\t- "))
				.add(new DelimitedLeaf("shelf", ";",
					Features.LOWER_CASE));
			//@formatter:on
			
			room.setChild(shelf);
			root.setChild(room);

			return root;
	}

	public final void read(final File file) throws IOException {
		final FlatFileIterator iterator = 
				new FlatFileIterator(
					getRoot(),
					new FileReader(file));

		while (iterator.hasNext()) {
			final Record record = iterator.next();
			System.out.println(record.getName());
			if (record.get("culture") != null) {
				System.out.println(record.get("culture"));
			}
		}
	}

}
