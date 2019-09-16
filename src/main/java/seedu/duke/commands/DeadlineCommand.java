package seedu.duke.commands;

import seedu.duke.exceptions.InvalidArgumentException;
import seedu.duke.storage.TaskList;
import seedu.duke.trackables.Deadline;
import seedu.duke.trackables.Task;
import seedu.duke.ui.Ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DeadlineCommand extends Command {

    private String description;
    private String dateString;

    public DeadlineCommand(String description, String dateString) {
        this.description = description;
        this.dateString = dateString;
    }

    @Override
    public void execute(TaskList tasks) throws InvalidArgumentException {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("d/MM/yyyy HHmm");
            dateFormat.setLenient(false);
            Date date = dateFormat.parse(dateString);
            Deadline deadline = new Deadline(description, date);
            tasks.add(deadline);
            Ui.printMessages("Got it. I've added this task:",
                "  " + deadline.toString(),
                "Now you have " + tasks.size() + " tasks in the list.");
        } catch (ParseException pe) {
            throw new InvalidArgumentException("Date input is not in the right format d/MM/yyyy HHmm", pe);
        }
    }
}

