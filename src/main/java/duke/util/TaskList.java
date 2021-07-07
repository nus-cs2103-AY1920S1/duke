package duke.util;

import duke.task.Task;
import java.util.ArrayList;
import java.util.List;

/** Contains the {@link TaskList} and operations to manage {@link Task} in the list.
 *  Operations include adding, deleting, getting by index, setting {@link Task}
 */
public class TaskList {

    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns true if {@link TaskList} is empty.
     *
     * @return Returns true if {@link TaskList} is empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Add a {@link Task} into the list.
     *
     * @param task Task to be added into the list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Remove a {@link Task} from the {@link TaskList} using its index as displayed.
     *
     * @param inputNum Index of item to remove as displayed to user.
     * @return The {@link Task} deleted.
     */
    public Task delete(int inputNum) {
        return tasks.remove(inputNum - 1);
    }

    /**
     * Returns a {@link Task} from the {@link TaskList} by its index (starting from 0).
     *
     * @param index Index of item in the list
     * @return {@link Task} denoted by index.
     */
    public Task getByIndex(int index) {
        return tasks.get(index);
    }

    /**
     * Returns number of {@link Task} in the list.
     *
     * @return Integer of total number of tasks in the {@link TaskList}.
     */
    public int listSize() {
        return tasks.size();
    }

    /**
     * Returns {@link TaskList} of {@link Task} which description contains
     * the user's key string.
     *
     * @param keyString User's input key string.
     */
    public TaskList getRelevantList(String keyString) {
        TaskList relevantList = new TaskList();
        for (int i = 0; i < this.listSize(); i++) {
            if (this.getByIndex(i).containsKeyString(keyString)) {
                relevantList.add(this.getByIndex(i));
            }
        }
        return relevantList;
    }

    /**
     * Returns {@link TaskList} of {@link Task} ordered by priority.
     */
    public TaskList getSortedList() {
        ArrayList<Task> sortedList = new ArrayList<>();
        for (int i = 0; i < this.listSize(); i++) {
            if (this.getByIndex(i).getPriority() != -1) {
                sortedList.add(this.getByIndex(i));
            }
        }
        sortedList.sort((t1,t2) -> t1.getPriority() - t2.getPriority());

        return new TaskList(sortedList);
    }
}
