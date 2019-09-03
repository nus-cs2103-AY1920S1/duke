package ui;

/**
 * Gui class.
 *
 * @author Marcus Ong
 */
public class Gui extends Ui {
    private final MainWindow mainWindowController;
    private final String LINE_APPEND = "\n";

    public Gui(MainWindow mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    /**
     * Show message to user.
     */
    @Override
    public void show() {
        String output = messageBuilder.toString();
        messageBuilder = new StringBuilder(); // clear messageBuilder
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
}
