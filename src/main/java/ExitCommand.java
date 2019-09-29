/**
 * Represents the Command for exiting the Duke Chatbot.
 * A sub-class of Command.
 */
public class ExitCommand extends Command {

    /**
     * Overridden execute method from Command to save the list data to the specified
     * text file. The method will set the hasExit boolean variable to true, call
     * the saveData method from storage and return a Goodbye Message for DialogBox.
     *
     * @param storage Storage object for saving purposes
     * @param tasks   Contains the list of tasks
     * @param ui      Holds Ui printing methods and user input field
     * @param input   User input
     * @return goodbye message
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui, String input) {
        hasExit = true;
        storage.saveData(tasks.getTaskList());
        return ui.showGoodbye();
    }
}
