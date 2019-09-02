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
                                     System.exit(0);
                                 }
                             }
            , new Date(System.currentTimeMillis() + 1 * 800));
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns if commands should continue being read.
     *
     * @return Boolean value if commands should continue being read.
     */
    @Override
    public boolean shouldContinue() {
        return false;
    }
}
