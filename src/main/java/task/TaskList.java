package task;

import exception.InvalidIndexException;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> toList() {
        return tasks;
    }

    /** Marks a task as done */
    public Task done(int taskIndex) throws InvalidIndexException {
        try {
            Task task = tasks.get(taskIndex - 1);
            task.setDone(true);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("OOPS!!! I can't do that, please gimme a valid task ID mate.");
        }
    }

    /** Deletes a task from the list permanently */
    public Task delete(int taskIndex) throws InvalidIndexException {
        try {
            Task task = tasks.remove(taskIndex - 1);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("OOPS!!! I can't do that, please gimme a valid task ID mate.");
        }
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void add(Task task) {
        tasks.add(task);
    }
}
