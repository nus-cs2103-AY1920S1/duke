package commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

import tasks.Task;

import java.util.ArrayList;

import exceptions.DukeException;

/**
 * Command is the abstract base class for all types of commands that
 * can be given from user input. Concrete command classes that extend
 * from Command include: ListCommand, ExitCommand, AddCommand, DoneCommand
 * DeleteCommand and FindCommand. Command objects contain an execute method that will
 * carry out the appropriate actions based on the specific type of command given.
 */
public abstract class Command {

    /** String array containing the split text retrieved from user input. */
    String[] commandArr;

    /**
     * Constructor for Command.
     * Takes in an Array of Strings representing the full command given by the user.
     *
     * @param commandArr String array containing the split text retrieved from user input.
     */
    public Command(String[] commandArr) {
        this.commandArr = commandArr;
    }

    /**
     * Returns true if the taskIndex the user provided corresponds
     * to an existing task in the taskLst, and false otherwise.
     * (taskIndex can assume values from 0 to size of taskLst - 1)
     *
     * @param taskIndex the index of task user wants to perform operations on.
     * @param taskLst ArrayList storing all the Tasks.
     * @return boolean true if task number is valid, false otherwise.
     */
    static boolean checkValidTaskNumber(int taskIndex, ArrayList<Task> taskLst) {
        return taskIndex >= 0 && taskIndex < taskLst.size();
    }

    /**
     * Returns String array containing the split text retrieved from user input.
     *
     * @return String array commandArr.
     */
    public String[] getCommandArr() {
        return commandArr;
    }

    /**
     * Executes the appropriate set of instructions associated with each command.
     *
     * @param tasks the TaskList object storing all recorded Tasks.
     * @param ui the Ui object dealing with user interaction.
     * @param storage the Storage object that reads from and writes to the file.
     * @return String output reply from Duke.
     * @throws DukeException  If there is invalid input.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

}
