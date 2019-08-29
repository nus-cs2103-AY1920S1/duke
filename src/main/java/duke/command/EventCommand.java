package duke.command;

import duke.exception.DukeException;
import duke.component.Ui;
import duke.component.TaskList;
import duke.database.Storage;
import duke.task.Event;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
     * @param ui Duke Ui Program enter by user.
     * @param storage Database of the Duke Program.
     * @throws DukeException If event method is not able to get executed.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        event(super.input, tasks, ui);
    }

    /**
     * "event" command to enter event description and event time.
     * @param data event description and time of event.
     * @param tasks List of task store in arraylist.
     * @param ui Ui of Duke Program.
     * @throws DukeException If data is empty and event time is not entered.
     */
    public static void event(String data, TaskList tasks, Ui ui) throws DukeException {
        if (data.isEmpty()) {
            throw new DukeException(ui.INDENT_COMMENT + "\u2639 OOPS !!! "
                    + "The description of an event cannot be empty.");
        }

        String[] result = data.split("/at");

        if (result.length <= 1) {
            throw new DukeException(ui.INDENT_COMMENT + "\u2639 OOPS !!! "
                    + "Event time is needed.");
        }

        String achieve = result[0].trim();
        String timeline = result[1].trim();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HHmm");
        DateFormat outputformat = new SimpleDateFormat("d MMMM yyyy',' h:mm a");
        Date date = null;
        String formattedDate = null;
        try {
            date = df.parse(timeline);
            formattedDate = outputformat.format(date);
            tasks.getTask().add(new Event(achieve, formattedDate));
            System.out.println(ui.INDENT_COMMENT + "Got it. I've added this task: ");
            System.out.println(ui.INDENT_TASK + tasks.getTask().get(tasks.getItemNo()));
            tasks.setItemNo(tasks.getItemNo() + 1);
            System.out.println(ui.INDENT_COMMENT + "Now you have "
                    + tasks.getItemNo() + " tasks in the list.");
        } catch (ParseException pe) {
            System.out.println(ui.INDENT_COMMENT + "Invalid Data and Time Format");
        }


    }
}