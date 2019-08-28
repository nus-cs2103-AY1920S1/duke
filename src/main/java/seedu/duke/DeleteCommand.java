package seedu.duke;

public class DeleteCommand extends Command {
    protected static int delete;

    public DeleteCommand(int d) {
        delete = d;
        type = "delete";
    }

    @Override
    public void execute(TaskList t, Ui u, Storage s) {
        Task task = t.list.get(delete);
        t.list.remove(delete);
        u.deleteLine(task.toString(), t.list.size());
    }
}
