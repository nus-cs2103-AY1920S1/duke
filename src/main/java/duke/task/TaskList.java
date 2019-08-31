package duke.task;
import duke.exception.DukeException;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task deleteTask(int n) throws DukeException {
        if (n > tasks.size()) {
            throw new DukeException(" ☹ OOPS!!! Do not exist that task.");
        }
        Task removeItem = tasks.get(n - 1);
        tasks.remove(n - 1);
        return removeItem;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task doneTask(int n) throws DukeException {
        if (n > tasks.size()) {
            throw new DukeException(" ☹ OOPS!!! Do not exist that task.");
        }
        tasks.get(n - 1).markAsDone();
        return tasks.get(n - 1);
    }

    public ArrayList<Task> getList() {
        return tasks;
    }
}
