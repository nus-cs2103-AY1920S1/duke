import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    private static Task[] tasks;

    public TaskList(Task[] tasks) {
        this.tasks = tasks;
    }

    public Task[] getTasks() {
        return tasks;
    }

    public static void add(Task task, int index) {
        tasks[index] = task;
    }

    public static Task delete(int indextodel) {
        ArrayList<Task> taskarraylist = new ArrayList<>(Arrays.asList(tasks));
        Task removed = taskarraylist.remove(indextodel - 1);

        tasks = new Task[100];
        for (int i = 0; i < taskarraylist.size(); i++) {
            tasks[i] = taskarraylist.get(i);
        }
        TaskList.updateTasks(tasks);

        return removed;
    }

    public static Task get(int index) {
        return tasks[index];
    }

    public static void updateTasks(Task[] taskarr) {
        tasks = taskarr;
    }
}
