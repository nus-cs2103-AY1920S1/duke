import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.EmptyInstructionException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.IOException;

/**
 * Duke is a to-do list that allows users to store todos, deadlines and events. Users are able to enter several
 * commands, such as list to list all tasks, done to mark certain tasks as done, and add and delete tasks.
 */
public class Duke {

    /**
     * Storage object that access the data file.
     */
    private Storage storage;

    /**
     * TaskList object that accesses the ArrayList of Tasks
     */
    private TaskList tasks;

    /**
     * UI interface of Duke
     */
    private Ui ui;


    public Duke() {
        this("duke.txt");
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            if (!input.equals("bye")) {
                Command c = Parser.parse(input);
                return c.execute(tasks, storage);
            } else {
                return ui.exit();
            }
        } catch (DukeException | IOException | EmptyInstructionException e) {
            return ui.showError(e.getMessage());
        }
    }
}
