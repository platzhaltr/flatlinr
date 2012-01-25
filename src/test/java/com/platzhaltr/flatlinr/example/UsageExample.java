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

public class UsageExample {

	// First you would define the structure of the file
	private final Node getRoot() {
		final Node root = new FlatNode("library").add(new ConstantLeaf("#"))
				.add(new DelimitedLeaf("name", ";"));
		final FlatNode room = new FlatNode("shelf").add(new ConstantLeaf("- "))
				.add(new DelimitedLeaf("room", ";"));
		final FlatNode shelf = new FlatNode("culture").add(
				new ConstantLeaf("\t- ")).add(new DelimitedLeaf("shelf", ";"));

		room.setChild(shelf);
		root.setChild(room);

		return root;
	}

	// You read the file with
	public final void read(final File path) throws IOException {
		final FlatFileReader flatFileReader = new FlatFileReader(getRoot(),
				new FileReader(path));

		while (flatFileReader.hasNext()) {
			final Record record = flatFileReader.next();
			System.out.println(record.getName());
			if (record.get("culture") != null) {
				System.out.println(record.get("culture"));
			}
		}
	}

}
