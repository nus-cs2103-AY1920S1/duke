package duke;

import java.util.ArrayList;
import tasks.Task;

/**
 * TaskList class stores the list of Tasks.
 */
public class TaskList {
    /** The list of Tasks */
    private ArrayList<Task> taskLst;

    /**
     * TaskList constructor that takes no arguments
     * and instantiates an empty list of Tasks.
     */
    public TaskList() {
        this.taskLst = new ArrayList<Task>();
    }

    /**
     * TaskList constructor that takes in a list
     * of Tasks and stores it in taskLst.
     *
     * @param taskLst the list of Tasks.
     */
    public TaskList(ArrayList<Task> taskLst) {
        this.taskLst = taskLst;
    }

    /**
     * Returns the list of Tasks stored by this TaskList object.
     *
     * @return ArrayList of Tasks.
     */
    public ArrayList<Task> getTaskLst() {
        return taskLst;
    }

}
