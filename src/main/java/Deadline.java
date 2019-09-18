import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Tasks {
    private final String TAG = "D";
    private String outputDate;
    private Date date;
    private static SimpleDateFormat FORMATOUT = new SimpleDateFormat("dd MMMMM yyyy ha");

    public Deadline(String dets, Date date) {
        super(dets);
        this.date = date;
        this.outputDate = FORMATOUT.format(date);
    }

    public Deadline(String dets, Date date, int status) {
        super(dets, status);
        this.date = date;
        this.outputDate = FORMATOUT.format(date);

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