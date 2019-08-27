package duke.component;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public Task deleteTask(int i) throws DukeException {
        if (!indexIsValid(i)) {
            throw new DukeException("Index must be between 1 and the number of task added!");
        }

        return tasks.remove(i);
    }

    public Task getTask(int i) throws DukeException {
        if (!indexIsValid(i)) {
            throw new DukeException("Index must be between 1 and the number of task added!");
        }

        return tasks.get(i);
    }

    public int size() {
        return tasks.size();
    }

    public void forEach(Consumer<Task> c) {
        tasks.forEach(c);
    }

    public ListIterator<Task> listIterator() {
        return tasks.listIterator();
    }

    private boolean indexIsValid(int index) {
        return index >= 0 && index < tasks.size();
    }
}
