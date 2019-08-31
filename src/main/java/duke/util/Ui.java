package duke.util;
/**
 * Ui class to print display items
 */
public class Ui {
    /**
     * Print welcome message
     */
    public void welcomeMessage() {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        drawLine();
        System.out.println("    Hello from\n" + logo);
        System.out.println("    What can I do for you?");
        drawLine();
        System.out.println("\n");
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
    public void byeMessage() {
        drawLine();
        System.out.println("     Bye. Hope to see you again soon!");
        drawLine();
        System.out.println("\n");
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
