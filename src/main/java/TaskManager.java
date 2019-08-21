import java.util.ArrayList;

public class TaskManager {
    private ArrayList<String> tasks = new ArrayList<String>();

    public void addTask(String task) {
        tasks.add(task);
        System.out.println("added: " + task);
    }

    public void printTasks() {
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}
