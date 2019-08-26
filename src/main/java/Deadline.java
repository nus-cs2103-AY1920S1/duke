public class Deadline extends Task {

    public Deadline(String taskName, String by) {
        super(taskName);
        this.taskType = TypeOfTask.DEADLINE;

        if ( by.contains("(by: ") ) this.details = by;
        else this.details = "(by: " + by + ")";
    }

    public Deadline(String taskName, String by, boolean isCompleted) {
        super(taskName, isCompleted);
        this.taskType = TypeOfTask.DEADLINE;

        if ( by.contains("(by: ") ) this.details = by;
        else this.details = "(by: " + by + ")";
    }



}
