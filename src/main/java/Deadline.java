import java.util.Date;

class Deadline extends Task{
    private Date deadlineBy;

    Deadline(String taskDetails, Date deadlineBy) {
        super(taskDetails);
        this.deadlineBy = deadlineBy;
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
        sb.append(ToDoList.outputDateFormat.format(deadlineBy));
        sb.append(")");
        return sb.toString();
    }
}
