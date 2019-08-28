import java.text.ParseException;

public class Command {

    protected boolean isExit;

    public Command() {
        isExit = false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws ParseException {

    }

    public boolean isExit() {
        return isExit;
    }
}