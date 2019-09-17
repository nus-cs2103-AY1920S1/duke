package duke.storage;

import java.io.*;
import java.util.ArrayList;

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
	 *
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
	public ArrayList<Task> load(Ui ui) throws IncorrectFileFormatException, IOException {
		//File f;
		//f = new File("data/tasks.txt");
		//Scanner s = new Scanner(f, "Unicode");
		
		
		InputStream stream = Storage.class.getClassLoader().getResourceAsStream("tasks.txt");
		if(stream == null){
			return new ArrayList<>();
		}
		
		assert stream != null;
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
			}
		}
		return listTask;
	}
	
	/**
	 * Obtain list of tasks to print, save to hard disk.
	 *
	 * @param l List containing all string format tasks to save.
	 */
	public void save(ArrayList<String> l) throws IOException {
		try {
			FileWriter fw = new FileWriter("data/print.txt");
			for (String s : l) {
				fw.write(s + System.lineSeparator());
			}
			fw.close();
		} catch (IOException e) {
			throw e;
		}
	}
}

