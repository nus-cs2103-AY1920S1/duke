import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public Task markAsDone(int index) throws DukeException {
        try {
            tasks.get(index).isDone = true;
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(" ☹ OOPS!!! There is no task number " + (index + 1));
        }

    }

    public Task removeTask(int index) throws DukeException {
        try {
            return tasks.remove(index);

        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(" ☹ OOPS!!! There is no task number " + (index + 1));
        }

    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }
}
