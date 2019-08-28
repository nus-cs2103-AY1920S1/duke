import java.util.ArrayList;

/**
 * contains the task list
 * - contains operations for commands to list
 * */
public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task updateTask(int num) {
        Task currTask = tasks.get(num - 1);
        currTask.markAsDone();
        return currTask;
    }

    public Task deleteTask(int num) {
        Task currTask = tasks.get(num - 1);
        tasks.remove(num - 1);
        return currTask;
    }

    public ArrayList<Task> searchTasks(String command) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(command)) {
                results.add(task);
            } // else ignore
        }
        return results;
    }

}