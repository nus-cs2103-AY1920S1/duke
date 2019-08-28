package duke.command;

/**
 * Represents a {@link Command} to shut down the {@link duke.Duke} application.
 */
public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    @Override
    public void execute() {
        isExit = true;
        ui.showLine();
        ui.showMessage("Bye. Hope to see you again soon!");
        ui.showLine();
    }
}
