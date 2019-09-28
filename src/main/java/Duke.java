import java.io.FileNotFoundException;

/**
 * The individual project portion for CS2103T which act as a task list
 * manager where you are able to input deadlines, events or things that needs
 * to be done.
 */
public class Duke {

	private DukeReadFile readFile;
	private DukeWriteFile writeFile;
	private Ui ui;
	private TaskList tasks;
	private Parser userCommand;

	public Duke() {
		ui = new Ui();
		writeFile = new DukeWriteFile("data/duke.txt");
		readFile = new DukeReadFile("data/duke.txt");
		try {
			readFile.readFileContent();
			tasks = new TaskList(readFile.myTask());
			userCommand = new Parser(ui, tasks, readFile, writeFile);
		} catch (FileNotFoundException e) {
			writeFile.createFile();
			tasks = new TaskList();
			userCommand = new Parser(ui, tasks, readFile, writeFile);
		}
	}

	public String getResponse(String input) {
		String userOutput = "";
		userOutput = userCommand.evaluate(input);
		return userOutput;

	}

}
