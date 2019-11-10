package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Class for the command that prompts the program to terminate.
 */
public class ExitCommand implements Command {

    /**
     * Constructs the exit command.
     */
    public ExitCommand() {
    }

    /**
     * Indicates whether this is an exit command.
     *
     * @return true
     */
    public boolean isExitCommand() {
        return true;
    }

    /**
     * Prints the bye message through the Ui object.
     *
     * @param tasks tasklist that stores all the tasks
     * @param ui ui object that deals with user interaction
     * @param storage storage object that deals with writing into the text file
     * @return string representation of the command response
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showResultOfCommand("     Bye. Hope to see you again soon!");
    }
}
