import java.io.IOException;

public class DoneCommand extends Command {
    protected int doneIdx;
    public DoneCommand(int doneTaskIdx) {
        super("done");
        this.doneIdx = doneTaskIdx;
    }

    /**
     * Executes the Done command to mark a task done.
     * @param taskList list of tasks
     * @param ui Ui
     * @param storage Save changes to file in hard disk
     * @throws IOException exception
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Task doneTask = taskList.markTaskDone(doneIdx);
        storage.save(taskList.getTaskArr());
        ui.showMarkTaskDoneMessage(doneTask);
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