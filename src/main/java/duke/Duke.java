package duke;

import duke.command.Command;

import duke.exception.DukeException;
import duke.exception.DukeIOException;

import duke.module.Parser;
import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;
import duke.module.UndoStack;

/**
 * <h1>Duke</h1>
 * Stores user-defined tasks.
 *
 * @author  Kyungho Min
 * @version v0.1
 * @since   2019-09-01
 */
public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private UndoStack undoStack;

    /**
     * Initializes the necessary modules to run the Duke application.
     *
     * @throws DukeIOException When an error occurs during the input-output process or
     *     during the parsing of the save file
     */
    public Duke() throws DukeIOException {
        this.ui = new Ui();
        this.undoStack = new UndoStack();
        this.storage = new Storage();
        this.taskList = new TaskList(storage.load());
    }

    /**
     * Runs the Duke application.
     */
    public void run() {
        // Greet the user
        this.ui.greet();

        // Handle user input
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = this.ui.readCommand();
                String description = this.ui.readDescription();
                Command c = Parser.parseToCommand(command, description);
                c.execute(this.taskList, this.undoStack, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                this.ui.printToUser(e.getMessage());
            }
        }
    }

}