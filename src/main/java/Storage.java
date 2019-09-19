import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;


class Storage {
	protected String filename;

	public Storage(String filename) {
		this.filename = filename;
	}

	/**
	 * Reads file to import existing tasks, adding to TaskList using addCommands
	 * @return Arraylist of addCommands, loaded from data/duke.txt
	 * @throws FileNotFoundException if file is not found
	 */

	public ArrayList<AddCommand> load() throws IOException {
		ArrayList<AddCommand> commands = new ArrayList<>();
		File f = new File(filename);
		if (!f.exists()) {
			f.getParentFile().mkdirs();
		}
		if (!f.exists() && !f.createNewFile()) {
			throw new IOException("Error creating file");
		}
		Scanner s = new Scanner(f);
		
		while (s.hasNext()) {
			AddCommand c = (AddCommand) Parser.parse(s.next(), s.nextLine().trim());
			commands.add(c);
		}
		s.close();

		return commands;
	}

	/**
	 * Writes updated todo list to file.
	 */

	public void update(TaskList tasks, String filename) throws IOException {
		FileWriter fw = new FileWriter(filename, false); // rewrites the entire doc
		for (Task task: tasks.getList()) {
			String type = task.getType();
			int binary = task.isDone ? 1 : 0;
			String toWrite = type + " " + binary + " " + task.getDescription();
			fw.write(toWrite + System.lineSeparator());
		}
		fw.close();
	}
}