package duke.main;

import duke.component.Ui;
import duke.component.TaskList;
import duke.database.Storage;
import duke.command.Command;
import duke.component.Parser;
import duke.exception.DukeException;

/**
 * Duke class handle the initialise of the Duke Program,
 * database and exit of the program.
 *
 * @author TeoShyanJie
 *
 */
public class Duke {
    /** Database of the Duke Program. */
    private Storage storage;

    /** List of task. */
    private TaskList tasks;

    /** Ui of the Duke Program. */
    private Ui ui;

    /**
     * Duke program to initalise the Database, Ui and get the task list.
     */
    public void initialise() {
        ui = new Ui();
        storage = new Storage(ui);
        storage.initialise();
        tasks = new TaskList(storage.getSavedTask());
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String command = "";

        try {
            Command c = Parser.parse(input);
            command = c.execute(tasks, storage);
        } catch (DukeException ex) {
            command = ex.getMessage();
        }

        return command;
    }
}