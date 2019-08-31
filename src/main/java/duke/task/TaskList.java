package duke.task;

import duke.exception.DukeException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import java.lang.IndexOutOfBoundsException;

public class TaskList implements Serializable {
    private List<Task> list; // List of all tasks

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public TaskList(TaskList taskList) {
        this.list = taskList.list;
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
            return this.list.remove(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Hey! There's no such task!");
        }
    }

    public Task at(int taskIndex) throws DukeException {
        try {
            taskIndex--; // convert to zero-indexing
            return this.list.get(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Hey! There's no such task!");
        }
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public int getSize() {
        return this.list.size();
    }

    public TaskList getTaskSubsetMatching(String searchWord) {
        List<Task> resultList = new ArrayList<>();
        this.list.forEach((task) -> {
            if (task.getDescription().contains(searchWord)) {
                resultList.add(task);
            }
        });
        return new TaskList(resultList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task task : this.list) {
            sb.append(i++);
            sb.append(". ");
            sb.append(task);
            sb.append("\n");
        }
        return sb.toString();
    }
}
