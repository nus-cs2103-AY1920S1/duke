package duke.task;

import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;
import duke.exception.*;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> loadedTasks) {
        this.tasks = loadedTasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public String delete(int index) throws DukeException {
        try {
            return this.tasks.remove(index).toString();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public void markAsDone(int taskNumber) {
        this.tasks.get(taskNumber).markedAsDone();
    }

    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }
}