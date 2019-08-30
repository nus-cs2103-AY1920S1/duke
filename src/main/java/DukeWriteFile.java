import java.io.FileWriter;
import java.io.IOException;

public class DukeWriteFile {

	protected static String filePath;

	public DukeWriteFile(String filePath) {

		this.filePath = filePath;
	}


	/**
	 * Writes the current task in the list to the text document.
	 *
	 * @param textToaAdd Current tasks in the list.
	 * @throws IOException
	 */
	public static void writeToFile(String textToaAdd) throws IOException {
		FileWriter fw = new FileWriter(filePath);
		fw.write(textToaAdd);
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

}
