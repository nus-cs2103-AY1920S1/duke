import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    //Lists out all the tasks in Duke
    public void list() throws IllegalArgumentException {
        if (tasks.size() == 0) {
            throw new IllegalArgumentException("Nothing found in list");
        }
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void done(int number) throws IndexOutOfBoundsException {
        if (number > tasks.size() || number <= 0) {
            throw new IndexOutOfBoundsException("The task number does not exist.");
        }
        Task task = tasks.get(number - 1);
        task.setDone();
    }

    public void delete(int number) throws IndexOutOfBoundsException {
        if (number > tasks.size() || number <= 0) {
            throw new IndexOutOfBoundsException("The task number does not exist.");
        }
        Task task = tasks.get(number - 1);
        tasks.remove(number - 1);
    }
}
