package commands;

import exceptions.DukeException;

import oop.Storage;
import oop.Ui;

import tasks.TaskList;

/**
 * The PrintCommand class takes care of any printing command passed in
 * by the user.
 */
public class PrintCommand extends Command {
    /**
     * Constructs and initializes the attributes of a new PrintCommand object.
     */
    public PrintCommand() {
        super();
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
    public String execute(TaskList task, Ui ui, Storage storage) {
        return ui.showText(task.printTasks());
    }
}

