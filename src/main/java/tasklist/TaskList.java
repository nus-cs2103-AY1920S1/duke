package duke.tasklist;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents a task list
 * Contains functions to interact with task list
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private int taskCount;

    // Default constructor
    public TaskList() {
        taskList = new ArrayList<>();
        taskCount = 0;
    }

    // Non-default constructor for initial loading
    public TaskList(ArrayList<Task> list) {
        this.taskList = list;
        this.taskCount = list.size();
    }

    /** Add new task to task list
     *
     * @param task To add to the task list.
     */
    public void addNewTask(Task task) {
        taskList.add(task);
    }

    /** Increase task count of task list.
     */
    public void increaseTaskCount() {
        taskCount++;
    }

    /** Get task count of task list.
     *
     * @return int Number of tasks in task list.
     */
    public int getTaskCount() {
        return taskCount;
    }

    /** Get list of print format of task list.
     *
     * @return ArrayList<String> List of strings representing task.
     */
    public ArrayList<String> printList() {
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            temp.add(taskList.get(i).printTask());
        }
        return temp;
    }

    /** Delete the task, and return deleted task.
     *
     * @return Task Deleted task.
     */
    public Task deleteTask(int indexToDelete) throws IndexOutOfBoundsException {
        Task t;
        try {
            t = taskList.remove(indexToDelete);
        } catch (IndexOutOfBoundsException o) {
            throw new IndexOutOfBoundsException();
        }

        taskCount--;
        return t;
    }

    /** Set task as done given index.
     *
     * @return Task Done task
     */
    public Task setTaskDone(int indexSetDone) throws IndexOutOfBoundsException {
        Task t;
        try {
            taskList.get(indexSetDone).setIsDone();
            t = taskList.get(indexSetDone);
        } catch (IndexOutOfBoundsException o) {
            throw new IndexOutOfBoundsException("Index out of Bound");
        }
        return t;
    }

    public ArrayList<String> find(String keyword) {
        ArrayList<String> listFound = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);

            String[] taskToken = t.getTaskDescription().split(" ");
            for (int j = 0; j < taskToken.length; j++) {
                if (taskToken[j].equals(keyword)) {
                    listFound.add(t.printTask());
                    break;
                }
            }

        }
        return listFound;
    }
}
