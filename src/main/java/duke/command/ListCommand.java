package duke.command;

import duke.exception.DukeException;
import duke.component.TaskList;
import duke.database.Storage;

/**
 * This List Command class get the input of the task description
 * and execute the list method and list all the task.
 *
 * @author TeoShyanJie
 *
 */
public class ListCommand extends Command {
    /**
     * ListCommand constructor.
     * @param input The input task enter by user.
     * @param type The type of task enter by user.
     */
    public ListCommand(String input, String type) {
        super(input, type);
    }

    /**
     * Execute method to execute the list method.
     * @param tasks List of task.
     * @param storage The Database of the Duke Program.
     * @return String of output.
     * @throws DukeException The list method is not able to execute.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return list(tasks);
    }

    /**
     * "list" command to list all the task.
     * @param tasks List of task saved in arraylist.
     * @return String of output.
     * @throws DukeException if number of items = 0.
     */
    public static String list(TaskList tasks) throws DukeException {
        if (tasks.getItemNo() == 0) {
            throw new DukeException("OOPS !!! " + "The task list are currently empty.");
        }

        StringBuilder reply = new StringBuilder();
        int index = 1;
        reply.append("Here are the tasks in your list:");
        reply.append("\n");
        for (int i = 0; i < tasks.getTask().size(); i++) {
            reply.append("    " + index++ + "." + tasks.getTask().get(i));
            reply.append("\n");
        }

        return reply.toString();
    }
}