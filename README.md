# README #

Parse flat files that describe a hierarchical data structure

	git clone git://github.com/platzhaltr/flatlinr.git
	cd flatlinr
	mvn clean install

## Usage ##

Assume the following example file

	#Library
	- History
		- greek
		- ROMAN

Flatlinr sees the file as a tree.

```java
public class UsageExample {

	// define the structure of the file
	private final Node getRoot() {
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
```
	
**Flatlinr** will take care of traversing the tree for you. You can react on the changes by using the `record.getName()` method to know where you are in the tree and build your data accordingly. 

I also created [Readr](https://github.com/platzhaltr/readr) as a companion project. It creates `java.io.Reader` classes that help you clean up the files by removing or tranforming certain lines.