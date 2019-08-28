public class ExitCommand extends Command{

    public ExitCommand() {
        super();
        isExit = true;
    }

    @Override
    public void execute(TaskList t, Ui u, Storage s) {
        u.exitLine();
    }
}
