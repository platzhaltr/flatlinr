# README #

**Flatlinr** supports parse simple flat file structures like CSV files and also really complex hierarchical data structures based on flat files.

	git clone git://github.com/platzhaltr/flatlinr.git
	cd flatlinr
	mvn clean install

## Usage ##

Assume the following example file

	#Library
		- History
			- Greek
			- Asia
		- Language
			- Spanish
			- English
			
Now we want to break this file into the following structure

	Library
		- Room
			- Shelf	

in which a file can multiple rooms, and each room can have multiple shelfs. That means that each node represents a type of records.

But as you can see some shelves are properly indentend but others are shelves are delimited using a semicolon. **Flatlinr** to the rescue. It helps you keeping sane. Once you have defined the structure of the file, **FlatlinrIterator** will take care of traversing the tree for you. All you have to do is to use the iterator interface and react on the various record identifiers.

```java
/**
 * Flatlinr interprets files as tree hierarchical structures.
 */
public class UsageExample {
	
	// define the structure of the file
	private final Node getRoot() {
		//@formatter:off
		final Node root = 
				new Node("library")
				.add(new ConstantLeaf("#"))
				.add(new DelimitedLeaf("name"));
			
			final Node room = 
				new Node("room")
				.add(new ConstantLeaf("\t- "))
				.add(new DelimitedLeaf("name"));
			
			// you can also auto-convert delimited leafs via Features
			final Node shelf = 
				new Node("shelf")
				.add(new ConstantLeaf("\t\t- "))
				.add(new DelimitedLeaf("name", ";",
					Features.LOWER_CASE));
			//@formatter:on

		room.addChild(shelf);
		root.addChild(room);

		return root;
	}

	public final void read(final File file) throws IOException {
		final FlatFileIterator iterator = new FlatFileIterator(getRoot(),
				new FileReader(file));

		while (iterator.hasNext()) {
			final Record record = iterator.next();
			if (record.getId().equals("library")) {
				System.out.println(record.get("name"));
			} else if (record.getId().equals("room")) {
				System.out.println(record.get("name"));
			} else if (record.getId().equals("shelf")) {
				System.out.println(record.get("name"));
			}
		}
	}
}
```

## Traversal ##

**flatlinr** supports more complex structures

- different delimiter per node
- multiple children per node

The drawback is that the decision which node should be used for parsing the next line is hard. The decision which `Node` is used can be defined in a `TraversalStrategy`. `DefaultTraversalStrategy` uses these in order until it gives up. Each bias is based ont the fact if the the next line starts with the first leaf of the current node.

1. Bias towards the child if it starts with a constant leaf and the current node starts with a delimited leaf.
2. Bias towards the current node itself. This allows for multiples instances.
3. Bias towards the children
4. Bias towards the next siblings
5. Bias towards the prior siblings
6. Bias towards the parent
7. If direct parent doesn't match, recursively traverse the tree up with the parent as the currentNode and start again with rule 4.

If all fails `IllegalStateException` is thrown.

## Notes ##

I also created [Readr](https://github.com/platzhaltr/readr) as a companion project. It creates `java.io.Reader` classes that help you clean up the files by removing or tranforming certain lines.