package duke.command;

import duke.Ui;
import duke.data.DukeData;
import duke.data.TaskList;

import java.io.IOException;

/**
 * Command is an interface which contains an execute method
 * to handle user commands.
 */
public interface Command {

    /**
     * Execute method which calls the method upon initialisation of the object.
     * @param dukeData the storage object of the program
     * @param ui ui object which handles output of user interaction
     * @param taskList the list of tasks that is stored in the Duke program
     * @return a string representation of the output for that command
     * @throws IOException if an I/O error occurs
     * @throws DukeException when there is a command Duke does not understand
     */
    String execute(DukeData dukeData, Ui ui, TaskList taskList)
            throws IOException, DukeException;

}
