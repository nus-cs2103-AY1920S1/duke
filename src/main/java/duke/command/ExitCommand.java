package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * Represents a <code>Command</code> that closes the program.
 */
public class ExitCommand extends Command {
    /**
     * Constructor for <code>ExitCommand</code>.
     */
    public ExitCommand() {
        super();
    }

    /**
     * Prints an exit message and closes the program.
     * @param tasks Instance of <code>TaskList</code> that stores <code>Task</code> objects.
     * @param ui Instance of <code>Ui</code> that handles user input and output.
     * @param storage Instance of <code>Storage</code> that handles writing and loading of information to hard disk.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printExitMessage();
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }

    /**
     * Checks if the current <code>Command</code> is an <code>ExitCommand</code>.
     * @return True.
     */
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof ExitCommand) {
            return true;
        } else {
            return false;
        }
    }
}
