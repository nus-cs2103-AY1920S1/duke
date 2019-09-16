package dukegui;

import duke.command.Command;

import duke.exception.DukeException;
import duke.exception.DukeIoException;

import duke.module.CommandStack;
import duke.module.Parser;
import duke.module.Storage;
import duke.module.TaskList;

/**
 * <h1>Duke GUI</h1>
 * Stores user-defined tasks.
 *
 * @author  Kyungho Min
 * @version v0.1
 * @since   2019-09-01
 */
public class Duke {

    private TaskList taskList;
    private Storage storage;
    private CommandStack commandStack;
    private boolean isExit;

    /**
     * Initializes the necessary modules to run the Duke application.
     *
     * @throws DukeIoException When an error occurs during the input-output process or
     *     during the parsing of the save file
     */
    public Duke() throws DukeIoException {
        this.commandStack = new CommandStack();
        this.storage = new Storage();
        this.taskList = new TaskList(storage.load());
        this.isExit = false;
    }

    /**
     * Returns the result of executing a command.
     *
     * @return Response of Duke.
     */
    public String getResponse(String input) {
        String[] command = input.split(" ", 2);
        try {
            String description = "";
            if (command.length == 2) {
                description = command[1];
            }
            Command c = Parser.parseToCommand(command[0], description);
            this.isExit = c.isExit();
            return c.getResponse(this.taskList, this.commandStack, this.storage);
        } catch (DukeException e1) {
            return e1.getMessage();
        }
    }

    public boolean isExit() {
        return this.isExit;
    }

}