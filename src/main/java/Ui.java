import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private MainWindow window;
    private Scanner sc;

    private static final String DUKE_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Constructs a Ui object to print Duke responses out in the command line interface.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Constructs a Ui object to output Duke responses out in the graphic user interface.
     */
    public Ui(MainWindow window) {
        this.window = window;
    }

    /**
     * Passes response message to controller to be printed in graphic user interface.
     *
     * @param message the response message from Duke.
     */
    public void printInGui(String message) {
        assert message.length() > 0 : "Duke reponse error";
        window.handleDukeResponse(message);
    }

    /**
     * Prints line that separates user input command from duke responses.
     */
    public void printLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    /**
     * Prints welcome message when Duke is initialised.
     */
    public void printWelcome() {
        printInGui("Starting up...\n" + DUKE_LOGO + "\n" + "Hello, I'm Duke\nWhat can I do for you?");
    }


    /**
     * Prints goodbye message when Duke is closed.
     */
    public void printGoodbye() {
        printInGui("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out the list of Tasks in ArrayList tasks.
     *
     * @param tasks the ArrayList of tasks Duke is currently storing for user.
     */
    public void printList(ArrayList<Task> tasks) {
        StringBuilder sb  = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append(i + "." + tasks.get(i - 1) + "\n");
        }

        printInGui(sb.toString());
    }

    /**
     * Prints the Task that is done by the user.
     *
     * @param taskToMarkAsDone the Task that had been done by user.
     */
    public void printDone(Task taskToMarkAsDone) {
        StringBuilder sb = new StringBuilder("Nice! I've marked this task as done:\n");
        sb.append("  " + taskToMarkAsDone);

        printInGui(sb.toString());
    }

    /**
     * Prints the Task that is deleted by the user as well as the number of tasks currently.
     *
     * @param taskToBeRemoved the Task that is to be deleted.
     * @param numberOfTasks the remaining number of Tasks in the ArrayList tasks.
     */
    public void printDelete(Task taskToBeRemoved, int numberOfTasks) {
        StringBuilder sb = new StringBuilder("Noted. I've removed this task:");
        sb.append("  " + taskToBeRemoved + "\n");
        sb.append("Now you have " + numberOfTasks + " tasks in the list.");

        printInGui(sb.toString());
    }

    /**
     * Prints the Task that has been added to ArrayList tasks as well as the number of tasks currently.
     *
     * @param newTaskToBeAdded the Task to be added to ArrayList tasks.
     * @param numberOfTasks the updated number of Tasks in the ArrayList tasks.
     */
    public void printAdd(Task newTaskToBeAdded, int numberOfTasks) {
        StringBuilder sb = new StringBuilder("Got it. I've added this task:\n");
        sb.append("  " + newTaskToBeAdded);
        sb.append("Now you have " + numberOfTasks + " tasks in the list.");

        printInGui(sb.toString());
    }

    /**
     * Reads the user input command.
     *
     * @return the user input command
     */
    public String readCommand() {
        String command = sc.nextLine();
        return command;
    }

    /**
     * Prints the error message from the exception.
     *
     * @param e the exception that is thrown from lower level methods to Duke.
     */
    public void printError(Exception e) {
        printInGui("☹ OOPS!!! " + e.getMessage());
    }

    /**
     * Prints the Tasks that match the user input keyword.
     *
     * @param matchingTasks the ArrayList of matching Tasks.
     */
    public void printMatches(ArrayList<Task> matchingTasks) {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 1; i <= matchingTasks.size(); i++) {
            sb.append(i + "." + matchingTasks.get(i - 1));
        }
    }

    /**
     * Prints the Task that has been updated based on user input command.
     *
     * @param t the updated Task object base don user input command.
     * @param tasks the TaskList currently stored in Duke.
     */
    public void printUpdatedTask(Task t, ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder("Got it. I've updated this task\n  " + t + "\n");
        sb.append("Here are the tasks in your list:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append(i + "." + tasks.get(i - 1) + "\n");
        }

        printInGui(sb.toString());
    }
}
