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
     * @param ui Ui of Duke Program.
     * @param storage Database of the Duke Program.
     * @throws DukeException If find method is not executable.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        find(tasks, ui);
    }

    /**
     * To find the specific task with keyword enter.
     * @param tasks The list of tasks.
     * @param ui Ui of Duke Program.
     */
    public void find(TaskList tasks, Ui ui) {
        int index = 1;
        System.out.println(ui.INDENT_COMMENT
                + "Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.getTask().size(); i++) {
            if (tasks.getTask().get(i).toString().toLowerCase().contains(super.input.trim())) {
                System.out.println(ui.INDENT_TASK + index++
                        + "." + tasks.getTask().get(i).toString());
            }
        }
    }
}
