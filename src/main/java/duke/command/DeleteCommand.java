package duke.command;

import duke.exception.DukeException;
import duke.component.TaskList;
import duke.component.Ui;
import duke.database.Storage;
import duke.main.Duke;

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
     * @param storage The Database of duke program.
     * @return String of output.
     * @throws DukeException If delete command fail to be executed.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String reply = delete(super.input, tasks);

        return reply;
    }

    /**
     * The delete method to delete specific task.
     * @param data Task enter by user.
     * @param tasks List of task.
     * @return String of output.
     * @throws DukeException If the list of task is empty
     *     or if the task to delete is not in the list.
     */
    public String delete(String data, TaskList tasks) throws DukeException {
        StringBuilder reply = new StringBuilder();

        try {
            if (data.isEmpty()) {
                if (tasks.getItemNo() == 0) {
                    throw new DukeException("OOPS !!! " + "The task list are currently empty.");
                } else {
                    throw new DukeException("OOPS !!! " + "Index of task are needed.");
                }
            }

            int item = Integer.parseInt(data);

            if (item > tasks.getItemNo()) {
                if (tasks.getItemNo() == 0) {
                    throw new DukeException("OOPS !!! " + "The task list are currently empty.");
                } else {
                    throw new DukeException("OOPS !!! "
                            + "Number enter can only be less than or equal number of task.");
                }
            }
            reply.append("Noted. I've removed this task: ");
            reply.append("\n");
            reply.append(tasks.getTask().remove(--item));
            reply.append("\n");
            tasks.setItemNo(tasks.getItemNo() - 1);
            reply.append("Now you have "
                    + tasks.getItemNo() + " tasks in the list.");
            return reply.toString();
        } catch (NumberFormatException ex) {
            throw new DukeException("OOPS !!! " + "Only Integer is allowed after done.");
        }
    }
}