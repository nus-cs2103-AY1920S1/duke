package duke.command;

import duke.Storage;
import duke.TextUi;
import duke.task.TaskList;

/**
 * The ByeCommand class is used when an exit command is given.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a bye Command, which sets the boolean value isExit to true.
     */
    public ByeCommand() {
        super("");
        this.isExit = true;
    }

    /**
     * Displays an exit message on the given user interface.
     *
     * @param tasks             List of tasks
     * @param ui                User interface
     * @param storage           Hard disk storage
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        ui.showText("Bye. Hope to see you again soon!");
    }
}
