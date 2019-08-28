package duke.command;

import duke.command.Command;
import duke.exception.DukeException;
import duke.initials.Deadline;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeadlineCommand extends Command {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HHmm");

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, ParseException {

        int end2 = ui.getRemainingWords().indexOf('/');
        if (end2 > 0) {

            String description2 = ui.getRemainingWords().substring(1, ui.getRemainingWords().indexOf('/'));
            String time2 = ui.getRemainingWords().substring(end2 + 4).trim();
            if (time2.isEmpty()) {
                throw new DukeException("☹OOPS!!! Wrong format'");
            } else {
                Deadline k = new Deadline(description2, makeDate(time2));
                tasks.getTaskArrayList().add(k);
                storage.writeData();
                System.out.println("Got it. I've added this task:");
                System.out.println(k);
                System.out.println("Now you have " + tasks.getTaskArrayList().size() + " tasks in the list.");
                ui.showLine();
            }
        } else {
            throw new DukeException("☹OOPS!!! Wrong format");
        }
    }

    public boolean isExit() {
        return false;
    }

    public String makeDate(String input) throws ParseException {
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
        return outputDate;
    }
}
