import java.util.Scanner;

/**
 * Class to handle interfacing with the user.
 */
public class Ui {
    // Strings that Duke will output
    private static final String LONG_LINE = "____________________________________________________________";
    private static final String GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String BYE_STR = "Bye. Hope to see you again soon!";
    private static final String LIST_STR = "Here are the tasks in your list:";
    private static final String DONE_STR = "Nice! I've marked this task as done:";
    private static final String SEARCH_STR = "Here are the tasks matching the following query: ";

    private Scanner input = new Scanner(System.in);

    /**
     * Prints a goodbye message to the user, and closes off the input Scanner.
     */
    public void printGoodbye() {
        printWithLongLines(BYE_STR);
        input.close();
    }

    /**
     * Prints a greeting to the user.
     */
    public void printGreeting() {
        printWithLongLines(GREETING);
    }

    /**
     * Reads the next line of input from the user.
     * 
     * @return The next line of input from the user
     */
    public String nextLine() {
        return input.nextLine();
    }

    /**
     * Acknowledges the user's deletion of a Task.
     * 
     * @param deletedTask The Task that was deleted
     * @param listSize The new size of the TaskList
     */
    public void ackDeletion(Task deletedTask, int listSize) {
        printWithLongLines(
            "Noted. I've removed this task:\n"
            + deletedTask
            + "\nNow you have " 
            + listSize
            + " tasks in the list."
        );
    }

    /**
     * Acknowledges the user's completion of a Task.
     * 
     * @param doneTask The Task that was done
     */
    public void ackDone(Task doneTask) {
        printWithLongLines(
            DONE_STR
            + "\n"
            + doneTask
        );
    }

    /**
     * Acknowledge the addition of a Task.
     * 
     * @param newTask The Task that was newly added
     * @param listSize The new size of the TaskList
     */
    public void ackAddition(Task newTask, int listSize) {
        printWithLongLines(
            "Got it. I've added this task:\n"
            + newTask
            + "\nNow you have " 
            + listSize
            + " tasks in the list."
        );
    }

    /**
     * Print the provided TaskList to the user.
     * 
     * @param taskList The TaskList to be printed
     */
    public void printList(TaskList taskList) {
        printListWithPreamble(taskList, LIST_STR);
    }

    public void displaySearchResults(TaskList taskList, String query) {
        printListWithPreamble(taskList, SEARCH_STR + query + "\n");
    }

    /**
     * Advise the user of the required date format.
     * 
     * @param format The expected date format
     */
    public void adviseDateFormat(String format) {
        printWithLongLines("Required date format: " + format);
    }

    /**
     * Display an Exception's message to the user.
     * 
     * @param e The Exception to be displayed
     */
    public void printException(Exception e) {
        printWithLongLines(e.getMessage());
    }

    private void printWithLongLines(String stringToPrint) {
        System.out.println(
            LONG_LINE
            + "\n"
            + stringToPrint
            + "\n"
            + LONG_LINE
            + "\n"
        );
    }

    private void printListWithPreamble(TaskList taskList, String preamble) {
        String wholeList = preamble + "\n";
        
        for (int i = 0; i < taskList.size(); i++) {
            wholeList += String.valueOf(i + 1)
                + "."
                + taskList.get(i);
            
            if (i < taskList.size() - 1) {
                wholeList += "\n";
            }
        }

        printWithLongLines(wholeList);
    
    }
}