package seedu.duke.commands;

import seedu.duke.exceptions.InvalidArgumentException;
import seedu.duke.trackables.Event;
import seedu.duke.trackables.Task;
import seedu.duke.ui.Ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EventCommand extends Command {

    private String description;
    private String dateString;

    public EventCommand(String description, String date) {
        this.description = description;
        this.dateString = date;
    }

    @Override
    public void execute(List<Task> tasks) throws InvalidArgumentException {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("d/MM/yyyy HHmm");
            dateFormat.setLenient(false);
            Date date = dateFormat.parse(dateString);
            Event event = new Event(description, date);
            tasks.add(event);
            Ui.printMessages("Got it. I've added this task:",
                "  " + event.toString(),
                "Now you have " + tasks.size() + " tasks in the list.");
        } catch (ParseException pe) {
            throw new InvalidArgumentException("Date input is not in the right format d/MM/yyyy HHmm", pe);
        }

    }
}
