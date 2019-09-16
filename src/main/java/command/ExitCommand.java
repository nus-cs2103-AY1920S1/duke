package command;

import java.util.Timer;
import java.util.TimerTask;

import duke.Storage;
import duke.Ui;
import javafx.application.Platform;
import task.TaskList;

/**
 * Represents a command that exits Duke.
 */

public class ExitCommand extends Command {
    public ExitCommand() {
        super.isExit = true;
    }

    /**
     * Executes this task.
     *
     * @param tasks All the tasks that the user currently has.
     * @param ui The Ui object associated with Duke.
     * @param storage The Storage object associated with Duke
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFarewellMessage();

        Timer timer = new Timer();
        timer.schedule(new ExitTask(), 1000); // 1 second
    }

    class ExitTask extends TimerTask {
        /**
         * Exits the application after five seconds.
         */
        @Override
        public void run() {
            Platform.exit();
        }
    }

    /**
     * Returns a Boolean to indicate whether this command is an ExitCommand.
     *
     * @return Whether this Command is an ExitCommand.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
