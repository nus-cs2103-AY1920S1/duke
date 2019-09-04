public class Deadline extends Tasks {
    private final TAG = "D";
    private String date;

    public Deadline(String dets, String date) {
        super(dets);
        this.date = date;
    }

    public void finishTask() {
        super.finishTask();
    }

    @Override
    public String toString() {
        return TAG + " | " + super.getStatus() + " | " + super.getDetails() + " | " + date;
    }
}