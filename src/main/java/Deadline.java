public class Deadline extends Tasks {
    private final String TAG = "D";
    private String date;

    public Deadline(String dets, String date) {
        super(dets);
        this.date = date;
    }

    public Deadline(String dets, String date, int status) {
        super(dets, status);
        this.date = date;
    }

    /**
     * Sets task status to done using the method in the super class.
     */
    public void doTask() {
        super.doTask();
    }

    @Override
    public String toString() {
        return TAG + " | " + super.getStatus() + " | " + super.getDetails() + " | " + date + "\n";
    }
}