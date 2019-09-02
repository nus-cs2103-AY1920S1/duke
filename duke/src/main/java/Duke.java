import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

import java.text.ParseException;

public class Duke {

	// private static ArrayList<Task> list = new ArrayList<>();
	private Storage storage;
	private TaskList tasks;
	private Ui ui;

	private static String filename = "./data/duke.txt"; // todo isDone? description

	public Duke(String filename) {
		ui = new Ui();
		storage = new Storage(filename);

		try {
			tasks = new TaskList(storage.load());
		} catch (FileNotFoundException fe) {
			ui.showError(fe.getMessage());
		} catch (DukeException e) {
			tasks = new TaskList();
			ui.showLoadingError(); // i suppose this just says file is corrupted therefore creating new or sth
		} catch (ParseException pe) {
			ui.showError(pe.getMessage());
		}
	}

	public void run() {
		ui.showWelcome();
		boolean isExit = false;
		while (!isExit) {
	        try {
	        	String command = ui.readWord();
	            String remainingCommand = ui.readCommand();
	            ui.showLine(); // show the divider line ("_______")
	            Command c = Parser.parse(command, remainingCommand);
	            c.execute(tasks, ui);
	            storage.update(tasks, filename);
	        	isExit = c.isExit();
	        } catch (DukeException e) {
	            ui.showError(e.getMessage());
	        } catch (ParseException pe) {
	        	ui.showError(pe.getMessage());
	        } catch (IOException e) {
				ui.showError("Something went wrong: " + e.getMessage());
			} finally {
	            ui.showLine();
	        }
	    }
		// bye invoked
		// update duke.txt with list

		try {
			storage.update(tasks, filename);
		} catch (IOException e) {
			ui.showError("Something went wrong with the file: " + e.getMessage());
		}
	}

    public static void main(String[] args) {
		new Duke(filename).run();
    }
}
