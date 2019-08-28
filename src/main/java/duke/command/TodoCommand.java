package duke.command;

import duke.exception.DukeException;
import duke.component.TaskList;
import duke.component.Ui;
import duke.database.Storage;
import duke.task.Todo;

/**
 * This To-do Command class get the input of the task description
 * and execute the to-do method to add task.
 *
 * @author TeoShyanJie
 *
 */
public class TodoCommand extends Command {
    /**
     * TodoCommand Constructor.
     * @param input The input task enter by user.
     * @param type The type of task enter by user.
     */
    public TodoCommand(String input, String type) {
        super(input, type);
    }

    /**
     * Execute method to execute to-do method.
     * @param tasks The list task enter by user.
     * @param ui The ui of the Duke Program.
     * @param storage The Database of Duke Program.
     * @throws DukeException If to-do method is not able to get executed.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        todo(super.input, tasks, ui);
    }

    /**
     * "to-do" command to enter the task description.
     * @param data to-do command and description of task.
     * @param tasks List of task save in arraylist.
     * @param ui Ui of Duke Program.
     * @throws DukeException If description of data is empty.
     */
    public void todo(String data, TaskList tasks, Ui ui) throws DukeException {
        if (data.isEmpty()) {
            throw new DukeException(ui.INDENT_COMMENT +"\u2639 OOPS !!! " + "The description of a todo cannot be empty.");
        }
        tasks.getTask().add(new Todo(data));
        System.out.println(ui.INDENT_COMMENT + "Got it. I've added this task: ");
        System.out.println(ui.INDENT_TASK + tasks.getTask().get(tasks.getItemNo()));
        tasks.setItemNo(tasks.getItemNo() + 1);
        System.out.println(ui.INDENT_COMMENT + "Now you have " + tasks.getItemNo() + " tasks in the list.");
    }
}