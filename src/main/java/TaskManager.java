import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public void addTask(String task) {
        tasks.add(new Task(task));
        System.out.println("added: " + task);
    }

    public void printTasks() {
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}
