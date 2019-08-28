package duke.tasklist;

import java.util.ArrayList;

import duke.task.Task;

// This class contains the task list and operations to work with task list.
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

    public void addNewTask(Task task) {
        taskList.add(task);
    }

    public void increaseTaskCount() {
        taskCount++;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public ArrayList<String> printList() {
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            temp.add(taskList.get(i).printTask());
        }
        return temp;
    }

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
}
