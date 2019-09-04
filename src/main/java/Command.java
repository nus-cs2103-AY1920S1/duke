/**
 * abstract class Command.
 */
public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    public boolean isExit() {
        return false;
    }
}
