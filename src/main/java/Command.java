/**
 * An abstract class which represent what a command should do.
 */
abstract class Command {
    String input;

    abstract boolean isExit();

    abstract String execute(TaskList tasks, Storage storage);
}
