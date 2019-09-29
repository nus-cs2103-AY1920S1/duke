/**
 * Represents a user command. A <code>ByeCommand</code> object corresponds to
 * with a valid exit command e.g., <code>bye</code>
 */

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.byeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
