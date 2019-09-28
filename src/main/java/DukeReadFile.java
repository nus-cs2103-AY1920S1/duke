import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Loads the tasks that were saved in a text document
 * when the program starts up.
 */
public class DukeReadFile {

	private static String filePath;
	private static String currentTask = "";
	private static ArrayList<Task> currentTaskList = new ArrayList<>();

	public DukeReadFile(String filePath) {

		this.filePath = filePath;
	}

	/**
	 * Creates a file object to read the data saved in a text
	 * document and loading the tasks to be included in the task
	 * list when the program starts up.
	 *
	 * @throws FileNotFoundException
	 */
	public static void readFileContent() throws FileNotFoundException {
		File savedTask = new File(filePath);
		Scanner scanner = new Scanner(savedTask);
		while (scanner.hasNext()) {
			currentTask += scanner.nextLine() + "\n";
		}

		String[] addTask = currentTask.split("\n");

		for (int i = 0; i < addTask.length; i++) {
			if (addTask[i].startsWith("T")) {
				String[] at = addTask[i].split("~");
				Todo td = new Todo(at[2]);
				if (at[1].equals("1")) {
					td.markAsDone();
				}
				currentTaskList.add(td);

			} else if (addTask[i].startsWith("D")) {
				String[] at = addTask[i].split("~");
				Deadline dl = new Deadline(at[2], at[3]);
				if (at[1].equals("1")) {
					dl.markAsDone();
				}
				currentTaskList.add(dl);

			} else if (addTask[i].startsWith("E")) {
				String[] at = addTask[i].split("~");
				Event e = new Event(at[2], at[3]);
				if (at[1].equals("1")) {
					e.markAsDone();
				}
				currentTaskList.add(e);
			}
		}
	}


	/**
	 * Returns an arraylist of the tasks that were loaded
	 * from the text file.
	 *
	 * @return
	 */
	public static ArrayList<Task> myTask() {

		return currentTaskList;
	}
}
