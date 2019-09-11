package commands;

import exceptions.DukeException;

import oop.Storage;
import oop.Ui;

import tasks.TaskList;

/**
 * The Command class covers all types of possible commands the user
 * may input into the application.
 */
public abstract class Command {
    /**
     * Checks whether "bye" has been said, if yes then isExit is true
     * and the program will terminate, else the program continues.
     */
    protected boolean isExit;

    /**
     * Description of the command, the necessary information of the command.
     */
    protected String description;

    /**
     * Constructs and initializes the attributes of a new Command object,
     * isExit is set to false by default.
     */
    public Command() {
        isExit = false;
    }

    /**
     * Checks whether the terminated command has been given in order to terminate
     * the application.
     * @return returns a boolean, true if "bye" is called, false if it is not.
     */
    public boolean checkExit() {
        return isExit;
    }

    /**
     * Carries out the command and does the necessary changes and prompts
     * user about the change.
     * @param task Current list of tasks.
     * @param ui Ui for user interactions.
     * @param storage Storage for writing of information to text file.
     * @throws DukeException Possibility of throwing a DukeException due to
     *      an exception occurring in the running of the application.
     */
    public abstract String execute(TaskList task, Ui ui, Storage storage) throws DukeException;
}

