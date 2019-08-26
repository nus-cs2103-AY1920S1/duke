package duke.Tasks;

import duke.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task{

    private Date eventTime;
    SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public Event(String taskName, String eventTime) throws DukeException {
        super(taskName);
        try {
            this.eventTime = myFormat.parse(eventTime);
        } catch (ParseException e) {
            throw new DukeException("The date input format is not correct, " +
                    "it should be in the form dd/MM/yyyy HH:mm:ss");
        }
    }

    @Override
    public String task_info() {
        String indicator;
        if (isFinished()) indicator = "[\u2713] ";
        else indicator = "[\u2715] ";
        return "[E]" + indicator + get_name() + " (at: " + myFormat.format(eventTime) + ")";
    }

    @Override
    public String record_info() {
        if (isFinished()) return "E|" + "1|" + get_name() + "|" + myFormat.format(eventTime);
        else return "E|" + "0|" + get_name() + "|" + myFormat.format(eventTime);
    }
}
