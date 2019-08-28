package duke;

import java.util.ArrayList;
import duke.task.Task;
import duke.exception.DukeException;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task get(int taskId) throws DukeException {
        try {
            return tasks.get(taskId - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(
                    String.format("Task No.%d is not present in your list. Please enter a valid task ID.", taskId));
        }
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int taskId) throws DukeException {
        Task task = get(taskId);
        tasks.remove(task);
        return task;
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public TaskList filter(String keyWord) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyWord)) {
                result.add(task);
            }
        }
        return new TaskList(result);
    }
}