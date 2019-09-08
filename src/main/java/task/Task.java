package task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class Task {
    public static final int NOT_DONE = 0;
    public static final int DONE = 1;
    public static final int NAME_CATEGORY = 0;
    public static final int DEADLINE_CATEGORY = 1;
    public static final int TYPE_CATEGORY = 2;
    public static final int STATUS_CATEGORY = 3;
    private static final String TICK_SYMBOL = "\u2713"; // "✓"
    private static final String CROSS_SYMBOL = "\u2718"; // "✘"
    private static final String DATE_FORMATTER_PATTERN = "dd/MM/yyyy HHmm";

    private String name;
    private int status;

    /**
     * Represents a single Task object in TaskList. Newly created Tasks are
     * set to NOT_DONE by default.
     *
     * @param name The name of the Task.
     */
    Task(String name) {
        this.name = name;
        this.status = NOT_DONE;
    }

    /**
     * Returns a single character representing the icon for the task type.
     *
     * @return The task icon for the type of task.
     */
    protected abstract String getTypeSymbol();

    /**
     * Returns additional information that is passed into the task object.
     *
     * @return The additional information of the task.
     * @see Deadline
     * @see Event
     */
    public abstract String getAdditionalInfo();

    /**
     * Returns the additional info in a manner used for printing directly
     * to the UI.
     *
     * @return The additional information of the task in formatted style.
     */
    protected abstract String getAdditionalInfoForDisplay();

    public abstract String getStorageStringFormat();

    protected void setDone() {
        this.status = DONE;
    }

    protected void setNotDone() {
        this.status = NOT_DONE;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }

    private String getStatusSymbol() {
        if (status == DONE) {
            return TICK_SYMBOL;
        } else {
            return CROSS_SYMBOL;
        }
    }

    public boolean isAssociated(String keyword) {
        return name.contains(keyword)
                || getAdditionalInfo().contains(keyword);
    }

    public Calendar getDeadlineFromAdditionalInfo() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMATTER_PATTERN);
        sdf.setLenient(false);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(getAdditionalInfo()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }

    @Override
    public String toString() {
        return String.format("%s%s %s %s", getTypeSymbol(),
                getStatusSymbol(),
                name,
                getAdditionalInfoForDisplay());
    }


}
