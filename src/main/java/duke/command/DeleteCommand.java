package duke.command;

import duke.exception.DukeException;
import duke.component.TaskList;
import duke.component.Ui;
import duke.database.Storage;

/**
 * This Delete Command class get the input of the task description
 * and execute the delete method to delete task.
 *
 * @author TeoShyanJie
 *
 */
public class DeleteCommand extends Command {
    /**
     * DeleteCommand class constructor.
     * @param input Task enter by user.
     * @param type The task of task.
     */
    public DeleteCommand(String input, String type) {
        super(input, type);
    }

    /**
     * Execute method to execute delete command.
     * @param tasks List of task.
     * @param ui The ui of duke program.
     * @param storage The Database of duke program.
     * @throws DukeException If delete command fail to be executed.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        delete(super.input, tasks, ui);
    }

    /**
     * The delete method to delete specific task.
     * @param data Task enter by user.
     * @param tasks List of task.
     * @param ui The ui of Duke Program.
     * @throws DukeException If the list of task is empty
     *     or if the task to delete is not in the list.
     */
    public void delete(String data, TaskList tasks, Ui ui) throws DukeException {
        try {
            if (data.isEmpty()) {
                if (tasks.getItemNo() == 0) {
                    throw new DukeException(ui.INDENT_COMMENT
                            + "\u2639 OOPS !!! " + "The task list are currently empty.");
                } else {
                    throw new DukeException(ui.INDENT_COMMENT
                            + "\u2639 OOPS !!! " + "Index of task are needed.");
                }
            }

            int item = Integer.parseInt(data);

            if (item > tasks.getItemNo()) {
                if (tasks.getItemNo() == 0) {
                    throw new DukeException(ui.INDENT_COMMENT
                            + "\u2639 OOPS !!! " + "The task list are currently empty.");
                } else {
                    throw new DukeException(ui.INDENT_COMMENT + "\u2639 OOPS !!! "
                            + "Number enter can only be less than or equal number of task.");
                }
            }
            System.out.println(ui.INDENT_COMMENT + "Noted. I've removed this task: ");
            System.out.println(ui.INDENT_TASK + tasks.getTask().remove(--item));
            tasks.setItemNo(tasks.getItemNo() - 1);
            System.out.println(ui.INDENT_COMMENT + "Now you have "
                    + tasks.getItemNo() + " tasks in the list.");
        } catch (NumberFormatException ex) {
            System.out.println(ui.INDENT_COMMENT
                    + "\u2639 OOPS !!! " + "Only Integer is allowed after done.");
        }
    }
}