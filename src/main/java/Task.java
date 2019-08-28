import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public abstract class Task {
    public static String tick = "\u2713";
    public static String cross = "\u2717";
    public static int totalNumOfTasks = 0;
    public static List<Task> taskList = new ArrayList<>();

    protected String description;
    protected boolean completed;
    protected int id;

    protected TaskType taskType;

    //getter mtds
    public String getDescription() {
        return this.description;
    }
    public int getId() {
        return this.id;
    }
    public boolean isCompleted() {
        return this.completed;
    }
    public TaskType getTaskType() { return this.taskType; }
    //setter mtds
    public void setCompleted() {
        this.completed = true;
    }


    public static Task createTask(TaskType taskType, String description) {
        Task t = null;
        switch (taskType) {
            case T:
            t = Todo.create(description);
            break;
            case D:
            t = Deadline.create(description);
            break;
            case E:
            t = Event.create(description);
            break;
            default:
        }
        return t;
    }


    @Override
    public String toString() {
        String checked;
        if (this.completed) {
            checked = tick;
        } else {
            checked = cross;
        }
        return String.format("[%s][%s] %s", this.taskType, checked, this.description);
    }
}
