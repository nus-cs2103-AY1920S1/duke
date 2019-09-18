package duke.command;

import duke.task.Task;
import java.util.ArrayList;

/**
 * Interacts with user by providing messages when required.
 * Each method returns a string that dukeDialogBox will display.
 */
public class Ui {
    public static String hr = "______________________________________________________________________";

    /**
     * Prints welcome when Duke is run.
     * @return String representing welcome message
     */
    public String showWelcome() {
        String result = "";
        result += "Hello there! I'm Duke Skywalker.\nWhat can I do for you?";
        return result;
    }

    /**
     * Prints goodbye when 'bye' command executed.
     * @return String representing GoodBye message
     */
    public String showGoodBye() {
        return "Bye. Hope to see you again soon!\nMay The Force Be With You!";
    }

    /**
     * Prints exception's message.
     * @param e exception that needs to be printed
     * @return String representing the Exception message
     */
    public String showException(Exception e) {
        return e.getMessage();
    }

    /**
     * Prints particular type of error message when non-number input is provided where number is required.
     * @param type type of command for which the error message needs to be generated
     * @return String representing the numberFormatError message
     */
    public String showNumberFormatError(String type) {
        String result = "";
        switch (type) {
        case "done" : result += " :( OOPS!!! Invalid format."
                + " Please enter the number of the task to be marked as done.";
            break;
        case "delete" : result += " :( OOPS!!! Invalid format."
                + " Please enter the number of the task to be deleted.";
            break;
        default : result += " :( OOPS!!! Invalid format."
            + " Please enter a number.";
        }
        return result;
    }

    /**
     * Prints a message providing details about task just created.
     * @param task the task that is created
     * @param noOfTasks number of tasks at the time, usually from TaskList
     * @return String representing the Task that is created and the number of Tasks in the TaskList
     */
    public String showTaskCreated(Task task, int noOfTasks) {
        String result = "";
        result += "Got it. I've added this task:\n";
        result += " " + task + "\n";
        result += "Now you have " + noOfTasks + " tasks in the list.";
        return result;
    }

    /**
     * Prints a message providing details about the task just marked as done.
     * @param task task that is marked as done
     * @return String representing the Task that is marked as done
     */
    public String showTaskDone(Task task) {
        String result = "";
        result += "Nice! I've marked this task as done:\n";
        result += "  " + task;
        return result;
    }

    /**
     * Prints a message providing details about the task just deleted and remaining tasks in TaskList.
     * @param task task that is just deleted
     * @param noOfTasks number of tasks in TaskList
     * @return String representing the Task that is deleted
     */
    public String showTaskDeleted(Task task, int noOfTasks) {
        String result = "";
        result += "Noted. I've removed this task:\n";
        result += " " + task + "\n";
        result += "Now you have " + noOfTasks + " tasks in the list.";
        return result;
    }

    /**
     * Prints output for the 'list' command.
     * @param taskList the taskList that needs its Tasks' details printed
     * @return String representing a list of Tasks in TaskList
     */
    public String showTasks(TaskList taskList) {
        String result = "Here are the tasks in your list:\n";
        ArrayList<Task> arr = taskList.getArr();
        for (int i = 0; i < arr.size(); i++) {
            Task temp = arr.get(i);
            result += (i + 1) + ". " + temp + "\n";
        }
        if (taskList.isEmpty()) {
            result += "There are no Tasks to show.\n";
        }
        return result;
    }

    /**
     * Prints out a list of deleted Tasks.
     * @param deletedTasks ArrayList of Tasks deleted by executor
     * @param noOfTasks number of Tasks remaining in the TaskList
     * @return String representing a list of deleted Tasks
     */
    public String showDeletedTasks(ArrayList<Task> deletedTasks, int noOfTasks) {
        String result = "I have removed all the tasks that were done. Here are the removed tasks:\n";
        int i = 1;
        for (Task deletedTask : deletedTasks) {
            result += i + ". " + deletedTask.toString() + "\n";
            i++;
        }
        result += "Now you have " + noOfTasks + " tasks left in the list.";
        return result;
    }

    /**
     * Prints error message if loading history file encounters problem.
     */
    public void showLoadingError() {
        System.out.println(" :( OOPS!!! Error occurred while loading the history file.");
    }

    /**
     * Prints all the tasks with a given String in their description.
     * @param taskList TaskList created by find operation of TaskList
     * @param filter String that is used to filter tasks
     * @return String representing list of tasks with given filter in their descriptions
     */
    public String showFound(TaskList taskList, String filter) {
        if (taskList.isEmpty()) {
            return "There are no Tasks in your list with '" + filter + "' in their description.\n";
        }
        String result = "Here are the tasks with '" + filter + "' in your list:\n";
        ArrayList<Task> arr = taskList.getArr();
        for (int i = 0; i < arr.size(); i++) {
            Task temp = arr.get(i);
            result += (i + 1) + ". " + temp + "\n";
        }
        return result;
    }

    /**
     * Prints a duplicate message error message.
     * @param task Task that is duplicate
     * @return message to user to indicate an attempt to add duplicate Task
     */
    public String showDuplicateTaskMessage(Task task) {
        return " :( OOPS!!! " + task.toString() + " already exists! Please enter a different Task.";
    }
}
