public class ListCommand extends Command {

    @Override
    public void execute(TaskList t, Ui u, Storage s) {
        u.list(t);
    }
}
