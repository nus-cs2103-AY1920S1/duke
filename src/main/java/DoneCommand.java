/**
 * Represent user command to mark their task as completed in their task list.
 */

public class DoneCommand extends Command {

    protected int index;
    private Task doneTask;

    /**
     * Represents an action to mark task as done in their task list.
     * @param command Type of task
     * @param index Task Index on their task list
     */
    public DoneCommand(String command, int index) {
        super(command);
        this.index = index;
    }

    public int getIndex() {
        return this.index - 1;
    }

    /**
     * Marks the specific task that is done.
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

        Task doneTask = tasks.getTask(this.getIndex());
        tasks.markTaskDone(this.getIndex());
        this.doneTask = doneTask;

        storage.save(tasks);
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        String printStr = "Nice! I've marked this task as done:\n  " + doneTask;
        return printStr;
    }
}
