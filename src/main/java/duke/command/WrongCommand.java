package duke.command;

import duke.Ui;
import duke.data.DukeData;

public class WrongCommand implements Command {
    private String givenCommand;

    /**
     * Creates a WrongCommand whenever there is a Command Duke does not understand.
     * @param command the user input, or supposed command
     */
    public WrongCommand(String command) {
        this.givenCommand = command;
    }
    /**
     * Execute method which calls the method upon initialisation of the object.
     * @param dukeData the storage object of the program
     * @param ui       ui object which handles output of user interaction
     * @return a string representation of the output for any command that Duke does not comprehend
     */
    @Override
    public String execute(DukeData dukeData, Ui ui) throws DukeException {
        throw new DukeException(this.givenCommand);
    }
}
