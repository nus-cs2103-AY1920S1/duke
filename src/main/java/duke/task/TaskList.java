package duke.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TaskList {
    private List<Task> list;

    /**
     * Constructor.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Adds new task (without date) into list.
     * @param task - task to add
     */
    public void add(Task task) {
        assert task != null;
        this.list.add(task);
    }

    /**
     * Marks task at given index as done.
     * @param idx - Index of task in list
     */
    public void done(int idx) {
        assert (idx >= 0 && idx < this.list.size());
        this.list.get(idx).markDone();
    }

    /**
     * Marks task at given index as done.
     * @param idx - Index of task in list
     */
    public void delete(int idx) {
        assert (idx >= 0 && idx < this.list.size());
        this.list.remove(idx);
    }

    /**
     * Returns task at index idx.
     * @param idx - index of task
     * @return duke.task.Task at index idx
     */
    public Task get(int idx) {
        assert (idx >= 0 && idx < this.list.size());
        return this.list.get(idx);
    }

    /**
     * Returns the current size of list.
     * @return size of list
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Prints out contents of list.
     * @return String containing list contents
     */
    public String getPrintListMessage() {
        String response = "Here are the tasks in your list:\n";
        AtomicInteger index = new AtomicInteger();
        String[] printedList = new String[this.list.size()];
        response += Arrays.stream(printedList)
                          .map(str -> {
                              int idx = index.getAndIncrement();
                              return (idx + 1) + "." + this.list.get(idx) + "\n";
                          })
                          .collect(Collectors.joining());
        return response;
    }

    /**
     * Returns a new tasklist containing a copy of all the same values.
     */
    public TaskList getCopyTaskList() {
        TaskList newList = new TaskList();
        for (int i = 0; i < this.size(); i++) {
            newList.add(this.list.get(i).getTaskCopy());
        }
        return newList;
    }
}
