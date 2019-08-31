package duke.command;

/**
 * Class representing a command that lists out the contents
 * of the current task list. Inherits from the Command abstract
 * class.
 * @see {@link Command}
 */
public class ListCommand extends Command {

    /**
     * Prints out a string representation of TaskList object to the
     * screen for the user to view.
     */
    public void execute() {
        if (taskList.getSize() == 0) {
            ui.displaySingleLine("Nice! You have no pending tasks!");
        } else {
            ui.displaySingleLine("Here are the tasks in your list:");
            ui.displayMessage(taskList.toString(), 2);
        }
    }
}
