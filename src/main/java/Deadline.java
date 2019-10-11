import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Deadline extends Tasks {
    private final String TAG = "D";
    private String outputDate;
    private Date date;
    private static SimpleDateFormat FORMATOUT = new SimpleDateFormat("dd MMMMM yyyy ha");

    public Deadline(String dets, Date date) {
        super(dets, "D");
        this.date = date;
        this.outputDate = FORMATOUT.format(date);
    }

    public Deadline(String dets, Date date, int status) {
        super(dets, status);
        this.date = date;
        this.outputDate = FORMATOUT.format(date);

    }

    /**
     * Snoozes the task for 7 days when executed
     */
    void snooze() {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 7);
        date = c.getTime();
        outputDate = FORMATOUT.format(date);
    }

    /**
     * Sets task status to done using the method in the super class.
     */
    public void doTask() {
        super.doTask();
    }

    @Override
    public String toString() {
        return TAG + " | " + super.getStatus() + " | " + super.getDetails() + " | by " + outputDate + "\n";
    }
}