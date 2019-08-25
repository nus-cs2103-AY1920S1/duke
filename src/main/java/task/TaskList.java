package task;

import exception.DukeIllegalStateException;
import exception.DukeIndexOutOfBoundsException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
        tasks.add(null); // leave index 0 unused for clarity.
    }

    public Task add(Task task) {
        tasks.add(task);
        return task;
    }

    public Task delete(int idx) throws DukeIndexOutOfBoundsException {
        if (idx <= 0 || idx >= tasks.size()) {
            throw new DukeIndexOutOfBoundsException(":'( OOPS!!! There's no such task index.");
        }
        return tasks.remove(idx);
    }

    public Task get(int idx) throws DukeIndexOutOfBoundsException {
        if (idx <= 0 || idx >= tasks.size()) {
            throw new DukeIndexOutOfBoundsException(":'( OOPS!!! There's no such task index.");
        }
        return tasks.get(idx);
    }

    public Task markAsDone(int idx) throws DukeIndexOutOfBoundsException {
        if (idx <= 0 || idx >= tasks.size()) {
            throw new DukeIndexOutOfBoundsException(":'( OOPS!!! There's no such task index.");
        }
        Task task = tasks.get(idx);
        task.markAsDone();
        return task;
    }

    public long count() {
        return tasks.size() - 1; // account for 1-indexing.
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }
}
