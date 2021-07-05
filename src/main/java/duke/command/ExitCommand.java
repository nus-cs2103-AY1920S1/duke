package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ExitCommand extends Command {

    /**
     * Executes the current command.
     *
     * @param ui       Ui object.
     * @param storage  Storage object.
     * @param taskList TaskList object.
     */
    public String execute(Ui ui, Storage storage, TaskList taskList) {
        new Timer().schedule(new TimerTask() {
            public void run() {
                System.exit(0); // Exit the application
            }
        }, new Date(System.currentTimeMillis() + 800)); // Set the delay to 800ms
        return "Bye. Hope to see you again soon!";
    }
}
