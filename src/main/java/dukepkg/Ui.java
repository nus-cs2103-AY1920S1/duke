package dukepkg;
import dukepkg.exceptions.DukeException;
import dukepkg.exceptions.UnrecognizedException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui object used to interact with the user..
 */
public class Ui {
    private final Scanner input;


    /**
     * Instantiates a new Ui.
     */
    public Ui() {
        input = new Scanner(System.in);
    }

    /**
     * Shows loading error of files.
     *
     * @param e the error message.
     * @return the string
     */
    public static String showLoadingError(FileNotFoundException e) {
        return "Did you created the file for task storage? " + e.getMessage() + "\n";
    }

    /**
     * Shows task list.
     *
     * @param tasks the tasks to be displayed.
     * @return the string
     */
    public String showTaskList(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            return "Your task list is empty.";
        }
        String str = "Here are the tasks in your list:\n";
        int counter = 0;
        assert(tasks.size() != 0);
        for (Task t : tasks) {
            counter++;
            str += counter + ". " + t.toString() + "\n";
        }
        return str;
    }

    /**
     * Shows exit msg.
     *
     * @return the string
     */
    public String showExitMsg() {
        return "Bye. Hope to see you again soon! Exit in 3 seconds...\n";
    }

    /**
     * Shows task done prompt.
     *
     * @param t the task that has been marked as finished.
     * @return the string
     */
    public String showTaskDonePrompt(Task t) {
        String prompt = "Nice! I've marked this task as done:\n" +
                "    " + t.toString() + "\n";
        return prompt;
    }

    /**
     * Shows task deleted prompt.
     *
     * @param t    the task that has been deleted.
     * @param size the number of the remaining tasks in the list
     * @return the string
     */
    public String showTaskDeletedPrompt(Task t, int size) {
        String prompt = "Noted. I've removed this task:\n" +
                "    " + t.toString() + "\n" +
                "Now you have " + size + (size == 1 ? " task" : " tasks") + " in the list.\n";
        return prompt;
    }

    /**
     * Shows added task prompt.
     *
     * @param tasks the tasks list
     * @param t     the task to be added
     * @return the string
     */
    public String showAddedTaskPrompt(ArrayList<Task> tasks, Task t) {
        String output = "Got it. I've added this task:\n" +
                "    " + t.toString() + "\n" +
                "Now you have " + tasks.size() + (tasks.size() == 1 ? " task " : " tasks ") + "in the list. \n";
        return output;
    }

    /**
     * Shows saving error. Cannot write to file.
     *
     * @param e the error message.
     * @return the string
     */
    public String showSavingError(IOException e) {
        return ("Task not saved: " + e.getMessage() + "\n");
    }

    /**
     * Shows greeting to user.
     *
     * @return the string
     */
    public static String showGreeting() {
        String greeting = "Hello! I'm Tsuki, your personal task manager.\n" +
                "What can I do for you?\n";
        return greeting;
    }

    /**
     * Shows error of type duke exception.
     *
     * @param e the error message.
     * @return the string
     */
    public String showDukeError(DukeException e) {
        return e.getMessage() + "\n";
    }

    /**
     * Reads command from user input.
     *
     * @return the input line from user.
     * @throws UnrecognizedException if there is no input from the user.
     */
    public String readCommand() throws UnrecognizedException {
        if(input.hasNextLine()) {
            return input.nextLine();
        } else {
            throw new UnrecognizedException("☹ OOPS!!! no input.");
        }
    }

    /**
     * Shows tasks that matches the keywords.
     *
     * @param selected the selected tasks
     * @return the string
     */
    public String showMatchingTask(ArrayList<Task> selected) {
        String str = "Here are the matching tasks in your list: \n";
        int counter = 0;
        for(Task s : selected) {
            counter ++;
            str += counter + "." + s.toString() + "\n";
        }
        return str;
    }

    /**
     * Show no matching task string.
     *
     * @return the string
     */
    public String showNoMatchingTask() {
        return "Oops, no matching task.";
    }

    public String showHelpMsg() {
        return "Here is the list of available commands:\n" +
                "Add task - todo <task>\n" +
                "Add timed task - time <task> <duration>\n" +
                "Add event - event <task> /at <time>\n " +
                "Add deadline - deadline <task> /by <time> \n" +
                "Mark task as done - done <task_id>\n" +
                "Delete task - delete <task_id>\n" +
                "List all tasks - list\n" +
                "Find task by keywords - find <keywords separated by space>\n" +
                "Exit the program - bye\n";
    }
}
