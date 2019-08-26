package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class representing a list command.
 */
public class ListCommand extends Command{
    public static final String COMMAND_WORD = "list";

    /**
     * Class constructor.
     */
    public ListCommand(){
    }

    /**
     * Executes the command.
     * @param tasks a list task to work on.
     * @param ui an user interface to show messages.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.showTasks(tasks);
    }
}