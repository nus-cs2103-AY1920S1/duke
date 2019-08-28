package seedu.duke;

public class ExitCommand extends Command{

    public ExitCommand() {
        super();
        isExit = true;
        type = "exit";
    }

    @Override
    public void execute(TaskList t, Ui u, Storage s) {
        u.exitLine();
    }
}
