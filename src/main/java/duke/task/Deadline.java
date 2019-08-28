package duke.task;

import java.util.Date;

public class Deadline extends Task{
    private Date deadlineBy;

    Deadline(String taskDetails, Date deadlineBy) {
        super(taskDetails);
        this.deadlineBy = deadlineBy;
    }

    String saveInfo() {
        return "deadline" + " " + taskDetails + " /by " + TaskList.inputDateFormat.format(deadlineBy) + System.getProperty("line.separator")
                + completed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.completed) {
            sb.append("[D][✓] ");
        } else {
            sb.append("[D][✗] ");
        }
        sb.append(taskDetails);
        sb.append (" (");
        sb.append(TaskList.outputDateFormat.format(deadlineBy));
        sb.append(")");
        return sb.toString();
    }
}
