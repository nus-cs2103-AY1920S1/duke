package command;

import ui.Ui;
import taskList.TaskList;
import storage.Storage;
/**
 * Represents the exit command.
 * Sets the boolean flag isExit to true for ui.Ui to exit loop.
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
     * @return output The String output for GUI message.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        String output = ui.showFarewell();
        isExit = true;
        return output;
    }
}
