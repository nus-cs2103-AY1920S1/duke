package duke;

import duke.command.Command;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Main application class.
 */
public class Duke {
    private PreParser preParser;
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a Duke object with the data file residing in the specified path.
     *
     * @param filePath  String designating the file to load and save tasks into
     */
    public Duke(String filePath) {
        preParser = new PreParser();
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e);
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Prints the welcome message and begins receiving input from the user.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = preParser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.oops(e.getMessage());
            }
        }

        try {
            storage.save(tasks);
        } catch(IOException e) {
            System.err.println(e);
            ui.oops("Couldn't save tasks to disk.");
        }
    }
}
