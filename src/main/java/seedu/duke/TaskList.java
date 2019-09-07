package seedu.duke;

import java.util.ArrayList;

/**
 * TaskList is a class that represents the list of tasks and contains operations to manipulate the list.
 */
public class TaskList {

    private static ArrayList<Task> taskList;

    /**
     * Constructor of the Tasklist class.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();;
    }

    /**
     * Another constructor of the Tasklist class with the list of tasks as parameter.
     *
     * @param taskList the arraylist of tasks
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds the task to the list of tasks.
     *
     * @param t the task to be added
     */
    public void addTask(Task t) {
        taskList.add(t);
    }

    /**
     * Deletes the task from the list of tasks.
     *
     * @param index the index of the task to be deleted
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * Returns the task from the list of tasks.
     *
     * @param index the index of the task to be returned
     * @return the task
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Returns the size of the list of tasks.
     *
     * @return the size of the list of tasks
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Loops through the list of tasks to find the tasks containing the keywords.
     *
     * @param keyword the keyword that need to be matched
     * @return an arraylist tasks containing the keywords
     */
    public ArrayList<Task> findMatching(String keyword) {
        ArrayList<Task> matchingList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            String[] arr = taskList.get(i).getDescription().split(" ");
            for (int j = 0; j < arr.length; j++) {
                if (arr[j].equalsIgnoreCase(keyword)) {
                    matchingList.add(taskList.get(i));
                    break;
                }
            }
        }
        return matchingList;
    }

}
