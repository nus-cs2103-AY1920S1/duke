import java.util.LinkedList;

public class TaskList {
    public LinkedList<Task> allTasks;

    public TaskList(LinkedList<Task> allTasks) {
        this.allTasks = allTasks;
    }

    public TaskList() {
        this.allTasks = new LinkedList<>();
    }

}
