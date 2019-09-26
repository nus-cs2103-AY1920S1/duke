package duke.command;

import duke.date.DateFormatter;
import duke.exception.DukeException;
import duke.component.TaskList;
import duke.database.Storage;
import duke.function.CheckDuplicate;
import duke.task.Event;

/**
 * This Event Command class get the input of the task description
 * and execute the event method to add the task description Date
 * and Time.
 *
 * @author TeoShyanJie
 *
 */
public class EventCommand extends Command {
    /**
     * Constructor of the EventCommand class.
     * @param input The input task enter by user.
     * @param type The type of task enter by user.
     */
    public EventCommand(String input, String type) {
        super(input, type);
    }

    /**
     * Execute method to execute the event method.
     * @param tasks List of task enter by user.
     * @param storage Database of the Duke Program.
     * @return String of output.
     * @throws DukeException If event method is not able to get executed.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String reply = event(super.input, tasks);

        return reply;
    }

    /**
     * "event" command to enter event description and event time.
     * @param data event description and time of event.
     * @param tasks List of task store in arraylist.
     * @return String of output.
     * @throws DukeException If data is empty and event time is not entered.
     */
    public static String event(String data, TaskList tasks) throws DukeException {
        final StringBuilder reply = new StringBuilder();

        if (data.isEmpty()) {
            throw new DukeException("OOPS !!! "
                    + "The description of an event cannot be empty.");
        }

        String[] result = data.split("/at");

        if (result.length <= 1) {
            throw new DukeException("OOPS !!! "
                    + "Event time is needed.");
        }

        String achieve = result[0].trim();
        String timeline = result[1].trim();

        DateFormatter formattedDate = new DateFormatter(timeline);

        Event event = new Event(achieve, formattedDate.getTime());

        CheckDuplicate check = new CheckDuplicate(event, tasks);

        if (check.addEvent()) {
            event.setTime(formattedDate.getTime());
            tasks.getTask().add(event);
        } else {
            throw new DukeException("OOPS !!! " + "Duplicate Task Detected.");
        }

        reply.append("Got it. I've added this task:  ");
        reply.append("\n");
        reply.append(tasks.getTask().get(tasks.getItemNo()));
        reply.append("\n");
        tasks.setItemNo(tasks.getItemNo() + 1);
        reply.append("Now you have "
                + tasks.getItemNo() + " tasks in the list.");

        return reply.toString();
    }
}