package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.sheet.Sheet;
import duke.exception.DukeException;

/**
 * Parent class of various allowed commands in Duke.
 */
public abstract class Command {

    protected String command;
    protected String type;

    /**
     * Constructs a Command object.
     *
     * @param command String of user input without the command head.
     */
    public Command(String command) {
        this.command = command;
    }

    /**
     * Instructs Sheet to execute the commend, Storage to update the file, and Ui to print message, if necessary.
     *
     * @param sh Sheet object to carry out the command.
     * @param ui Ui object to print the message.
     * @param stor Storage object to update the change to file.
     * @throws DukeException If error occurs when accessing the file by Storage.
     */
    public abstract void execute(Sheet sh, Ui ui, Storage stor) throws DukeException;

    /**
     * Checks whether the command is an exit command.
     *
     * @return True if the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * ToString method that returns the String representation of the object.
     *
     * @return String representation of the Command object.
     */
    @Override
    public String toString() {
        return type + command;
    }
}
