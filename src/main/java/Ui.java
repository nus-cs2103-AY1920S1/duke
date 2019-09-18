import java.util.Scanner;

/**
 * Class to handle interfacing with the user.
 */
public class Ui {
    // Strings that Duke will output
    private static final String PRINTED_LONG_LINE = "____________________________________________________________";
    private static final String PRINTED_GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String PRINTED_BYE = "Bye. Hope to see you again soon!\nThis program will exit in one second.";

    private static final String PRINTED_HEADER_LIST = "Here are the tasks in your list:";
    private static final String PRINTED_HEADER_DONE = "Nice! I've marked this task as done:";
    private static final String PRINTED_HEADER_SEARCH = "Here are the tasks matching the following query: ";
    private static final String PRINTED_INDEX_OUT_OF_BOUNDS_EXCEPTION =
        "You have entered an invalid index. Please try again.";

    private Scanner input = new Scanner(System.in);

    /**
     * Prints a goodbye message to the user, and closes off the input Scanner.
     */
    public String printGoodbye() {
        input.close();
        return printWithLongLines(PRINTED_BYE);
    }

    /**
     * Prints a greeting to the user.
     */
    public String printGreeting() {
        return printWithLongLines(PRINTED_GREETING);
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
    public String ackDeletion(Task deletedTask, int listSize) {
        assert listSize >= 0 : "ackDeletion was passed a negative listSize";

        return printWithLongLines(
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
    public String ackDone(Task doneTask) {
        return printWithLongLines(
            PRINTED_HEADER_DONE
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
    public String ackAddition(Task newTask, int listSize) {        
        assert listSize >= 0 : "ackAddition was passed a negative listSize";

        return printWithLongLines(
            "Got it. I've added this task:\n"
            + newTask
            + "\nNow you have " 
            + listSize
            + " tasks in the list."
        );
    }

    /**
     * Prints the provided TaskList to the user.
     * 
     * @param taskList The TaskList to be printed
     */
    public String printList(TaskList taskList) {
        return printListWithPreamble(taskList, PRINTED_HEADER_LIST);
    }

    public String displaySearchResults(TaskList taskList, String query) {
        return printListWithPreamble(taskList, PRINTED_HEADER_SEARCH + query + "\n");
    }

    /**
     * Advises the user of the required date format.
     * 
     * @param format The expected date format
     */
    public String adviseDateFormat(String format) {
        return printWithLongLines("Required date format: " + format);
    }

    /**
     * Displays an Exception's message to the user.
     * 
     * @param e The Exception to be displayed
     */
    public String printException(Exception e) {
        return printWithLongLines(e.getMessage());
    }

    /**
     * Displays an explanatory note to the user that explains an IndexOutOfBoundsException.
     */
    public String printIndexOutOfBoundsException() {
        return printWithLongLines(PRINTED_INDEX_OUT_OF_BOUNDS_EXCEPTION);
    }

    private String printWithLongLines(String stringToPrint) {

        // The commented-out code is for printing the long horizontal lines above and below each message.
        // I chose not to remove this code in case I need it in future.

        String outValue = (
            stringToPrint
            + "\n"
        );

        System.out.println(outValue);
        return outValue;
    }

    private String printListWithPreamble(TaskList taskList, String preamble) {
        String wholeList = preamble + "\n";
        
        for (int i = 0; i < taskList.size(); i++) {
            wholeList += String.valueOf(i + 1)
                + "."
                + taskList.get(i);
            
            if (i < taskList.size() - 1) {
                wholeList += "\n";
            }
        }

        return printWithLongLines(wholeList);
    }
}