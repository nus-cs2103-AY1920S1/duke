package commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

import exceptions.DukeException;

/**
 * Command is the abstract base class for all types of commands that
 * can be given from user input. Concrete command classes that extend
 * from Command include: ListCommand, ExitCommand, AddCommand, DoneCommand
 * and DeleteCommand. Command objects contain an execute method that will
 * carry out the appropriate actions based on the specific type of command given.
 */
public abstract class Command {

    /** Boolean representing whether this command should trigger termination of the program */
    protected boolean isExit;
    /** String containing the full line of text retrieved from user input */
    protected String fullCommand;

    /**
     * Returns boolean determining whether program should be terminated
     * after execution of the command.
     *
     * @return boolean isExit.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes the appropriate set of instructions associated with each command.
     *
     * @param tasks the TaskList object storing all recorded Tasks.
     * @param ui the Ui object dealing with user interaction.
     * @param storage the Storage object that reads from and writes to the file.
     * @throws DukeException  If there is invalid input.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

    }

}
