import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Writes the current tasks in the lst to a text document
 * whenever the task list changes.
 */
public class DukeWriteFile {

	protected static String filePath;

	public DukeWriteFile(String filePath) {

		this.filePath = filePath;
	}


	/**
	 * Writes the current task in the list to the text document.
	 *
	 * @param textToAdd Current tasks in the list.
	 * @throws IOException
	 */
	public static void writeToFile(String textToAdd) throws IOException {
		FileWriter fw = new FileWriter(filePath);
		fw.write(textToAdd);
		fw.close();
	}


	/**
	 * Appends new tasks to the text document instead of overwriting
	 * the current content.
	 *
	 * @param textToAppend New task to be appended to the text document.
	 * @throws IOException
	 */
	public static void appendToFile(String textToAppend) throws IOException {
		FileWriter fw = new FileWriter(filePath, true);
		fw.write(textToAppend);
		fw.close();
	}

	/**
	 * Converting current tasks in the list to a string
	 * in order to write onto a text document.
	 *
	 * @param currentTask Tasks that are on the list.
	 * @return String of the task on the list.
	 */
	public static String writeFile(ArrayList<Task> currentTask) {
		String writeTask = "";

		for (int i = 0; i < currentTask.size(); i++) {

			if (currentTask.get(i) instanceof Todo) {
				writeTask += "T~" + currentTask.get(i).getStatus() +
						"~" + currentTask.get(i).getDescription() + "\n";
			} else if (currentTask.get(i) instanceof Event) {
				writeTask += "E~" + currentTask.get(i).getStatus() +
						"~" + currentTask.get(i).getDescription() + "~" + ((Event) currentTask.get(i)).getVenue() +
						"\n";
			} else {
				writeTask += "D~" + currentTask.get(i).getStatus() +
						"~" + currentTask.get(i).getDescription() + "~" + ((Deadline) currentTask.get(i)).getDeadline() + "\n";
			}
		}
		return writeTask;
	}

	public static void createFile() {
		try{
			File createdFile = new File(filePath);
			createdFile.getParentFile().mkdirs();
			createdFile.createNewFile();
		} catch (IOException e) {
			System.out.println("File does not exist");
		}

	}

}
