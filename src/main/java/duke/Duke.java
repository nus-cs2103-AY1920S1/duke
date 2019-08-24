package duke;

import java.io.File;
import java.io.IOException;
import exceptions.DukeException;
import commands.Command;

/**
 * Duke is the driver class of the program.
 * Parser, Storage, TaskList, Ui objects are instantiated here and
 * passed to Command objects in order to execute the appropriate actions.
 */
public class Duke {

    /** the Storage object that reads from and writes to the file */
    private Storage storage;
    /** the TaskList object storing all recorded Tasks */
    private TaskList tasks;
    /** the Ui object dealing with user interaction */
    private Ui ui;

    /**
     * Duke constructor that takes in a file path and
     * instantiates Ui, Storage and TaskList objects.
     *
     * @param filePath the path of the file to read from and write to.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * The driver method where the logic of the Duke
     * program is written: parsing of user input, instantiating
     * the appropriate Command class and then executing the corresponding
     * set of actions associated with that command.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showOpeningLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showClosingLine();
            }
        }
        // Save the new task list to the hard disk
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showSavingError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        String filePath = "." + File.separator + "data" + File.separator + "duke.txt";
        new Duke(filePath).run();
    }

}
