package duke;

/**
 * Encapsulates a TaskList object, which contains the task list,
 * and has operations to add/delete/done tasks in the list.
 */

import java.util.ArrayList;

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
    String showTasks() {
        String message = "Here are the tasks in your list: \n";
        for (int i = 0; i < list.size(); i++) {
            message += (i + 1) + ". " + list.get(i) + "\n";
        }
        return message;
    }

    /**
     * Prints out the tasks with keyword in the list currently.
     */
    String findTask(String keyword) {
        String message = "Here are the matching tasks in your list: \n";
        int i = 0;
        for (Task task : list) {
            if (task.toString().contains(keyword)) {
               message += (i + 1) + ". " + task + "\n";
                i++;
            }
        }
        return message;
    }

    /**
     * Adds a new task into the list and prints out the message.
     * @param newTask newly created task object.
     */
    private String getMessage(Task newTask) {
        return "Got it. I've added this task: \n  "
                + newTask + "\nNow you have " + list.size() + " tasks in the list.";
    }

    /**
     * Creates a new Todo object and adds it into the list.
     * @param activityName String of take name.
     * @param isDone true if the task is done, or false otherwise.
     */
    String addTodo(String activityName, boolean isDone) {
        Task newTask = new Todo(activityName,isDone);
        list.add(newTask);
        return getMessage(newTask);
    }

    /**
     * Creates a new Deadline object and adds it into the list.
     * @param activityName String of take name.
     * @param deadline String of deadline time.
     * @param isDone true if the task is done, or false otherwise.
     */
    String addDeadline(String activityName, String deadline, boolean isDone) {
        Task newTask = new Deadline(activityName, isDone, deadline);
        list.add(newTask);
        return getMessage(newTask);
    }

    /**
     * Creates a new Event object and adds it into the list.
     * @param activityName String of take name.
     * @param time String of activity time.
     * @param isDone true if the task is done, or false otherwise.
     */
    String addEvent(String activityName, String time, boolean isDone) {
        Task newTask = new Event(activityName,isDone, time);
        list.add(newTask);
        return getMessage(newTask);
    }

    /**
     * Marks the task of the specified index to "done".
     * @param idx integer that represents the index of the task in the command.
     */
    String doneTask(int idx) {
        assert idx >=1 && idx <= list.size() : "Invalid index input";
        list.get(idx - 1).markAsDone();
        return "Nice! I've marked this task as done: \n  "
                + list.get(idx - 1);
    }

    /**
     * Deletes the task in the command from the task list.
     * @param idx integer that represents the index of the task in the command.
     */
    String deleteTask(int idx) {
        assert idx >=1 && idx <= list.size() : "Invalid index input";
        Task removed = list.remove(idx - 1);
        return "Noted. I've removed this task: \n  " + removed
                + "\nNow you have " + list.size() + " tasks in the list.";
    }

    /**
     * Returns an ArrayList of the current task list.
     * @return an ArrayList of the current task list.
     */
    ArrayList<Task> getTaskList() {
        return list;
    }
}
