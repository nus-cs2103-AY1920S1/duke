/**
 * Represent user command to mark their task as completed in their task list.
 */

public class DoneCommand extends Command {

    protected int index;

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

    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        //check if the index is out of bound
        if (this.getIndex() < tasks.getTaskCount()) {
            Task doneTask = tasks.getTask(this.getIndex());
            tasks.markTaskDone(this.getIndex());
            ui.showDoneMessage(doneTask);
        } else {
            throw new DukeException("☹ Index is not within the list. Please enter a index within the list.");
        }
    }

    public boolean isExit() {
        return false;
    }
}
