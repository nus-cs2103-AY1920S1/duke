package duke.commands;

import duke.model.Model;
import duke.storage.Storage;
import duke.ui.Ui;

public class ExitCommand extends Command {
    /**
     * Exit the program.
     * @param model model of the Duke project
     * @param ui an instance of the Ui class
     * @param storage an instance of the storage class
     */
    @Override
    public void execute(Model model, Ui ui, Storage storage) {
        ui.showExit();
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        System.exit(0);
                    }
                },
                500
        );
    }
}
