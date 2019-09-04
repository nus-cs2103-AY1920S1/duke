public class Deadline extends Task {
    protected String by;
    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
    @Override
    public String getStorageString(){
        return "D | " + super.isDone + " | " + taskName + " | " + by;
    }
}