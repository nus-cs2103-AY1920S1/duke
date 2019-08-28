import java.text.ParseException;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, ParseException;

    public abstract boolean isExit();
}
