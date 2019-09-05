package duke.command;

import duke.component.TaskList;
import duke.component.Ui;
import duke.database.Storage;
import duke.exception.DukeException;

/**
 * Find command class allow user to find task
 * with specific keyword.
 *
 * @author TeoShyanJie
 *
 */
public class FindCommand extends Command {
    /**
     * Constructor of the FindCommand class.
     * @param input Keyword enter by user.
     * @param type Type of the task.
     */
    public FindCommand(String input, String type) {
        super(input, type);
    }

    /**
     * Execute method to execute the find method.
     * @param tasks List of task.
     * @param storage Database of the Duke Program.
     * @return String of output.
     * @throws DukeException If find method is not executable.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return find(tasks);
    }

    /**
     * To find the specific task with keyword enter.
     * @param tasks The list of tasks.
     * @return String of output.
     */
    public String find(TaskList tasks) {
        StringBuilder reply = new StringBuilder();

        int index = 1;
        reply.append("Here are the matching tasks in your list:");
        reply.append("\n");
        for (int i = 0; i < tasks.getTask().size(); i++) {
            if (tasks.getTask().get(i).toString().toLowerCase().contains(super.input.trim())) {
                reply.append(index++
                        + "." + tasks.getTask().get(i).toString());
                reply.append("\n");
            }
        }

        return reply.toString();
    }
}
