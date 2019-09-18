package duke.ui;

/**
 * Ui class. Stores the strings used for greetings.
 */
public class Ui {

    /** Pre-intro message. */
    private static String pre_intro = "♬♫♪♩ Cebuuuuu ♩♪♫♬\n";
    /** Intro message. */
    private static String intro = "Oh sorry! I'm Larry\nWhat can I do for you?\n";
    /** Help prompt for user.*/
    private static String help_prompt = "Unsure what to do? Type 'help'.";
    /** Goodbye message. */
    private static String goodbye = "Bye. Hope to see you again soon!\n";

    /** Stores the MainWindow object. */
    private MainWindow mainWindow;

    /**
     * Initialises duke.main.Ui.
     * @param mainWindow The application's MainWindow object.
     */
    public Ui(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * Returns the intro string.
     */
    public void printIntro() {
        mainWindow.printAsLarry(intro + help_prompt);
    }

    /**
     * Prints the goodbye message.
     */
    public void printGoodbye() throws InterruptedException {
        mainWindow.printAsLarry(goodbye);
    }

    /**
     * Prints a message to the user.
     * @param s The string to be printed.
     */
    public void printToUser(String s) {
        mainWindow.printAsLarry(s);
    }

    /**
     * Prints an error message to the user.
     * @param e The exception to be printed.
     */
    public void printErrToUser(Exception e) {
        mainWindow.printAsLarry(e.toString());
    }

}
