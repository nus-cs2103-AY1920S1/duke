public class Event extends Tasks {
    private final String TAG = "E";
    private String dateTime;

    public Event(String dets, String dateTime) {
        super(dets);
        this.dateTime = dateTime;
    }

    public void finishTask() {
        super.finishTask();
    }

    @Override
    public String toString() {
        return TAG + " | " + super.getStatus() + " | " + super.getDetails() + " | " + dateTime;
    }
}