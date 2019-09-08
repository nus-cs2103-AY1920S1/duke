/**
 * Represents an abstract command.
 */
public abstract class Command {
    abstract public String exec(Storage storage, TaskList tasks, Ui ui);
}
