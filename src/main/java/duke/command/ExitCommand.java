package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Represents an executable action Exit. A <code>ExitCommand</code> object corresponds to
 * a terminate action for the Duke program.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        super();
        super.isExit = true;
    }

    /**
     * Signals termination of Duke program.
     *
     * @param taskList  List of Task objects.
     * @param ui Interface for user interaction.
     * @param storage Interface for read and write operations on file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printLine("Bye. Hope to see you again soon!");
    }
}