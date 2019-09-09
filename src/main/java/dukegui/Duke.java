package dukegui;

import duke.exception.DukeException;
import duke.exception.DukeIOException;

import duke.module.Storage;
import duke.module.TaskList;

import dukegui.command.Command;
import dukegui.module.Parser;


/**
 * <h1>Duke GUI</h1>
 * Stores user-defined tasks.
 *
 * @author  Kyungho Min
 * @version v0.1
 * @since   2019-09-01
 */
public class Duke {

    /** Stores the tasks inputted by the user. */
    private TaskList taskList;
    /** Saves the tasks in the TaskList to use at the next boot up. */
    private Storage storage;
    /** Indicates whether this program should keep running or quit. */
    private boolean isExit;

    /**
     * Initializes the necessary modules to run the Duke application.
     *
     * @throws DukeIOException When an error occurs during the input-output process or
     *     during the parsing of the save file
     */
    public Duke() throws DukeIOException {
        this.storage = new Storage();
        this.taskList = new TaskList(storage.load());
        this.isExit = false;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String[] command = input.split(" ", 2);
        try {
            Command c = Parser.parseToCommand(command[0], command[1]);
            this.isExit = c.isExit();
            return c.getResponse(this.taskList, this.storage);
        } catch (ArrayIndexOutOfBoundsException e) {
            try {
                Command c = Parser.parseToCommand(command[0], "");
                this.isExit = c.isExit();
                return c.getResponse(this.taskList, this.storage);
            } catch (DukeException e2) {
                return e2.getMessage();
            }
        } catch (DukeException e1) {
            return e1.getMessage();
        }
    }

    public boolean isExit() {
        return this.isExit;
    }

}