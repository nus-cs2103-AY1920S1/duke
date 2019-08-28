package duke.task;

public class TodoTask  extends Task{
    TodoTask(String taskDetails) {
        super(taskDetails);
    }

    String saveInfo() {
        return "todo" + " " + taskDetails + System.getProperty("line.separator")
                + completed;
    }

    @Override
    public String toString() {
        if (this.completed) {
            return "[T][✓] " + taskDetails;
        } else {
            return "[T][✗] " + taskDetails;
        }
    }
}
