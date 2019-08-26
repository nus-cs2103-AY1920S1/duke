package duke;

/**
 * Represents a command to list all tasks. An <code>ListCommand</code> object corresponds to
 * an input from user to list all tasks in the task list e.g., list
 */
public class ListCommand extends Command {

    /**
     * Executes the command.
     * User interface prints out message to user.
     * Prints out each task in the task list.
     *
     * @param tasks Contains the task list .
     * @param ui Deals with interactions with the user.
     * @param storage Deals with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printListMessage();
        int counter = 0;
        for (Task t : tasks.taskList) {
            counter++;
            System.out.println(counter + ". " + t);
        }
    }

    /**
     * Returns false for exit status.
     * If exit status is false, Duke continues operating.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns true if two instances of ListCommand are equal.
     * Otherwise, returns false.
     *
     * @param o  An object.
     * @return boolean.
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof ListCommand;
    }
}
