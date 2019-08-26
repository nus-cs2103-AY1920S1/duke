import java.util.ArrayList;
import java.util.Iterator;

public class TaskList {
    ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int getSize() {
        return tasks.size();
    }

    public void displayAllTasks() {
        Iterator<Task> iter = tasks.iterator();
        int i = 1;
        while (iter.hasNext()) {
            Task task = iter.next();
            System.out.println(task.toString());
            i++;
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
        Ui.outputTaskAdded(task, this);
    }

    public void deleteTask(int index) {
        Task removedTask = tasks.remove(index - 1);
        Ui.outputTaskRemoved(removedTask, this);
    }

    public void markTaskAsDone(int index) {
        Task task = tasks.get(index - 1);
        task.markAsDone();
        Ui.outputTaskDone(task);
    }
}
