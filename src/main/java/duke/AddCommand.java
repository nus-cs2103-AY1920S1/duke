package duke;

/**
 * Represents a command to add task. An <code>AddCommand</code> object corresponds to
 * an input from user to add task e.g., todo read book
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command, adds task to the task list.
     * User interface prints out message to user.
     * If saving tasks in the file throws exception, user interface prints out exception message to user.
     *
     * @param tasks Contains the task list .
     * @param ui Deals with interactions with the user.
     * @param storage Deals with loading tasks from the file and saving tasks in the file.
     * @return String add message to be printed by UI.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        try {
            tasks.addTask(this.task);
            storage.writeToHardDisk(tasks);
            return ui.printAddMessage(this.task, tasks);
        } catch (DukeException ex) {
            return ui.printException(ex);
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
     * Returns true if two instances of AddCommand are equal.
     * Otherwise, returns false.
     *
     * @param object  An object.
     * @return boolean.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof AddCommand) {
            AddCommand addCommand = (AddCommand) object;
            return this.task.equals(addCommand.task);
        }
        return false;
    }
}
