package duke.ui;

/**
 * The Ui class is in charge of interactions with user, such as reading user commands and displaying
 * formatted messages to the user.
 */

import duke.task.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class Ui {
    private static final String TABS = "     ";

    public Ui() {

    }

    /**
     * Prints a welcome message.
     */
    public void showWelcome() {
        System.out.println("\t Hello! I'm Duke\n" + "What can I do for you?");
    }

    public String showDoneMessage() {
        return "Nice! I've marked this task as done: \n";
    }

    public String showDeleteMessage() {
        return "Noted. I've removed this task: \n";
    }

    /**
     * Prints the addition message when a task is added.
     */
    public String showAddMessage() {
        return "Got it. I've added this task: \n";
    }

    /**
     * Prints the number of tasks in the task list.
     * @param numberOfTasks number of tasks in task list
     * @return String of message showing number of tasks
     */
    public String showNumberOfTasks(Integer numberOfTasks) {
        if (numberOfTasks == 1) {
            return String.format("Now you have %d task in the list.\n", numberOfTasks);
        } else {
            return String.format("Now you have %d tasks in the list.\n", numberOfTasks);
        }
    }

    public String showTask(Task taskToDisplay) {
        return taskToDisplay.toString() + "\n";
    }

    public String showExit() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Displays the task list.
     * @param taskList the task list to be displayed
     */
    public String showTaskList(ArrayList<Task> taskList) {
        String message = "";
        message += "Here are the tasks in your list:\n";
        Integer index = 1;
        for (Task taskToShow : taskList) {
            message += "\t" + index.toString() + "." + taskToShow.toString() + "\n";
            index++;
        }
        return message;
    }

    /**
     * Displays tasks in the taskList argument supplied to the screen.
     * The taskList argument is derived from the search() method in the TaskList class.
     * @param taskList taskList containing tasks which has task descriptions matching a user supplied keyword.
     */
    public String showSearchResults(HashMap<Integer, Task> taskList) {
        String message = "";
        message += "Here are the matching tasks in your list:\n";

        for (Integer index : taskList.keySet()) {
            Task currentTask = taskList.get(index);
            message += "\t" + index.toString() + "." + currentTask.toString() + "\n";
        }
        return message;
    }

    /**
     * Displays a DukeException.
     * @param message exception message to be displayed
     */
    public String showError(String message) {
        return "\u2639 OOPS!!! " + message + "\n"; //displays a sad face
    }
}
