import javafx.application.Platform;

/**
 * Encapsulates a user command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Overridden method. Executes the exit command.
     * @param tasks list of tasks
     * @param ui user interface
     * @param storage storage file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
    }

    @Override
    public String executeForGui(TaskList tasks, Ui ui, Storage storage) {
        return ui.exitForGui();
    }
}
