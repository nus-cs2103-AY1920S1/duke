package duke.task;

import duke.exception.DukeException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import java.lang.IndexOutOfBoundsException;

public class TaskList implements Serializable {
    private List<Task> tasks; // List of all tasks

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList(TaskList taskList) {
        this.tasks = taskList.tasks;
    }

    /*
     * Given an index, deletes the corresponding task from the stored list
     * in Duke.
     *
     * @param taskIndex index of task to be deleted.
     * @throws DukeException if index is invalid or refers to a non-existent task.
     */
    public Task delete(int taskIndex) throws DukeException {
        try {
            taskIndex--; // convert to zero-indexing
            return this.tasks.remove(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Hey! There's no such task!");
        }
    }

    public Task at(int taskIndex) throws DukeException {
        try {
            taskIndex--; // convert to zero-indexing
            return this.tasks.get(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Hey! There's no such task!");
        }
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public int getSize() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task task : this.tasks) {
            sb.append(i++);
            sb.append(". ");
            sb.append(task);
            sb.append("\n");
        }
        return sb.toString();
    }
}
