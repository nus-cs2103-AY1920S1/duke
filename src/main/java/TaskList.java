import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    Task deleteTask(int n) throws DukeException {
        if (n > tasks.size()) {
            throw new DukeException(" ☹ OOPS!!! Do not exist that task.");
        }
        Task removeItem = tasks.get(n - 1);
        tasks.remove(n - 1);
        return removeItem;
    }

    void addTask(Task task) {
        tasks.add(task);
    }

    Task doneTask(int n) throws DukeException {
        if (n > tasks.size()) {
            throw new DukeException(" ☹ OOPS!!! Do not exist that task.");
        }
        tasks.get(n - 1).markAsDone();
        return tasks.get(n - 1);
    }

    ArrayList<Task> getList() {
        return tasks;
    }
}
