import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
     * @throws DukeException If description and time of event is empty.
     */
    public static void event(String data, TaskList tasks, Ui ui) throws DukeException {
        if (data.isEmpty()) {
            throw new DukeException(ui.INDENT_COMMENT +"\u2639 OOPS !!! " + "The description of an event cannot be empty.");
        }

        String[] result = data.split("/at");

        if (result.length <= 1) {
            throw new DukeException(ui.INDENT_COMMENT +"\u2639 OOPS !!! " + "Event time is needed.");
        }

        String achieve = result[0].trim();
        String timeline = result[1].trim();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HHmm");
        DateFormat outputformat = new SimpleDateFormat("d MMMM yyyy',' h:mm a");
        Date date = null;
        String formatted_Date = null;
        try {
            date= df.parse(timeline);
            formatted_Date = outputformat.format(date);
            tasks.getTask().add(new Deadline(achieve, formatted_Date));
            System.out.println(ui.INDENT_COMMENT + "Got it. I've added this task: ");
            System.out.println(ui.INDENT_TASK + tasks.getTask().get(tasks.getItemNo()));
            tasks.setItemNo(tasks.getItemNo() + 1);
            System.out.println(ui.INDENT_COMMENT + "Now you have " + tasks.getItemNo() + " tasks in the list.");
        } catch (ParseException pe) {
            System.out.println(ui.INDENT_COMMENT + "Invalid Data and Time Format");
        }


    }
}
