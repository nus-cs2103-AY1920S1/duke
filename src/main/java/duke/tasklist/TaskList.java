package duke.tasklist;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    /**
     * Constructor method to create blank TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Getter method to retrieve number of tasks in tasklist.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Method to add a task to the tasklist.
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Method to retrieve a task given an index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Method to get remove a task given an index.
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }
}
