/**
 * Represents a bye command.
 *
 * @author Michelle Yong
 */
public class ByeCommand extends Command {
    /**
     * Creates a bye command.
     */
    public ByeCommand() {}

    /**
     * Executes the bye command and shows bye to the user.
     *
     * @param storage The storage for the file with all the tasks.
     * @param taskList The taskList used.
     * @param ui The User Interface used.
     * @return The bye message shown to the user.
     */
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        return ui.showBye();
    }
}