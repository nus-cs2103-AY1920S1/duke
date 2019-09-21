import java.text.ParseException;
import java.util.Date;

public class Deadline extends Task {
    private Date by;

    /**
     * constructor.
     * @param description of deadline.
     * @param by is the date and time of the deadline.
     * @throws ParseException when format of date and time is different from required.
     */
    Deadline(String description, String by) throws ParseException {
        super(description);
        this.by = Duke.formatter.parse(by);
    }

    /**
     * date and time.
     * @return a string showing the date and time of deadline.
     */
    public String getBy() {
        return Duke.formatter.format(by);
    }

    @Override
    String getStoredForm() {
        String name = this.getDescription();
        int status = this.isDone()? 1 : 0;
        return "D " + status + " " + name + "@" + Duke.formatter.format(by) + "\n";
    }

    /**
     * postpone time of the event.
     *
     * @param time is when the task is postponed to.
     */
    @Override
    void postpone(String time) throws ParseException {
        this.by = Duke.formatter.parse(time);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Duke.formatter.format(by) + ")";
    }


}
