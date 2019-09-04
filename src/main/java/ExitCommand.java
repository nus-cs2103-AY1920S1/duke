/**
 * Represents the exit command.
 * Sets the boolean flag isExit to true for Ui to exit loop.
 */
public class ExitCommand extends Command {

    public ExitCommand(String type, String command) {
        super(type, command);
    }

    /**
     * Executes the ExitCommand.
     * Sets static boolean variable isExit to "true".
     *
     * @param ui       The Ui currently running.
     * @param taskList The TaskList Class containing the task list.
     * @param storage  The Storage class containing the name of file the be read.
     */
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        isExit = true;
    }
}
