import commands.Command;
import exception.DukeException;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * A program that tracks a lists of tasks.
 * Capable of adding, deleting and listing tasks.
 * Tasks can be marked done.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke program.
     * Initializes the Ui, Storage and Tasklist.
     *
     * @see Ui
     * @see Storage
     * @see TaskList
     */
    public Duke() {
        ui = new Ui();
        try {
            storage = new Storage("tasks.txt");
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            try {
                // creates tasks.txt file if not found
                FileOutputStream file = new FileOutputStream("tasks.txt");
                storage = new Storage("tasks.txt");
                tasks = new TaskList();
            } catch (FileNotFoundException fileE) {
                ui.showError("Admin permission required to create text file for backup.");
            }
        }
    }


    /**
     * Returns the response of Duke after executing the user command.
     *
     * @param input the string representation of the user command
     * @return the string representation of the Duke response
     */
    protected String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }


}