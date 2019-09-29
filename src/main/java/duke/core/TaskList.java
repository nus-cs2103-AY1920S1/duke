package duke.core;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

/**
 * Encapsulates a TaskList object, which contains the task list,
 * and has operations to add/delete/done tasks in the list.
 */

public class TaskList {

    /** 1 attribute.
     * list represents the task list.
     */
    private ArrayList<Task> list;

    /**
     * The constructor takes in no parameter and creates an empty list.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * The constructor takes in an ArrayList of task objects and creates a list of
     * the same content as the parameter.
     * @param tasks an ArrayList of task object.
     */
    public TaskList(ArrayList<Task> tasks) {
        list = tasks;
    }

    /**
     * Prints out the tasks in the list currently.
     */
    public String showTasks() {
        StringBuilder message = new StringBuilder("Here are the tasks in your list: ");
        int i = 0;
        for (Task task : list) {
            message.append("\n").append(i + 1).append(". ").append(task);
            i++;
        }
        //for (int i = 0; i < list.size(); i++) {
        //    message += (i + 1) + ". " + list.get(i) + "\n";
        //}
        return message.toString();
    }

    /**
     * Prints out the tasks with keyword in the list currently.
     */
    public String findTask(String keyword) {
        StringBuilder message = new StringBuilder("Here are the matching tasks in your list: ");
        int i = 0;
        for (Task task : list) {
            if (task.toString().contains(keyword)) {
                message.append("\n").append(i + 1).append(". ").append(task);
                i++;
            }
        }
        return message.toString();
    }

    /**
     * Formats and returns a message when a task is added.
     * @param newTask newly created task object.
     */
    private String getMessageAfterAdding(Task newTask) {
        return "Got it. I've added this task: \n  "
                + newTask + "\nNow you have " + list.size() + " tasks in the list.";
    }

    /**
     * Creates a new Todo object and adds it into the list.
     * @param activityName String of take name.
     * @param isDone true if the task is done, or false otherwise.
     */
    public String addTodo(String activityName, boolean isDone) {
        Task newTask = new Todo(activityName,isDone);
        list.add(newTask);
        return getMessageAfterAdding(newTask);
    }

    /**
     * Creates a new Deadline object and adds it into the list.
     * @param activityName String of take name.
     * @param deadline String of deadline time.
     * @param isDone true if the task is done, or false otherwise.
     */
    public String addDeadline(String activityName, String deadline, boolean isDone) {
        Task newTask = new Deadline(activityName, isDone, deadline);
        list.add(newTask);
        return getMessageAfterAdding(newTask);
    }

    /**
     * Creates a new Event object and adds it into the list.
     * @param activityName String of take name.
     * @param time String of activity time.
     * @param isDone true if the task is done, or false otherwise.
     */
    public String addEvent(String activityName, String time, boolean isDone) {
        Task newTask = new Event(activityName,isDone, time);
        list.add(newTask);
        return getMessageAfterAdding(newTask);
    }

    /**
     * Marks the task of the specified index to "done".
     * @param indices integer array that represents the index of the task in the command.
     */
    public String doneTask(int[] indices) {
        StringBuilder message = new StringBuilder("Nice! I've marked the following task(s) as done: ");
        for (int i : indices) {
            assert i >= 1 && i <= list.size() : "Invalid index input";
            list.get(i - 1).markAsDone();
            message.append("\n  ");
            message.append(list.get(i - 1));
        }
        return message.toString();
    }

    /**
     * Deletes the task in the command from the task list.
     * @param indices integer array that represents the index of the task in the command.
     */
    public String deleteTask(int[] indices) {
        StringBuilder message = new StringBuilder("Noted. I've removed the task(s): ");
        ArrayList<Task> toRemove = new ArrayList<>();
        for (int i : indices) {
            assert i >= 1 && i <= list.size() : "Invalid index input";
            toRemove.add(list.get(i - 1));
            message.append("\n  ");
            message.append(list.get(i - 1).toString());
        }
        list.removeAll(toRemove);
        return message + "\nNow you have " + list.size() + " tasks in the list.";
    }

    /**
     * Returns the number of existing tasks in the list.
     * @return an integer representing the number of tasks in the list.
     */
    int getNumOfTask() {
        return list.size();
    }

    /**
     * Returns an ArrayList of the current task list.
     * @return an ArrayList of the current task list.
     */
    ArrayList<Task> getTaskList() {
        return list;
    }
}
