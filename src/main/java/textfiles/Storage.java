package textfiles;

/**
 * Stores each user's task list in their local drive. This list
 * will be loaded whenever the user start up Duke.
 */
public class Storage {

    /**
     * Prompts the user if duke.txt file is missing in the local drive.
     *
     * @return ioErrorMessage which prompts the user there is no duke.txt file.
     */
    public String ioErrorMessage() {
        return printLine()
                + "     Sorry there is no text file to read or write data.\n"
                + printLine()
                + "\n";
    }

    /**
     * Gives a string as a border.
     *
     * @return a string that forms the border for duke.
     */
    private String printLine() {
        return ("    ____________________________________________________________\n");
    }
}
