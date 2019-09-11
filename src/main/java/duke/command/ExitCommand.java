package duke.command;

import duke.exception.DukeException;
import duke.component.TaskList;
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
     * @param storage Database of the Duke Program.
     * @return String of output.
     * @throws DukeException If bye method is not able to execute.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        super.exit = true;
        return bye(tasks, storage);
    }

    /**
     * "bye" to exit the Duke Program.
     * @param tasks List of task.
     * @param storage Database of the Duke Program.
     * @return String of output.
     * @throws DukeException If update file fail when exiting Duke Program.
     */
    public static String bye(TaskList tasks, Storage storage) throws DukeException {
        storage.updateFile(tasks.getTask());
        return "Bye. Hope to see you again soon!";
    }
}
