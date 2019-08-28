/**
 * Represent user command.
 */

public abstract class Command {

    protected String command;

    /**
     * Represents an action taken by the user.
     * @param command User Action
     */
    public Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }

    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
