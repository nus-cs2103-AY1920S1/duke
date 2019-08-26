/**
 * Abstract class to represent a command to be executed when an input is entered into a Duke object.
 */
public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;
    public abstract boolean isExit();

}
