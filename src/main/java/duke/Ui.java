package duke;

import task.Task;

import java.util.Scanner;

public class Ui {
    private String displayText;
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }


    /**
     * Prints a greeting message to welcome the user.
     */
    public void showGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greet = "Hello! I'm duke.Duke\nWhat can I do for you?";
        System.out.println(greet);
    }

    /**
     * Sets the displayText variable to print a goodbye message.
     */
    public void showBye() {
        displayText = "Bye. Hope to see you again soon!";
    }

    /**
     * Prints an error message when the tasks could not be loaded from the txt file.
     */
    public void showLoadingError() {
        //if face not shown, try to use \u2639
        System.out.println("☹ OOPS!!! I'm sorry, but I could not load your saved task list ");
    }

    /**
     * Reads the user input.
     * @return user input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints any error message.
     * @param message error message.
     */
    public void setError(String message) {
        //if face not shown, try to use \u2639
        StringBuilder sb = new StringBuilder("☹ OOPS!!! I'm sorry, but ");
        sb.append(message);
        setText(sb.toString());
    }

    /**
     * Prints the saved text and reset the variable.
     */
    public String showLine() {
        String tmp = displayText;
        displayText = "";
        return tmp;
    }

    /**
     * Sets the displayText message that is to be printed when showLine() is called.
     * @param str to replace displayText.
     */
    public void setText(String str) {
        displayText = str;
    }

    /**
     * Sets the displayText message when a task is saved.
     * @param task saved.
     * @param size number of tasks in the list.
     */
    public void showTaskSaved(Task task, int size) {
        displayText = "Got it. I've added this task:\n\t" + task + "\nNow you have " + size + " tasks in the list.";
    }
}
