import java.util.Date;

class Event extends Task{
    private Date eventAt;
    Event(String taskDetails, Date eventAt) {
        super(taskDetails);
        this.eventAt = eventAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.completed) {
            sb.append("[E][✓] ");
        } else {
            sb.append("[E][✗] ");
        }
        sb.append(taskDetails);
        sb.append (" (");
        sb.append(ToDoList.outputDateFormat.format(eventAt));
        sb.append(")");
        return sb.toString();
    }
}
