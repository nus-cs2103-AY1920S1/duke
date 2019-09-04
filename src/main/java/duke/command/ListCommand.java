package duke.command;

/**
 * Class representing a command that lists out the contents
 * of the current task list. Inherits from the Command abstract
 * class.
 * @see Command
 */
public class ListCommand extends Command {

    /**
     * Prints out a string representation of TaskList object to the
     * screen for the user to view.
     */
    public String execute() {
        if (taskList.getSize() == 0) {
            //ui.displaySingleLine("Nice! You have no pending tasks!");
            return "Nice! You have no pending tasks!";
        } else {
            //ui.displaySingleLine("Here are the tasks in your list:");
            //ui.displayMessage(taskList.toString(), 2);
            return "Here are the tasks in your list:\n" +
                    ui.indentMessage(taskList.toString());
        }
    }
}
