public class Todo extends Tasks {
    private final String TAG = "T";

    public Todo(String dets) {
        super(dets);
    }

    public void finishTask() {
        super.finishTask();
    }


    @Override
    public String toString() {
        return TAG + " | " + super.getStatus() + " | " + super.getDetails();
    }
}