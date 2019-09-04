/**
 * Abstract class for a command to be executed when the user inputs a command.
 */
abstract class Command {

    abstract boolean isExit();

    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
