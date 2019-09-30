import java.io.IOException;

/**
 * This class is initiased and its execute method is called whenever you want to exit from the application.
 */
public class ExitCommand extends Command {

    /**
     * Bids farewell to the user
     * @param taskList
     * @param ui
     * @param storage
     * @throws IOException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return ui.bye();
    }

    /**
     * Marks isExit as true to exit the program
     * @return
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
