/***
 * Abstract command class.
 */
public abstract class Command {
    protected boolean isExit;

    /**
     * Class constructor.
     * @param isExit Set to true when command is an exit command
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(Storage storage, TaskList tasks, UI ui);
}
