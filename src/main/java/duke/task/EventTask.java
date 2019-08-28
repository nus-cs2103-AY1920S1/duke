package duke.task;

import duke.constant.Consts;
import duke.exception.WrongDateFormatException;

import java.text.ParseException;

public class EventTask extends Task {

    public EventTask(String description, String startDate) throws WrongDateFormatException {
        super(description);
        try {
            this.dateTime = Consts.DATE_TIME_FORMATTER.parse(startDate);
        } catch (ParseException e) {
            throw new WrongDateFormatException();
        }
        this.type = "E";
    }

    public EventTask(String description, String startDate, boolean isDone) throws WrongDateFormatException {
        super(description, isDone);
        try {
            this.dateTime = Consts.DATE_TIME_FORMATTER.parse(startDate);
        } catch (ParseException e) {
            throw new WrongDateFormatException();
        }
        this.type = "E";
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), Consts.DATE_TIME_OUTPUT_FORMATTER.format(dateTime));
    }
}
