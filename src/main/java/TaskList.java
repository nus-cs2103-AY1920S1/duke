import java.util.ArrayList;
import java.util.Iterator;

public class TaskList implements Iterable<Task> {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    public int size() {
        return tasks.size();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(String taskId) throws DukeException {
        try {
            return tasks.get(Integer.parseInt(taskId) - 1);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task ID input!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task not found!");
        }
    }

    public Task remove(String taskId) throws DukeException {
        try {
            return tasks.remove(Integer.parseInt(taskId) - 1);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task ID input!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task not found!");
        }
    }
}
