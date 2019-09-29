package q.main;

import q.command.Command;
import q.command.ExitCommandEvent;
import q.error.QException;
import q.error.FileLoadError;

/**
 * The main command center that manages the logic.
 */
public class Q {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke object.
     *
     * @param savePath    Path to save the list of tasks.
     * @param archivePath Path to the task archive.
     */
    public Q(String savePath, String archivePath) {
        ui = new Ui();
        storage = new Storage(savePath, archivePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileLoadError e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Gets a response based on an input String command.
     *
     * @param input A user-inputted command.
     * @return A string of the response to the command.
     * @throws ExitCommandEvent Thrown when there is an exit command.
     */
    public String getResponse(String input) throws ExitCommandEvent {
        try {
            Command c = Parser.parse(input);
            String output = c.execute(tasks, ui, storage);
            if (c.isExit()) {
                throw new ExitCommandEvent();
            }
            return output;
        } catch (QException e) {
            return ui.showError(e);
        }
    }
}
