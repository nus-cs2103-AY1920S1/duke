public class Todo extends Tasks {
    private final String TAG = "T";

    public Todo(String dets) {
        super(dets);
    }

    public Todo(String dets, int status) {
        super(dets, status);
    }

    /**
     * Sets task status to done using the method in the super class.
     */
    public void doTask() {
        super.doTask();
    }


    @Override
    public String toString() {
        return TAG + " | " + super.getStatus() + " | " + super.getDetails() + "\n";
    }
}