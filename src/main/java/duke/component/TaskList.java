package duke.component;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class store the task arraylist and allow
 * update, modify, delete and add of task by user.
 *
 * @author TeoShyanJie
 *
 */
public class TaskList {
    /** Array list of task. */
    private List<Task> task;

    /** Total number of task. */
    private int itemNo;

    /**
     * TaskList Constructor enter by user.
     * @param task List of task.
     */
    public TaskList(List<Task> task) {
        this.task = task;
        itemNo = task.size();
    }

    /**
     * List of task.
     */
    public TaskList() {
        this.task = new ArrayList<>();
    }

    /**
     * To get the total number of task.
     * @return The total number of task.
     */
    public int getItemNo() {
        return itemNo;
    }

    /**
     * To the set total number of item.
     * @param itemNo The number of item.
     */
    public void setItemNo(int itemNo) {
        this.itemNo = itemNo;
    }

    /**
     * To get the list of task.
     * @return The number task.
     */
    public List<Task> getTask() {
        return task;
    }

}