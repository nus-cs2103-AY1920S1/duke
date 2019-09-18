package duke.ui;

/**
 * The Ui class handles the chat bot's responses to the user, namely what it should print back to the user.
 */

public class Ui {
    /**
     * The Duke logo.
     */
    public String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    /**
     * A line that separates user input from chat bot output.
     */
    String line = "________________________________________";
    /**
     * An indentation of 4 spaces. All chat bot output are indented by 4 spaces.
     */
    String indent = "    ";

    /**
     * Prints the loading error message if Duke fails to load properly.
     */
    public void showLoadingError() {
        System.out.println("Hello from\n" + logo);
        System.out.println(indent + line);
        System.out.println(indent + "Duke has failed to load properly.");
        System.out.println(indent + line);
    }
}