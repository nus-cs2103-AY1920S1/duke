package duke.util;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a TaskList class that encapsulates all user's tasks.
 */
public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getTotalTask() {
        return tasks.size();
    }

    public Task getTaskAt(int index) {
        return tasks.get(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task removeTaskAt(int index) {
        return tasks.remove(index);
    }

    /**
     * Indicates whether the newly added task is already in the list.
     *
     * @param task  a <code>Task</code> that the user intends to add
     * @return      <code>true</code> if this task list has a same task
     *              <code>false</code> otherwise.
     */
    public boolean contains(Task task) {
        for (Task t : tasks) {
            if (t.equals(task)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Compares two task list by sequentially comparing each task.
     * This method is mainly for testing purpose.
     *
     * @param obj a <code>TaskList</code> object to be compared
     * @return    <code>true</code> if this task list has same tasks in the same order as another task list;
     *            <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        //@@author ZhangHuafan-reused
        //Reused from https://www.javaworld.com/article/3305792/comparing-java-objects-with-equals-and-hashcode.html
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        //@@author

        TaskList another = (TaskList) obj;
        if (another.getTotalTask() != this.getTotalTask()) {
            return false;
        }
        for (int i = 0; i < getTotalTask(); i++) {
            if (!another.getTaskAt(i).equals(this.getTaskAt(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String fullList = "";
        for (int i = 1; i <= getTotalTask(); i++) {
            fullList = fullList + i + ". " + this.getTaskAt(i - 1) + "\n";
        }
        return fullList;
    }
}
