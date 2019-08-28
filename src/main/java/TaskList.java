import java.util.LinkedList;

public class TaskList {
    private LinkedList<Task> tasks;

    public TaskList() {
        this.tasks = new LinkedList<Task>();
    }

    public TaskList(LinkedList<Task> tasks) {
        this.tasks = tasks;
    }

    public LinkedList<Task> getListOfTasks() {
        return tasks;
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    public void deleteTask(int taskIndex) {
        tasks.remove(taskIndex);
    }
}
