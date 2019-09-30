/**
 * Represents an abstract command.
 */
public abstract class Command {
    public abstract String exec(Storage storage, TaskList tasks, Ui ui);
}
