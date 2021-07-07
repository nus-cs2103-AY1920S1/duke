package duke;

/**
 * Represents a command to mark task as done. An <code>DoneCommand</code> object corresponds to
 * an input from user to mark task as done e.g., done 1
 */
public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Executes the command, marks task as done in the task list.
     * Throws exception if task is not found.
     * If saving tasks in the file throws exception, user interface prints out exception message to user.
     * User interface prints out message to user.
     *
     * @param tasks Contains the task list .
     * @param ui Deals with interactions with the user.
     * @param storage Deals with loading tasks from the file and saving tasks in the file.
     * @return String done message to be printed by UI.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index > tasks.getListSize()) {
            throw new DukeException("OOPS!!! duke.Task not found.");
        }
        Task task = tasks.getTask(index);
        task.markDone();
        try {
            storage.writeToHardDisk(tasks);
        } catch (DukeException ex) {
            ui.printException(ex);
        }

        return ui.printDoneMessage(task);
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
     * Returns true if two instances of DoneCommand are equal.
     * Otherwise, returns false.
     *
     * @param object  An object.
     * @return boolean.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof DoneCommand) {
            DoneCommand doneCommand = (DoneCommand) object;
            return this.index == doneCommand.index;
        }
        return false;
    }
}
