package duke.command;

import duke.exception.DukeException;
import duke.component.TaskList;
import duke.component.Ui;
import duke.task.Task;
import duke.database.Storage;

/**
 * This Done Command class get the input of the task description
 * and execute the done method to mark task as done.
 *
 * @author TeoShyanJie
 *
 */
public class DoneCommand extends Command {
    /**
     * DoneCommand class constructor.
     * @param input The input task enter by user.
     * @param type The type of task.
     */
    public DoneCommand(String input, String type) {
        super(input,type);
    }

    /**
     * The execute method to execute done method.
     * @param tasks The list of task.
     * @param storage The Database of Duke Program.
     * @return String of output.
     * @throws DukeException If done method is not executed properly.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return done(super.input, tasks);
    }

    /**
     * "done" command to check the finish task.
     * @param data Command and item index of the task.
     * @param tasks Task array that contains list of tasks.
     * @return String of output.
     * @throws DukeException if number of items = 0 and index enter > total number of task.
     */
    public static String done(String data, TaskList tasks) throws DukeException {
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

            assert (item > 0) : "Index Must be more than 1";

            if (item > tasks.getItemNo()) {
                if (tasks.getItemNo() == 0) {
                    throw new DukeException("OOPS !!! " + "The task list are currently empty.");
                } else {
                    throw new DukeException("OOPS !!! "
                            + "Number enter can only be less than or equal number of task.");
                }
            }
            reply.append("Nice! I've marked this task as done:");
            reply.append("\n");
            Task t = tasks.getTask().get(--item);
            t.markAsDone();
            reply.append(t);

            return reply.toString();
        } catch (NumberFormatException ex) {
            throw new DukeException("OOPS !!! " + "Only Integer is allowed after done.");
        }
    }
}