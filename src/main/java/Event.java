public class Event extends Tasks {
    private final String TAG = "E";
    private String dateTime;

    public Event(String dets, String dateTime) {
        super(dets);
        this.dateTime = dateTime;
    }

    public Event(String dets, String dateTime, int status) {
        super(dets, status);
        this.dateTime = dateTime;
    }

    /**
     * Sets task status to done using the method in the super class.
     */
    public void doTask() {
        super.doTask();
    }

    @Override
    public String toString() {
        return TAG + " | " + super.getStatus() + " | " + super.getDetails() + " | " + dateTime + "\n";
    }
}