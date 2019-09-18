import java.util.Date;
import java.text.SimpleDateFormat;

public class Event extends Tasks {
    private final String TAG = "E";
    private String dateOut;
    private Date date;
    private static SimpleDateFormat FORMATOUT = new SimpleDateFormat("dd MMMMM yyyy ha");

    public Event(String dets, Date date) {
        super(dets);
        this.date = date;
        this.dateOut = FORMATOUT.format(date);
    }

    public Event(String dets, Date date, int status) {
        super(dets, status);
        this.date = date;
        this.dateOut = FORMATOUT.format(date);
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