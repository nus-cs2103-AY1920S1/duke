package duke.tasks;

import java.util.ArrayList;

/**
 * Represents the object where tasks are store in during the programme.
 */

public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs the taskList from an ArrayList of tasks.
     * 
     * @param tasksList ArrayList of tasks
     */

    public TaskList(ArrayList<Task> tasksList) {
        this.tasks = tasksList;
    }

    /**
     * Constructs empty TaskList.
     */

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task into the TaskList.
     * 
     * @param task task to be added into TaskList
     */

    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the TaskList with index corresponding to the parameter.
     * 
     * @param index index of task to be removed from TaskList
     */

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Returns a task with the specified index.
     * 
     * @param index index of task to be returned
     * @return Task with specified index
     */

    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of Task in the TaskList.
     *
     * @return number of Tasks in the TaskList
     */

    public int getSize() {
        return tasks.size();
    }

    /**
     * returns the TaskList in ArrayList format.
     * 
     * @return ArrayList of Tasks
     */

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Searches the TaskList for any Task that contains the toSearch String and
     * returns a TaskList containing all these Tasks.
     * 
     * @param toSearch substring to be searched
     * @return a TaskList of tasks with the toSearch substring
     */

    public TaskList search(String toSearch) {
        ArrayList<Task> matchingTask = new ArrayList<>();
        for (Task task : tasks) {
            if (task.contains(toSearch)) {
                matchingTask.add(task);
            }
        }
        return new TaskList(matchingTask);
    }

    /**
     * Returns the list of Tasks to be printed.
     * 
     * @return String of the list of task
     */

    @Override
    public String toString() {
        StringBuilder toReturnBuilder = new StringBuilder();
        int length = tasks.size();
        for (int i = 0; i < length; i++) {
            int index = i + 1;
            toReturnBuilder.append(index + "." + tasks.get(i) + "\n");
        }
        return toReturnBuilder.toString();
    }
}
