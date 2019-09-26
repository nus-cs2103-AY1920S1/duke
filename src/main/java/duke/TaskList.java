package duke;

import java.util.ArrayList;
import java.util.Collections;

import tasks.Task;

public class TaskList {
    ArrayList<Task> list;

    /**
     * This is a constructor for duke.TaskList.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * This method is used to return the task list.
     *
     * @return the task list
     */
    public ArrayList<Task> getList() {
        return list;
    }

    public void sort() {
        Collections.sort(list);
    }

    /**
     * This method is used to add a task to the list.
     *
     * @param taskToAdd the task to add
     */
    public void addTask(Task taskToAdd) {
        assert taskToAdd != null;
        list.add(taskToAdd);
    }

    /**
     * This method is used to delete a task to the list.
     *
     * @param taskNumToDelete the task number to delete
     * @return Nothing
     */
    public Task deleteTask(int taskNumToDelete) {
        return list.remove(taskNumToDelete - 1);
    }

    /**
     * This method is used to mark a task as done.
     *
     * @param taskNumToMark the task number to mark
     */
    public void doneTask(int taskNumToMark) {
        Task t = list.get(taskNumToMark - 1);
        t.markAsDone();
    }

    public Task getTask(int taskNumToGet) {
        Task t = list.get(taskNumToGet - 1);
        return t;
    }

    public int getDoneTaskCount() {
        int count = 0;
        for (Task task : list) {
            if (task.isDone()) {
                count++;
            }
        }
        return count;
    }

    public int getTotalTaskCount() {
        return list.size();
    }
}
