package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import javafx.application.Platform;
import javafx.concurrent.Task;

public class ExitCommand extends Command {
    /**
     * Executes an exit command using the given task list, UI and file storage.
     *
     * @param tasks the task list supplied.
     * @param ui the UI supplied.
     * @param storage the file storage supplied.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
        Task<Void> delay = new Task<>() {
            @Override
            protected Void call() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        delay.setOnSucceeded((x) -> Platform.exit());
        new Thread(delay).start();
    }
}
