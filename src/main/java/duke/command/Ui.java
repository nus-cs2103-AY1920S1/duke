package duke.command;

import duke.task.Task;
import java.util.ArrayList;

/**
 * Interacts with user by providing messages when required.
 */
public class Ui {
    public static String hr = "______________________________________________________________________";

    /**
     * Prints welcome when Duke is run.
     */
    public String showWelcome() {
        String result = "";
        result += "Hello there! I'm Duke Skywalker.\nWhat can I do for you?";
        return result;
    }

    /**
     * Prints goodbye when 'bye' command executed.
     */
    public String showGoodBye() {
        return "Bye. Hope to see you again soon!\nMay The Force Be With You!";
    }

    /**
     * Prints exception's message.
     * @param e exception that needs to be printed.
     */
    public String showException(Exception e) {
        return e.getMessage();
    }

    /**
     * Prints particular type of error message when non-number input is provided where number is required.
     * @param type type of command for which the error message needs to be generated.
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
     * Prints a long horizontal line for segmentation of commands that user provides and output that user sees.
     */
    public static String printLine() {
        return hr + "\n";
    }

    /**
     * Prints a message providing details about task just created.
     * @param task the task that is created.
     * @param noOfTasks number of tasks at the time, usually from TaskList.
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
     * @param task task that is marked as done.
     */
    public String showTaskDone(Task task) {
        String result = "";
        result += "Nice! I've marked this task as done:\n";
        result += "  " + task;
        return result;
    }

    /**
     * Prints a message providing details about the task just deleted and remaining tasks in TaskList.
     * @param task task that is just deleted.
     * @param noOfTasks number of tasks in TaskList
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
     * @param taskList the taskList that needs its Tasks' details printed.
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
     * Prints error message if loading history file encounters problem.
     */
    public String showLoadingError() {
        return " :( OOPS!!! Error occurred while loading the history file.";
    }

    public String showDuplicateTaskMessage(Task task) {
        return " :( OOPS!!! " + task.toString() + " already exists! Please enter a different Task.";
    }
}
