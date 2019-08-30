package duke;

import command.Command;
import task.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/**
 * Runs the program skeleton: Event flow.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private UserInterface ui;

    /**
     * Constructs Duke object.
     * @param filePath Specified file destination.
     */
    private Duke(String filePath) {
        ui = new UserInterface();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException | ParseException e) {
            ui.showError(e.getMessage());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the logic flow of the system.
     */
    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                storage.save(tasks.getAllTasks());
            } catch (DukeException | ParseException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main method to run the system.
     * @param args Arguments.
     */
    public static void main(String[] args) {
        new Duke("data/dukeData.txt").run();
    }
}