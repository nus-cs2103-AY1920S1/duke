public class Deadline extends Task {
    protected String deadlineTime;
    final String TASK_TYPE = "[D]";

    public Deadline(String description, String by) {
        super(description);
        this.deadlineTime = by;
    }

    public String getTime(){
        return deadlineTime;
    }

    public String getDescription(){
        return this.description + "|" + this.deadlineTime;
    }

    public String getType() {
        return TASK_TYPE;
    }

    @Override
    public String toString() {
        return  TASK_TYPE + super.getStatusIcon() + " " + super.toString() + " (by: " + deadlineTime + ")";
    }
}
