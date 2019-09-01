package Tasks;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Acts as a list of tasks which can be manipulated through
 * create/read/delete/update operations.
 */
public class TaskList {
    ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Constructor for TaskList object
     *
     * @param taskList is the ArrayList of tasks going to be stored
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds more tasks into TaskList once at a time
     *
     * @param task to be stored
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes 1 task from TaskList
     *
     * @param itemNum is the index of the task to be deleted
     */
    public void deleteTask(int itemNum) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + taskList.get(itemNum));
        taskList.remove(itemNum);
        System.out.println("Now you have " + taskList.size() + " in the list.");
    }

    /**
     * Marks 1 task from TaskList as done
     *
     * @param itemNum is the index of the task to be marked as done
     */
    public void markTaskDone(int itemNum) {
        taskList.get(itemNum).doneJob();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + taskList.get(itemNum));
        System.out.println("Now you have " + taskList.size() + " in the list.");
    }

    /**
     * Iterates though TaskList and provides a list of tasks stored.
     */
    public void showAllTasks() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < taskList.size(); i++) {
            System.out.print((i + 1) + ".");
            System.out.println(taskList.get(i));
            continue;
        }
    }

    /**
     * Getter for ArrayList of Tasks
     *
     * @return ArrayList of Tasks
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Setter for ArrayList of Tasks
     *
     * ArrayList of Tasks stored into TaskList
     */
    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

}
