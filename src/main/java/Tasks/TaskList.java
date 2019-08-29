package Tasks;

import Exceptions.DukeException;
import Tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int getListSize() {
        return tasks.size();
    }

    public String getTaskString(int index) {
        return tasks.get(index).toString();
    }

    public void addNewTask(Task task) throws DukeException {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public void markAsDone(int taskIndex) {
        tasks.get(taskIndex).markAsDone();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
