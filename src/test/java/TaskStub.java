import cs2103t.duke.task.Task;
import cs2103t.duke.task.TaskType;

public class TaskStub extends Task {
    public TaskStub() {
        super.taskType = getTaskType();
        super.description = getDescription();
        super.completed = false;
    }
    public String getDescription() {
        return "some description";
    }
    public boolean isCompleted() {
        return completed;
    }
    public TaskType getTaskType() {
        return TaskType.T;
    }
    //setter mtds
    public void setCompleted() {
        completed = true;
    }
}
