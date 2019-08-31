package duke.command;

/**
 * Class representing a command that initiates shut down of the Duke
 * system.
 */
public class ByeCommand extends Command {

    /**
     * Sets the isExit attribute to true,
     * indicating that this command requires Duke
     * to shut down.
     */
    public void execute() {
        this.isExit = true;
        this.ui.greetGoodbye();
    }
}
