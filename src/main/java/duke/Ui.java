package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a user interface which interacts with the user.
 */
public class Ui {
    Scanner scanner = new Scanner(System.in);

    /**
     * Default constructor.
     */
    public Ui() {
    }


    /**
     * Returns the number of tasks in the taskList.
     *
     * @param taskList taskList used to store tasks
     */
    public String printNumberOfTasks(TaskList taskList) {
        return ("Now you have " + taskList.getTasksSize()
                + " tasks in the list.") + "\n";
    }

    /**
     * Returns the string representation of task t.
     *
     * @param t duke.task.Task to be printed out
     */
    public String printTask(Task t) {
        return (t.toString()) + "\n";
    }

    /**
     * Returns the String representation of all the tasks in taskList.
     *
     * @param taskList taskList used to store tasks
     */
    public String printTaskList(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        int number = 1;
        String outputString = "";
        for (Task task : tasks) {
            outputString = outputString + number + ". " + task.toString() + "\n";
            number++;
        }
        return outputString + "\n";
    }

    /**
     * String representation of the error message.
     * @param error exception to be caught
     */
    public String printErrorMessage(String error) {
        return "Oops!!! " + error + "\n";
    }

    /**
     * String representation of the pre-message for the add command.
     */
    public String printAddedMessage() {
        return ("Got it. I've added this task:") + "\n";
    }

    /**
     * String representation of the pre-message for the delete command.
     */
    public String printDeletedMessage() {
        return ("Noted. I've removed the task(s):") + "\n";
    }

    /**
     * String representation of the pre-message for the done command.
     */
    public String printDoneMessage() {
        return ("Nice! I've marked the task(s) as done: ") + "\n";
    }

    /**
     * String representation of the pre-message for the list command.
     */
    public String printListMessage() {
        return ("Here are the tasks in your list: ") + "\n";
    }

    /**
     * String representation of greetings for duke.Duke bot.
     */
    public String greet() {
        return ("Beep Boop! I'm Duke\nWhat can I do for you?") + "\n";
    }

    /**
     * String representation of the message for the bye command.
     */
    public String bye() {
        return ("Bye. Hope to see you again soon!") + "\n";
    }

    /**
     * String representation of the message for the find command.
     */
    public String printFindMessage(ArrayList<Task> tasks) {
        String outputString;
        outputString = "Here are the matching tasks in your list:\n";
        int count = 1;
        for (Task t : tasks) {
            String taskString = count + "." + t.toString() + "\n";
            outputString = outputString + taskString;
            count++;
        }
        return outputString + "\n";
    }
}
