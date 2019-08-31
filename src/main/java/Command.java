import java.text.ParseException;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws ParseException;
}
