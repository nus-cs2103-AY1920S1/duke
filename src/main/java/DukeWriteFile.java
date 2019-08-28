import java.io.FileWriter;
import java.io.IOException;

public class DukeWriteFile {

	protected static String filePath;

	public DukeWriteFile(String filePath) {

		this.filePath = filePath;
	}

	public static void writeToFile(String textToaAdd) throws IOException {
		FileWriter fw = new FileWriter(filePath);
		fw.write(textToaAdd);
		fw.close();
	}

	public static void appendToFile(String textToAppend) throws IOException {
		FileWriter fw = new FileWriter(filePath, true);
		fw.write(textToAppend);
		fw.close();
	}

}
