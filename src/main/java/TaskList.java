package duke;

import duke.task.Task;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * TaskList class contains Tasks to be done.
 */
public class TaskList extends ArrayList<Task> implements Serializable {
    /**
     * Adds Task to TaskList.
     * 
     * @param task Task to be added to TaskList.
     * @return Boolean if Task is successfully added.
     */
    public boolean addTask(Task task) {
        return this.add(task);
    }

    /**
     * Returns Task of the specified id.
     * 
     * @param itemId Id of the Task.
     * @return Task of the specified itemId.
     */
    public Task get(int itemId) {
        return super.get(itemId - 1);
    }

    /**
     * Returns Task that has been removed from TaskList.
     * 
     * @param itemId Id of the Task to be removed.
     * @return Task which has been removed from TaskList.
     */
    public Task remove(int itemId) {
        return super.remove(itemId - 1);
    }

    /**
     * Marks the Task of the specified id as done.
     * 
     * @param itemId Id of the Task to be marked as done.
     */
    public void markAsDone(int itemId) {
        super.get(itemId - 1).markAsDone();
    }

    /**
     * Returns String of Tasks contained in TaskList.
     * 
     * @return String of Tasks contained in TaskList.
     */
    public String toString() {
        StringBuffer listBuffer = new StringBuffer();
        int len = this.size();
        for (int i = 1; i <= len; i++) {
            listBuffer.append(i + "."
                    + this.get(i).toString()
                    + "\n");
        }
        return listBuffer.toString();
    }
}