import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Ui {
    private PrintStream ps;

    /**
     * Constructor for the Ui object. This object's role is to display messages to the user.
     */
    public Ui() {
        this.ps = new PrintStream(System.out, true, StandardCharsets.UTF_8);
    }

    /**
     * Displays the welcome message when the user starts the program.
     */
    public void welcomeMessage() {
        ps.println("Hello from\n"
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n");
        printResponse("Hello! I'm Duke. \n"
                + "What can I do for you?");
    }

    /**
     * Generic response template for displaying a normal response.
     * @param response The response to be displayed.
     */
    public void printResponse(String response) {
        ps.print("\t____________________________________________________________\n\t");
        response = response.replaceAll("\n", "\n\t");
        ps.println(response);
        ps.println("\t____________________________________________________________");
    }

    /**
     * Generic response template for displaying an error.
     * @param e The error message to be displayed.
     */
    public void printException(DukeException e) {
        ps.print("\t____________________________________________________________\n\t");
        String response = e.toString().replaceAll("\n", "\n\t");
        ps.println(" " + ((char) 0x2639) + " OOPS!!! " + response);
        ps.println("\t____________________________________________________________");
    }

    /**
     * Prints the whole list of items in the order as stored by the program.
     * @param listItems The list of all the items in the task list.
     */
    protected void printList(ArrayList<Task> listItems) {
        if (listItems.isEmpty()) {
            printResponse("You currently have no tasks!");
        } else {
            StringBuilder sb = new StringBuilder("Here are the tasks in your list:");
            for (int i = 1; i <= listItems.size(); i++) {
                sb.append("\n  ")
                        .append(i)
                        .append(". ")
                        .append(listItems.get(i - 1));
            }
            printResponse(sb.toString());
        }
    }

    /**
     * Prints the list of items stored by the program that matched the keyword given by the user.
     * @param listItems The list of matched items in the task list.
     */
    protected void printMatchList(ArrayList<Task> listItems) {
        if (listItems.isEmpty()) {
            printResponse("Sorry, there are no matching tasks!");
        } else {
            StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:");
            for (int i = 1; i <= listItems.size(); i++) {
                sb.append("\n  ")
                        .append(i)
                        .append(". ")
                        .append(listItems.get(i - 1));
            }
            printResponse(sb.toString());
        }
    }

    protected void printBye() {
        printResponse("Hope to see you again soon!");
    }

    /**
     * Displays a message when a task has been added to the task list.
     * @param taskString The String representation of the added task.
     * @param listSize The new size of the list.
     */
    protected void taskListAdd(String taskString, int listSize) {
        String taskSingular = listSize == 1 ? "task" : "tasks";
        printResponse("Got it. I've added this task: \n"
                + "  " + taskString + "\n"
                + "Now you have " + listSize + " " + taskSingular + " in the list.");
    }

    /**
     * Displays a message when a task in the task list has been set as Done.
     * @param taskString The String representation of the modified task.
     */
    protected void taskListDone(String taskString) {
        printResponse("Nice! I've marked this task as done: \n"
                + "  " + taskString);
    }

    /**
     * Displays a message when a task in the task list has been deleted.
     * @param taskString The String representation of the deleted task.
     * @param listSize The new size of the list.
     */
    protected void taskListDelete(String taskString, int listSize) {
        printResponse("I've removed this task:\n"
                + "  " + taskString + "\n"
                + "Now you have " + listSize + " tasks in the list.");
    }

}
