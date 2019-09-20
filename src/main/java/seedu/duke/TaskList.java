package seedu.duke;

import java.util.ArrayList;

/**
 * Class for storing Tasks in an ArrayList.
 */
public class TaskList {
    protected static ArrayList<Task> list;

    /** Constructor for blank TaskList.*/
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**Constructor for TaskList using existing ArrayList of Tasks.*/
    public TaskList(ArrayList<Task> l) {
        this.list = l;
    }
}
