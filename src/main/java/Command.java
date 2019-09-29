/**
 * Represents the overall command class. A <code>Command</code> object inherits
 * from this abstract command class. e.g., <code>bye, delete #index</code>
 */

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
