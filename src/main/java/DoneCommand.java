import java.io.IOException;

public class DoneCommand extends Command {

    protected int doneIdx;
    public DoneCommand(int doneTaskIdx) {
        super("done");
        this.doneIdx = doneTaskIdx;
    }

    /**
     * Marks a task done, saves changes in text file.
     * @param taskList list of tasks
     * @param ui
     * @param storage
     * @throws IOException
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        // Checks if index of task to mark done is within range
        int numTasks = taskList.getNumTasks();
        if (numTasks == 0) {
            throw new DukeException("You have no tasks to mark done!");
        }
        if (doneIdx < 1 || doneIdx > numTasks) {
            throw new DukeException("Please provide a positive integer that is " +
                    numTasks + " and below.");
        }

        Task doneTask = taskList.markTaskDone(doneIdx);
        storage.save(taskList.getTaskArr());
        ui.showMarkTaskDoneResponse(doneTask);
    }

    public void print() {
        super.print();
        System.out.println("Index of task to mark done: " + doneIdx);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) { return true; }
        // Not even the same class
        if (!(o instanceof DoneCommand)) {
            return false;
        }
        DoneCommand c = (DoneCommand) o;
        return c.doneIdx == doneIdx;
    }
}