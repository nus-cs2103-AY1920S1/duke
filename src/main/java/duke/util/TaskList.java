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

    /**
     * Copies all the tasks from another task list to this task list.
     *
     * @param another a <code>TaskList</code> object containing some tasks
     */
    public void addAll(TaskList another) {
        for (int i = 0; i < another.getTotalTask(); i++) {
            tasks.add(another.getTaskAt(i));
        }
    }

    public Task removeTaskAt(int index) {
        return tasks.remove(index);
    }

    /**
     * Compares two task list by sequentially comparing each task.
     *
     * @param obj a <code>TaskList</code> object to be compared
     * @return <code>true</code> if this task list has same tasks in the same order as another task list;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TaskList) {
            TaskList another = (TaskList) obj;
            if (another.getTotalTask() != this.getTotalTask()) {
                return false;
            } else {
                for (int i = 0; i < this.getTotalTask(); i++) {
                    if (!this.getTaskAt(i).equals(another.getTaskAt(i))) {
                        return false;
                    }
                }
                return true;
            }
        } else {
            return false;
        }
    }
}
