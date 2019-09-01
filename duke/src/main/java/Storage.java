import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;

import java.util.Stream;
import java.util.ArrayList;


class Storage {
	protected String filename;

	public Storage(String filename) {
		this.filename = filename;
	}

	public Stream<String> load() throws DukeException {
		try {
			File f = new File(filename);
			Scanner s = new Scanner(f);
			Stream<String> stringStream = Stream.generate(() -> s.nextLine());
			s.close();
			return stringStream;
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
	}

	/**
	 * Writes updated todo list to file.
	 */

	public void update(ArrayList<Task> list, String filename) throws IOException {
		// writes into file
		FileWriter fw = new FileWriter(filename, false); // rewrites the entire doc
		for (Task task: list) {
			String type = task.getType();
			int binary = task.isDone ? 1 : 0;
			String toWrite = type + " " + binary + " " + task.getDescription();
			fw.write(toWrite + System.lineSeparator());
		}
		fw.close();
	}
}