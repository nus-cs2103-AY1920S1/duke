import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * The individual project portion for CS2103T which act as a task list
 * manager where you are able to input deadlines, events or things that needs
 * to be done.
 */
public class Duke {

	DukeReadFile readFile;
	DukeWriteFile writeFile;
	Ui ui;
	TaskList tasks;
	Parser userCommand;

	public Duke(String filePath) {
		ui = new Ui();
		readFile = new DukeReadFile(filePath);
		writeFile = new DukeWriteFile(filePath);
		try {
			readFile.readFileContent();
			tasks = new TaskList(readFile.myTask());
		} catch (FileNotFoundException e) {
			tasks = new TaskList();
			System.out.println("File not found");
		}
	}


	/**
	 * Execution of the task list manager
	 */
	public void run() {
		try {
			System.out.println(ui.INTRO);
			boolean isExit = false;
			userCommand = new Parser(ui, tasks, readFile, writeFile);
			while (!isExit) {
				String input = ui.getInput();
				userCommand.evaluate(input);
				isExit = userCommand.isExit();
			}
		} catch (DukeInvalidArgumentException e) {
			System.out.println(ui.BORDER + "\n" + e + "\n" + ui.BORDER);
		} catch (DukeException e) {
			System.out.println(ui.BORDER + "\n" + e + "\n" + ui.BORDER);
		}

	}

	/**
	 * Main method to execute the Duke's program.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		new Duke("data/duke.txt").run();
	}
}
