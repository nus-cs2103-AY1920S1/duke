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

    /*
    //constructors
    private Task() {
        this.description = "";
        this.completed = false;
        this.id = 0;
        this.taskType = "";
    }

    private Task(String description, boolean completed, int id) {
        this.description = description;
        this.completed = completed;
        this.id = id;
    }

    //methods
    public static Optional<Task> createTask(String description, boolean completed) {
        if (description.equals("")) {
            return Optional.empty();
        } else {
            totalNumOfTasks++;
            Task newTask = new Task(description, false, totalNumOfTasks);
            taskList.add(newTask);
            return Optional.of(newTask);
        }
    }
     */

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
    //setter mtds
    public void setCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        String checked;
        if (this.completed) {
            checked = "\u2713";
        } else {
            checked = "\u2717";
        }
        return String.format("[%s][%s]%s", this.taskType, checked, this.description);
    }
}
