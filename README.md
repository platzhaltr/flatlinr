# README #

Parse flat files describing a hierarchical data structure

Requires JDK 1.6 or higher.

## Usage ##

Flatlinr sees the file as a tree. Let's take this simple example file

	#Library
	- History
		- Greek
		- Roman

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
	
**Flatlinr** will take care of traversing the tree for you. You can react on the changes by using the `record.getName()` method to know where you are in the tree and build your data accordingly. 

I also created [Readr](https://github.com/platzhaltr/readr) as a companion project. It helps you clean up the files (by removing or tranforming certain lines) before feeding it to Flatlinr.