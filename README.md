# README #

Parse flat files describing a hierarchical data structure

Requires JDK 1.6 or higher.

## Usage ##

Flatlinr sees the file as a tree. Let's take this simple example

	#Library
	- History
		- Greek
		- Roman

First you would define the structure of the file

	Node root = new FlatNode("library").addLeaf(new ConstantLeaf("#")).addLeaf(
		new DelimitedLeaf("name", ";"));
	final FlatNode room = new FlatNode("shelf").addLeaf(new ConstantLeaf("- "))
			.addLeaf(new DelimitedLeaf("room", ";"));
	final FlatNode shelf = new FlatNode("culture").addLeaf(
			new ConstantLeaf("\t- ")).addLeaf(
			new DelimitedLeaf("shelf", ";"));
	
	room.setChild(shelf);
	root.setChild(room);
	
Each node can occur multiple times. You read the file with

	FlatFileReader flatFileReader = new FlatFileReader(root, new FileReader(new File(path)));
	
	while (flatFileReader.hasNext()) {
		final Record record = flatFileReader.next();
		System.out.println(record.getName());
		if (record.getPartial("culture") != null) {
			System.out.println(record.getPartial("culture"));
		}
	}
	
**Flatlinr** will take care of traversing the tree for you. You can react on the changes by using the `record.getName()` method to know where you are in the tree and build your data accordingly.

Sometimes you will find that will contain lines that don't fit into your hierarachy. For this I created a `util` package that contain convenience classes to create `java.io.FilterReader` that can filter and manipulate the original data before it is then read by **Flatlinr**  