/**
 * Simulates Duke's task list and handles operations such as
 * adding, removing and marking tasks as done.
 */

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Creates a TaskList instance with the appropriate attributes.
     */
    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    /**
     * Returns the size of a task list.
     * @return The size of the task list.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Returns the array list of a task list.
     * @return The array list of a task list.
     */
    public ArrayList getArrayList() {
        return taskList;
    }

    /**
     * Adds a task to the task list.
     * @param task The task to be added to the task list.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task from the task list.
     * @param task The task to be deleted from the task list.
     */
    public void deleteTask(Task task) {
        taskList.remove(task);
    }

    /**
     * Sets a task as done from the task list.
     * @param task The task to be set as done from the task list.
     */
    public void setTaskAsDone(Task task) {
        task.setDone();
    }

    /**
     * Lists all the tasks in the task list.
     */
    public void listTasks() {
        int indexNumber = 1;

        for(int i = 0; i < taskList.size(); i++) {
            System.out.println((indexNumber++) + ". " + taskList.get(i));
        }
    }

}
