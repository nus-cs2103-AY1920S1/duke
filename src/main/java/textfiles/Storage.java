package textfiles;


/**
 * Stores each user's task list in their local drive. This list
 * will be loaded whenever the user start up Duke.
 */
public class Storage {

    public String ioErrorMessage() {
        return printLine() +
                "     Sorry there is no text file to read or write data.\n" +
                printLine() +
                "\n";
    }

    private String printLine() {
        return ("    ____________________________________________________________\n");
    }
}
