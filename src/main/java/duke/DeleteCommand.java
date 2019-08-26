package duke;

/**
 * Represents a command to delete task. An <code>DeleteCommand</code> object corresponds to
 * an input from user to delete task e.g., delete 1
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Executes the command, deletes task from the task list.
     * Throws exception if task is not found.
     * If saving tasks in the file throws exception, user interface prints out exception message to user.
     * User interface prints out message to user.
     *
     * @param tasks Contains the task list .
     * @param ui Deals with interactions with the user.
     * @param storage Deals with loading tasks from the file and saving tasks in the file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index > tasks.getListSize()) {
            throw new DukeException("OOPS!!! duke.Task not found.");
        }
        Task task = tasks.getTask(index);
        tasks.deleteTask(task);
        try {
            storage.writeToHardDisk(tasks);
        } catch (DukeException ex) {
            ui.printException(ex);
        }

        ui.printDeleteMessage(task, tasks);
}
    /**
     * Returns false for exit status.
     * If exit status is false, Duke continues operating.
     *
     * @return false.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns true if two instances of DeleteCommand are equal.
     * Otherwise, returns false.
     *
     * @param o  An object.
     * @return boolean.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof DeleteCommand) {
            DeleteCommand deleteCommand = (DeleteCommand) o;
            return this.index == deleteCommand.index;
        }
        return false;
    }
}
