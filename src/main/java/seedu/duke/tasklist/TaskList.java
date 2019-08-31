package seedu.duke.tasklist;

import seedu.duke.task.Task;
import java.util.ArrayList;

/**
 * TaskList class contains the list of Tasks (stored as an ArrayList) and methods to add, delete Tasks.
 */
public class TaskList {
    private ArrayList<Task> listOfTasks;

    /**
     * Constructor for TaskList class. Returns a TaskList object.
     *
     * @param taskList An ArrayList of Task objects.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.listOfTasks = taskList;
    }

    /**
     * Returns the number of Task objects currently stored in the list.
     *
     * @return Number of Task objects.
     */
    public int getSize(){
        return listOfTasks.size();
    }

    /**
     * Returns the object Task at the specified int index.
     *
     * @param i Integer index.
     * @return Task object which is located at index i.
     */
    public Task getTask(int i){
        return listOfTasks.get(i);
    }

    /**
     * Adds a new Task object into the list.
     *
     * @param newTask Task object that needs to be added into the list.
     */
    public void addTask(Task newTask){
        this.listOfTasks.add(newTask);
    }

    /**
     * Deletes a Task object from a specified index.
     *
     * @param i Integer index of where the Task object (to be deleted) is in.
     */
    public void deleteTask(int i){
        this.listOfTasks.remove(i);
    }

}
