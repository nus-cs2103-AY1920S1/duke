package duke;

import exception.DukeException;
import task.Task;

import java.util.ArrayList;

/**
 * Represents a list of Tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a TaskList which stores Tasks.
     * @param tasks an Arraylist of Task.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws DukeException {
        tasks.remove(index);
    }

    public void doneTask(int index) throws DukeException {
        tasks.get(index).markAsDone();
    }

    public String toString() {
        String res;
        if(tasks.size() == 0) {
            return "You have no tasks in the list.";
        } else {
            res = "You have " + tasks.size() + " task"
                    + (tasks.size() == 1 ? " " : "s ") + "in the list.";
        }
        for (int i = 0; i < tasks.size(); i++) {
            res += ("\n" + (i + 1) + "." + tasks.get(i));
        }
        return res;
    }
}
