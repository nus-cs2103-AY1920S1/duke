package duke.util;
/**
 * Ui class to print display items
 */
public class Ui {
    public static final String line = "    ________________________"
            + "____________________________________";

    /**
     * Print welcome message
     */
    public static String welcomeMessage() {
        String result = "Hello!\nWhat can I do for you?";
        result += "\nType 'commands' to view a list of commands you can use.";
        return result;
    }

    /**
     * Prints error messages for DukeException
     * @param error Error message of DukeException
     */
    public void printErrorMessage(String error) {
        drawLine();
        System.out.println("     " + error);
        drawLine();
        System.out.println("\n");
    }

    /**
     * Prints bye message
     */
    public String byeMessage() {
        String result = "Bye. Hope to see you again soon!";
        return result;
    }

    /**
     * Draws dividing line
     */
    public void drawLine() {
        String line = "    ________________________"
                + "____________________________________";
        System.out.println(line);
    }

    /**
     * Draws dividing line with new line
     */
    public void drawLineNewLine() {
        String line = "    ________________________"
                + "____________________________________";
        System.out.println(line);
        System.out.println();
    }
}
