import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Event extends Tasks {
    private final String TAG = "E";
    private String dateOut;
    private Date date;
    private static SimpleDateFormat FORMATOUT = new SimpleDateFormat("dd MMMMM yyyy ha");

    public Event(String dets, Date date) {
        super(dets, "E");
        this.date = date;
        this.dateOut = FORMATOUT.format(date);
    }

    public Event(String dets, Date date, int status) {
        super(dets, status);
        this.date = date;
        this.dateOut = FORMATOUT.format(date);
    }

    /**
     * Snoozes the task for 7 days when executed
     */
    void snooze() {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 7);
        date = c.getTime();
        dateOut = FORMATOUT.format(date);
    }

    /**
     * Sets task status to done using the method in the super class.
     */
    public void doTask() {
        super.doTask();
    }

    @Override
    public String toString() {
        return TAG + " | " + super.getStatus() + " | " + super.getDetails() + " | at " + dateOut+ "\n";
    }
}