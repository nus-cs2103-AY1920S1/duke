package duke.task;

import duke.DukeException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int indexOf(Task t) {
        return tasks.indexOf(t);
    }

    public Task get(int index) throws DukeException {
        if (isValidIndex(index)) {
            return tasks.get(index - 1);
        } else {
            throw new DukeException("\u2754 OOPS!!! There is no task at index " + index + ".");
        }
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public void remove(Task t) throws DukeException {
        if (tasks.contains(t)) {
            tasks.remove(t);
        } else {
            throw new DukeException("\u2754 OOPS!!! There is no such task in taskList.");
        }
    }

    public Task remove(int index) throws DukeException {
        Task task;
        if (isValidIndex(index)) {
            task = tasks.remove(index - 1);
        } else {
            throw new DukeException("\u2754 OOPS!!! There is no such task in taskList.");
        }
        return task;
    }

    private boolean isValidIndex(int index) {
        return index >= 1 && index <= tasks.size();
    }
}
