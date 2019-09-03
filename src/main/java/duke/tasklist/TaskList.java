package duke.tasklist;

import duke.exception.DukeException;
import duke.initials.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskArrayList;

    /**
     * Constructs an instance of TaskList
     * @param taskArrayList
     */
    public TaskList(ArrayList<Task> taskArrayList) throws DukeException {
        if (taskArrayList == null) {
            throw new DukeException("ArrayList is not loaded");
        }
        this.taskArrayList = taskArrayList;
    }

    /**
     * Constructs an instance of TaskList with no params
     */
    public TaskList() {
    }

    /**
     * Deletes a specific task in the list based on the index in the array
     * @param index an integer that will be deleted
     */
    public void delete(int index) {
        taskArrayList.remove(index);
    }

    /**
     * Adds a specific task into the list
     * @param task a specific Task to be added into the arraylist
     */
    public void add(Task task) {
        taskArrayList.add(task);
    }

    /**
     * Returns the taskArrayList that is an attribute of this specific TaskList Object
     * @return a ArrayList<Task>
     */
    public ArrayList<Task> getTaskArrayList() {
        return taskArrayList;
    }

}
