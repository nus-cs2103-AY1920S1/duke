package duke.task;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;

    /**
     * Constructor
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Adds new task (without date) into list
     * @param task - task to add
     */
    public void add(Task task) {
        assert task != null;
        this.list.add(task);
    }

    /**
     * Marks task at given index as done
     * @param idx - Index of task in list
     */
    public void done(int idx) {
        assert (idx >= 0 && idx < this.list.size());
        this.list.get(idx).markDone();
    }

    /**
     * Marks task at given index as done
     * @param idx - Index of task in list
     */
    public void delete(int idx) {
        assert (idx >= 0 && idx < this.list.size());
        this.list.remove(idx);
    }

    /**
     * Returns task at index idx
     * @param idx - index of task
     * @return duke.task.Task at index idx
     */
    public Task get(int idx) {
        assert (idx >= 0 && idx < this.list.size());
        return this.list.get(idx);
    }

    /**
     * Returns the current size of list
     * @return size of list
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Prints out contents of list
     */
    public String getPrintListMessage() {
        String response = "Here are the tasks in your list:\n";
        for (int i = 0; i < this.list.size(); i++) {
            response += (i+1) + "." + this.list.get(i) + "\n";
        }
        return response;
    }
}
