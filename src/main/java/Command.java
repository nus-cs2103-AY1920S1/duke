

public abstract class Command {
    abstract String execute(TaskList tasks, Storage storage) throws DukeException;

    abstract boolean isExit();
}
