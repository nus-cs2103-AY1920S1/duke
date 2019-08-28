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
     * Show loading error of files.
     *
     * @param e the error message.
     */
    public static void showLoadingError(FileNotFoundException e) {
        System.out.println("Did you created the file for task storage? " + e.getMessage());
    }

    /**
     * Show task list.
     *
     * @param tasks the tasks to be displayed.
     */
    public void showTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        int counter = 0;
        for (Task t : tasks) {
            counter++;
            System.out.println(counter + ". " + t.toString());
        }
    }

    /**
     * Show exit msg.
     */
    public void showExitMsg() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Show task done prompt.
     *
     * @param t the task that has been marked as finished.
     */
    public void showTaskDonePrompt(Task t) {
        String prompt = "Nice! I've marked this task as done:\n" +
                "    " + t.toString();
        System.out.println(prompt);
    }

    /**
     * Show task deleted prompt.
     *
     * @param t    the task that has been deleted.
     * @param size the number of the remaining tasks in the list
     */
    public void showTaskDeletedPrompt(Task t, int size) {
        String prompt = "Noted. I've removed this task:\n" +
                "    " + t.toString() + "\n" +
                "Now you have " + size + (size == 1 ? " task" : " tasks") + " in the list.";
        System.out.println(prompt);
    }

    /**
     * Show added task prompt.
     *
     * @param tasks the tasks list
     * @param t     the task to be added
     */
    public void showAddedTaskPrompt(ArrayList<Task> tasks, Task t) {
        String output = "Got it. I've added this task:\n" +
                "    " + t.toString() + "\n" +
                "Now you have " + tasks.size() + (tasks.size() == 1 ? " task " : " tasks ") + "in the list.";
        System.out.println(output);
    }

    /**
     * Show saving error. Cannot write to file.
     *
     * @param e the error message.
     */
    public void showSavingError(IOException e) {
        System.out.println("Task not saved: " + e.getMessage());
    }

    /**
     * Show greeting to user.
     */
    public void showGreeting() {
        String greeting = "Hello! I'm Duke\n" +
                "What can I do for you?";
        System.out.println(greeting);
    }

    /**
     * Show error of type duke exception.
     *
     * @param e the error message.
     */
    public void showDukeError(DukeException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Read command from user input.
     *
     * @return the input line from user.
     * @throws UnrecognizedException if there is no input from the user.
     */
    public String readCommand() throws UnrecognizedException {
        if(input.hasNextLine()) {
            return input.nextLine();
        } else {
            throw new UnrecognizedException("☹ OOPS!!! no input.");
            //System.exit(0);
            //return "";
        }
    }
}
