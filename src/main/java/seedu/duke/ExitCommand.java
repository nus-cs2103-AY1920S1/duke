package seedu.duke;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

/**
 * Represents a Exit Command.
 * A <code>ExitCommand</code> object corresponds to a command with a description "bye".
 */
public class ExitCommand extends Command {

    /**
     * Constructor of the ExitCommand class.
     */
    public ExitCommand() {
    }

    /**
     * Sets the exit variable to true and prints the exit message. After that, the application closes by itself.
     *
     * @param list the TaskList object that is handling the arraylist of the datafile
     * @param ui the UserInterface object that handles the interaction with users
     * @param storage the Storage object that stores and handles the path to datafile
     * @return the output string
     */
    public String execute(TaskList list, Ui ui, Storage storage) {
        exit = true;

        PauseTransition exitDelay = new PauseTransition(Duration.seconds(1));
        exitDelay.setOnFinished(MainWindow.exitHandler);
        exitDelay.play();

        return ui.exit();
    }

}
