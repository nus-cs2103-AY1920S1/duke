package duke.command;

import duke.date.DateFormatter;
import duke.exception.DukeException;
import duke.component.TaskList;
import duke.database.Storage;
import duke.task.Deadline;

/**
 * This Deadline Command class get the input of the task description
 * and execute the deadline method to add deadline for task.
 *
 * @author TeoShyanJie
 *
 */
public class DeadlineCommand extends Command {
    /**
     * Constructor of DeadlineCommand class.
     * @param input Task input by user.
     * @param type Type of task.
     */
    public DeadlineCommand(String input, String type) {
        super(input, type);
    }

    /**
     * Execute method to execute deadline command.
     * @param tasks List of task.
     * @param storage The Database of duke program.
     * @return String of output.
     * @throws DukeException If deadline command fail to be executed.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return deadline(super.input, tasks);
    }

    /**
     * "deadline" command to enter deadline description and deadline time.
     * @param data deadline description and deadline of task.
     * @param tasks Task arraylist that contain list of task.
     * @return String of output.
     * @throws DukeException If description and time of deadline is empty.
     */
    public static String deadline(String data, TaskList tasks) throws DukeException {
        StringBuilder reply = new StringBuilder();

        if (data.isEmpty()) {
            throw new DukeException("OOPS !!! "
                    + "The description of a deadline cannot be empty.");
        }

        String[] result = data.split("/by");

        if (result.length <= 1) {
            throw new DukeException("OOPS !!! " + "Deadline is needed.");
        }

        String achieve = result[0].trim();
        String timeline = result[1].trim();

        DateFormatter formattedDate = new DateFormatter(timeline);
        tasks.getTask().add(new Deadline(achieve, formattedDate.getTime()));

        reply.append("Got it. I've added this task: ");
        reply.append("\n");
        reply.append(tasks.getTask().get(tasks.getItemNo()));
        reply.append("\n");
        tasks.setItemNo(tasks.getItemNo() + 1);
        reply.append("Now you have "
                + tasks.getItemNo() + " tasks in the list.");
        return reply.toString();
    }
}
