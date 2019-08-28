import java.io.IOException;

public class DoneCommand extends Command {
    protected static int done;

    public DoneCommand(int d) {
        done = d;
    }

    @Override
    public void execute(TaskList t, Ui u, Storage s) {
        Task task = t.list.get(done);
        task.done();
        try {
            s.update(false, "", t);
        } catch (IOException e) {
            u.showError(e.getMessage());
        }
        u.doneLine(task.toString());
    }
}
