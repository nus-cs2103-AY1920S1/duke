package duke.task;

import duke.exception.EmptyFieldDukeException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public abstract class Task implements Serializable {

    private static final long serialVersionUID = 1L;
    private String description;
    private boolean isDone;

    /**
     * DateTimeFormatter meant for duke.ui.Ui output.
     */
    protected static DateTimeFormatter outDTF = DateTimeFormatter.ofPattern("MMMM d y, K:mm a");


    /**
     * Constructs an abstract duke.task.Task with description.
     *
     * @param description Description
     * @throws EmptyFieldDukeException On empty description
     */
    public Task(String description) throws EmptyFieldDukeException {
        this.description = description.trim();
        if (this.description.isBlank()) {
            throw new EmptyFieldDukeException("description", this.childClass());
        }
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return this.isDone ? "✓" : "✘";
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    protected abstract String childClass();

    protected static DateTimeFormatter inDateTimeFormat() {
        LocalDateTime dt = LocalDateTime.now();
        return new DateTimeFormatterBuilder().parseCaseInsensitive()
            .appendPattern("[MMMM][MMM][ ][/][d][ ][/][MMMM][MMM][M][ ][/][yyyy][ "
                + "]['T'][HH[':']mm]")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, dt.getHour())
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, dt.getMinute())
            .parseDefaulting(ChronoField.YEAR_OF_ERA, dt.getYear())
            .parseDefaulting(ChronoField.MONTH_OF_YEAR, dt.getMonthValue())
            .parseDefaulting(ChronoField.DAY_OF_MONTH, dt.getDayOfMonth())
            .toFormatter();
    }
}
