import java.time.LocalDateTime;

public class Deadline extends Task {
<<<<<<< HEAD
    protected String deadlineTime;
    final String TASK_TYPE = "[D]";
=======
    protected LocalDateTime by;
>>>>>>> branch-Level-8

    public Deadline(String description, LocalDateTime by) {
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
<<<<<<< HEAD
        return  TASK_TYPE + super.getStatusIcon() + " " + super.toString() + " (by: " + deadlineTime + ")";
=======
        return "[D]" + super.getStatusIcon() + " " + super.toString() + " (by: " + DateTimeHelper.formatOutput(by)  + ")";
>>>>>>> branch-Level-8
    }
}
