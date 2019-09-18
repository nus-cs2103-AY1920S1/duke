package duke.ui;

/**
 * duke.main.Ui class. Stores the strings used for greetings.
 */
public class Ui {

    /** Intro message. */
    private static String intro = "Hello! I'm duke.main.Duke\nWhat can I do for you?\n";
    /** Help prompt for user.*/
    private static String help_prompt = "Unsure what to do? Type 'help'.";
    /** Goodbye message. */
    private static String goodbye = "Bye. Hope to see you again soon!\n";

    /** Stores the duke.main.MainWindow object. */
    private MainWindow mainWindow;

    /**
     * Initialises duke.main.Ui.
     * @param mainWindow The application's duke.main.MainWindow object.
     */
    public Ui(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * Returns the intro string.
     */
    public void printIntro() {
        mainWindow.printAsDuke(intro + help_prompt);
    }

    /**
     * Prints the goodbye mesage.
     */
    public void printGoodbye() throws InterruptedException {
        mainWindow.printAsDuke(goodbye);
    }

    /**
     * Prints a message to the user.
     * @param s The string to be printed.
     */
    public void printToUser(String s) {
        mainWindow.printAsDuke(s);
    }

    /**
     * Prints an error message to the user.
     * @param e The exception to be printed.
     */
    public void printErrToUser(Exception e) {
        mainWindow.printAsDuke(e.toString());
    }

}
