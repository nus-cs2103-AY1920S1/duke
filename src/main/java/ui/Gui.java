package ui;

/**
 * Gui class.
 *
 * @author Marcus Ong
 */
public class Gui extends Ui {
    private final MainWindow mainWindowController;
    private static final String LINE_APPEND = "\n";

    public Gui(MainWindow mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    /**
     * Show message to user.
     */
    @Override
    public void show() {
        String output = messageBuilder.toString();
        messageBuilder = new StringBuilder();
        mainWindowController.addDukeResponse(output);
    }

    /**
     * Adds a line to the message.
     *
     * @param line String containing a line to add to messageBuilder.
     */
    @Override
    public void addLineToMessage(String line) {
        messageBuilder.append(line);
        messageBuilder.append(LINE_APPEND);
    }

    /**
     * Print out welcome message.
     */
    @Override
    public void showWelcome() {
        String greeting = "G'day mate! I'm Duke. Whatcha need help with?";
        addLineToMessage(greeting);
        show();
    }
}
