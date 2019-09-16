package duke.command;

import duke.Ui;
import duke.data.DukeData;

import java.io.IOException;

/**
 * Command is an interface which contains an execute method to handle user commands.
 */
public interface Command {

    /**
     * Execute method which calls the method upon initialisation of the object.
     * @param dukeData the storage object of the program
     * @param ui ui object which handles output of user interaction
     * @return a string representation of the output for that command
     */
    public String execute(DukeData dukeData, Ui ui) throws IOException, DukeException;

}
