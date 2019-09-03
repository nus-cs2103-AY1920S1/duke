import java.io.IOException;

public class DeleteCommand extends Command {
    protected int deleteIdx;
    public DeleteCommand(int deleteTaskIdx) {
        super("delete");
        this.deleteIdx = deleteTaskIdx;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Task deletedTask = taskList.deleteTask(deleteIdx);
        storage.save(taskList.getTaskArr());
        ui.showDeleteTaskMessage(deletedTask, taskList.getTaskArr());
    }

    public void print() {
        super.print();
        System.out.println("Index of task to delete: " + deleteIdx);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) { return true; }
        // Not even the same class
        if (!(o instanceof DeleteCommand)) { return false; }
        DeleteCommand c = (DeleteCommand) o;
        return c.deleteIdx == deleteIdx;
    }
}