package com.platzhaltr.flatlinr.example;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.platzhaltr.flatlinr.api.Node;
import com.platzhaltr.flatlinr.api.Record;
import com.platzhaltr.flatlinr.core.ConstantLeaf;
import com.platzhaltr.flatlinr.core.DelimitedLeaf;
import com.platzhaltr.flatlinr.core.FlatNode;
import com.platzhaltr.flatlinr.io.FlatFileReader;
import com.platzhaltr.flatlinr.util.Features;

public class UsageExample {

	// define the structure of the file
	private final Node getRoot() {
		//@formatter:off
		final Node root = 
				new FlatNode("library")
				.add(new ConstantLeaf("#"))
				.add(new DelimitedLeaf("name", ";"));
			
			final FlatNode room = 
				new FlatNode("shelf")
				.add(new ConstantLeaf("- "))
				.add(new DelimitedLeaf("room", ";"));
			
			// you can also auto-convert delimited leafs via Features
			final FlatNode shelf = 
				new FlatNode("culture")
				.add(new ConstantLeaf("\t- "))
				.add(new DelimitedLeaf("shelf", ";",
					Features.LOWER_CASE));
			//@formatter:on
			
			room.setChild(shelf);
			root.setChild(room);

			return root;
	}

	public final void read(final File file) throws IOException {
		final FlatFileReader reader = 
				new FlatFileReader(
					getRoot(),
					new FileReader(file));

		while (reader.hasNext()) {
			final Record record = reader.next();
			System.out.println(record.getName());
			if (record.get("culture") != null) {
				System.out.println(record.get("culture"));
			}
		}
	}

}
