package duke.command;

import duke.exception.DukeException;
import duke.component.TaskList;
import duke.component.Ui;
import duke.database.Storage;

/**
 * This Exit Command class get the input of the task description
 * and execute the bye method and update the text file with task
 * saved.
 *
 * @author TeoShyanJie
 *
 */
public class ExitCommand extends Command {
    /**
     * ExitCommand class constructor.
     * @param input The input task enter by user.
     * @param type The type of task enter by user.
     */
    public ExitCommand(String input, String type) {
        super(input, type);
    }

    /**
     * Execute method to execute bye method.
     * @param tasks List of task enter.
     * @param ui Ui of Duke Program.
     * @param storage Database of the Duke Program.
     * @throws DukeException If bye method is not able to execute.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        bye(tasks, ui, storage);
        super.exit = true;
    }

    /**
     * "bye" to exit the Duke Program.
     * @param tasks List of task.
     * @param ui Ui of Duke Program.
     * @param storage Database of the Duke Program.
     * @throws DukeException If update file fail when exiting Duke Program.
     */
    public static void bye(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        System.out.println(ui.INDENT_COMMENT + "Bye. Hope to see you again soon!");
        storage.updateFile(tasks.getTask());
    }
}
