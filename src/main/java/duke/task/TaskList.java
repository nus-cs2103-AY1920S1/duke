package duke.task;

import java.util.ArrayList;
import java.util.Collections;

/**
 * TaskList contains an array-list of tasks. It keeps tracks
 * of all the tasks and is the gateway for all modifications
 * between Duke and the tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Initialises a new TaskList with an initial capacity of 100.
     */
    public TaskList() {
        taskList = new ArrayList<>(100);
    }

    /**
     * Adds a task into the TaskList.
     *
     * @param t The task that is added to the TaskList.
     */
    public void addTask(Task t) {
        taskList.add(t);
        Collections.sort(taskList);
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        taskList.remove(index);
        Collections.sort(taskList);
    }

    /**
     * Returns an integer of the number of tasks in TaskList.
     *
     * @return The number of tasks in TaskList.
     */
    public int getSizeOfTaskList() {
        return taskList.size();
    }

    /**
     * Marks a task as completed by changing its
     * completed field to true.
     *
     * @param taskIndex The index of the task.
     */
    public void checkTask(int taskIndex) {
        Task t = taskList.get(taskIndex);
        t.taskDone();
    }

    /**
     * Sets the priority of a task of given index to the given PriorityLevel.
     *
     * @param taskIndex The index of the task.
     * @param priority The PriorityLevel which the task's priority should be changed to.
     */
    public void setPriorityOfTask(int taskIndex, PriorityLevel priority) {
        Task t = taskList.get(taskIndex);
        t.setPriority(priority);
        Collections.sort(taskList);
    }

    /**
     * Gets the description of a task by its index.
     *
     * @param taskIndex The index of the task.
     * @return A String of the task description.
     */
    public String getTaskDsc(int taskIndex) {
        Task t = taskList.get(taskIndex);
        return t.getDescription();
    }

    /**
     * Gets the full information of a task.
     * The information is made out of its type of task, completion status,
     * description and PriorityLevel.
     *
     * @param taskIndex The index of the task.
     * @return A String of the type of task, completion status, description, time and PriorityLevel.
     */
    public String getFullTaskInfo(int taskIndex) {
        Task t = taskList.get(taskIndex);
        return t.toString();
    }

    /**
     * Returns a string of full information of all tasks in the TaskList.
     *
     * @return A string containing full information of all tasks in the TaskList.
     */
    public String saveInfo() {
        StringBuilder info = new StringBuilder();
        boolean isFirstIteration = true;
        for (Task t : taskList) {
            if (isFirstIteration) {
                info.append(t.saveInfo());
                isFirstIteration = false;
            } else {
                info.append(System.getProperty("line.separator")).append(t.saveInfo());
            }
        }
        return info.toString();
    }
}
