/**
 * An abstract class which represent what a command should do.
 */
abstract class Command {
    abstract boolean isExit();
    abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
