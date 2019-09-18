/**
 * Represent user command to delete tasks from their task list.
 */

public class DeleteCommand extends Command {

    protected int index;
    private Task deletedTask;
    private TaskList tasks;

    /**
     * Represents an action to delete task using the index in their task list.
     * @param command Type of task
     * @param index Task Index on their task list
     */
    public DeleteCommand(String command, int index) {
        super(command);
        this.index = index;
    }

    public int getIndex() {
        return this.index - 1;
    }

    /**
     * Deletes a specific task from the task list.
     *
     * @param tasks List of tasks.
     * @param ui User Interface.
     * @param storage Storage of tasks.txt files.
     * @throws DukeException If index is not within the list.
     */
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (this.getIndex() >= tasks.getTaskCount()) {
            throw new DukeException("Index is not within the list. Please enter a index within the list.");
        }

        Task deletedTask = tasks.getTask(this.getIndex());
        tasks.deleteTask(this.getIndex());

        this.deletedTask = deletedTask;
        this.tasks = tasks;

        storage.save(tasks);
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        String printStr = "Noted. I've removed this task:\n"
                + "  " + deletedTask + "\n"
                + "Now you have " + tasks.getTaskCount() + " tasks in the list.";
        return printStr;
    }
}
