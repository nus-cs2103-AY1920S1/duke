package textfiles;

/**
 * Stores each user's task list in their local drive. This list
 * will be loaded whenever the user start up Duke.
 */
public class Storage {
    /**
     * Sends error message when there is no duke.txt file in the user's
     * hard drive to read and write the data into.
     */
    public void ioErrorMessage() {
        printLine();
        System.out.println("     Sorry there is no text file to read or write data.");
        printLine();
        System.out.println();
    }

    /**
     * Print out a generic line for indentation purposes.
     */
    private static void printLine() {
        System.out.println("    ____________________________________________________________");
    }
}
