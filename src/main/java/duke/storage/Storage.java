package duke.storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import duke.parser.IncorrectArgumentsException;
import duke.parser.IncorrectFileFormatException;
import duke.parser.Parser;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Represents a storage function of duke.
 * Has loading from file and saving to hard disk function.
 */
public class Storage {
	//private String targetFilePath;
	//private String printFilePath;
	
	/**
	 * Constructor to initialize storage filepaths.
	 * <p>
	 * //@param filePath String containing filepath to read list of task from disk.
	 */
	public Storage() {
		// Convert string filePath to actual filepath and store in FilePath class
		//targetFilePath = filePath;
		//printFilePath = "C:\\Users\\user\\Desktop\\CS2103_Git\\duke\\data\\print.txt";
	}
	
	/**
	 * Returns task list of tasks from file,
	 * convert input from file to task objects.
	 *
	 * @param ui UI to show load messages if failed.
	 * @return task list from file.
	 * @throws IncorrectFileFormatException If file format is incorrect.
	 */
	public ArrayList<Task> load(Ui ui) throws IncorrectFileFormatException, IOException, IncorrectArgumentsException {
		//File f;
		//f = new File("data/tasks.txt");
		//Scanner s = new Scanner(f, "Unicode");
		
		
		InputStream stream = Storage.class.getClassLoader().getResourceAsStream("tasks.txt");
		if (stream == null) {
			return new ArrayList<>();
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		
		ArrayList<String> listInput = new ArrayList<>();
		
		String i = " ";
		while ((i = reader.readLine()) != null) {
			listInput.add(i.trim());
		}
		
		ArrayList<Task> listTask = new ArrayList<>();
		for (String value : listInput) {
			try {
				listTask.add(Parser.parseFromFile(value, ui));
				
			} catch (IncorrectFileFormatException f1) {
				throw new IncorrectFileFormatException(ui.getLoadingError());
				
			} catch (NullPointerException n) {
				throw new NullPointerException();
			} catch (IncorrectArgumentsException e) {
				throw e;
			}
		}
		return listTask;
	}
	
	
}

