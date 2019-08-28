import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DukeReadFile {

	protected static String filePath;
	protected static String currentTask = "";
	protected static ArrayList<Task> ct = new ArrayList<>();
	static String border = "____________________________________________________________";

	public DukeReadFile (String filePath) {

		this.filePath = filePath;
	}

	public static void readFileContent() throws FileNotFoundException {
		File savedTask = new File(filePath);
		Scanner scanner = new Scanner(savedTask);
		while(scanner.hasNext()) {
			currentTask += scanner.nextLine() + "\n";
		}


		String[] addTask = currentTask.split("\n");

		for (int i = 0; i < addTask.length; i ++) {
			if (addTask[i].startsWith("T")) {
				String[] at = addTask[i].split("/");
				Todo td = new Todo(at[2]);
				if (at[1].equals("1")) {
					td.markAsDone();
				}
				ct.add(td);

			} else if (addTask[i].startsWith("D")) {
				String[] at = addTask[i].split("/");
				Deadline dl = new Deadline(at[2], at[3]);
				if (at[1].equals("1")) {
					dl.markAsDone();
				}
				ct.add(dl);

			} else if (addTask[i].startsWith("E")) {
				String[] at = addTask[i].split("/");
				Event e = new Event(at[2], at[3]);
				if (at[1].equals("1")) {
					e.markAsDone();
				}
				ct.add(e);
			} else {
//				try {
//					throw new DukeException("OOPS!! Invalid format.");
//				} catch (DukeException e) {
//					System.out.println(border + "\n" + e + "\n" + border);
//				}
			}

		}
	}

	public static ArrayList<Task> myTask() {
		return ct;
	}
}
