package duke.command;

import duke.exception.DukeException;
import duke.initials.Event;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventCommand extends Command {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * Creates new deadline task using a TaskList, Ui and Storage, it will then be added into the taskArrayList that
     * was loaded into the TaskList as param.
     * @param tasks the TaskList to be used
     * @param ui the Ui to be used
     * @param storage the Storage to be used
     * @throws DukeException
     * @throws ParseException if the date is not able to be parsed
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, ParseException{
        ui.showLine();
        int end = ui.getRemainingWords().indexOf('/');
        if (end > 0) {
            String description = ui.getRemainingWords().substring(1, ui.getRemainingWords().indexOf('/'));
            String time = ui.getRemainingWords().substring(end + 4).trim();
            if (time.isEmpty()) {
                throw new DukeException("☹OOPS!!! Wrong format'");
            } else {
                Event m = new Event(description, makeDate(time));
                tasks.add(m);
                storage.writeData();

                String toPrint = "Got it. I've added this task: \n "
                        + m + "\n" + "Now you have " + tasks.getTaskArrayList().size() + " tasks in the list.";
                return toPrint;
            }
        } else {
            throw new DukeException("☹OOPS!!! Wrong format'");
        }
    }

    /** Makes a date in the format from the user input
     * @param input
     * @return a string date that is in the required form
     * @throws ParseException upon wrong format of date
     * adopted and edited from weigenie (github username)
     */
    public String makeDate(String input) throws ParseException {

        assert input!= null : "input should be an instance of string";

        String ordinalIndicator;
        Date date = DATE_FORMAT.parse(input);
        String day = new SimpleDateFormat("dd").format(date);
        String month = new SimpleDateFormat("MMMMMMMMMMM").format(date);
        String year = new SimpleDateFormat("yyyy").format(date);
        String time = new SimpleDateFormat("h:mma").format(date).toLowerCase();

        int int_day = Integer.parseInt(day);

        if (int_day >= 11 && int_day <= 13) {
            ordinalIndicator = "th";
        } else if (int_day % 10 == 1) {
            ordinalIndicator = "st";
        } else if (int_day % 10 == 2) {
            ordinalIndicator = "nd";
        } else if (int_day % 10 == 3) {
            ordinalIndicator = "rd";
        } else {
            ordinalIndicator = "th";
        }

        String outputDate = int_day + ordinalIndicator + " of " + month + " " + year + ", " + time;

        assert outputDate!= null : "outputDate should not be null";

        return outputDate;
    }
}
