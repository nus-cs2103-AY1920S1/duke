package duke.ui;

import duke.io.Ui;
import duke.io.Storage;
import duke.io.Parser;

import duke.task.TaskList;

import duke.exception.DukeException;
import duke.exception.IllegalInstructionException;

import duke.command.Command;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    String storageSuccess;
    String greeting;

    /**
     * Returns a new duke.ui.Duke object using the given file path.
     *
     * @param filePath filePath to saved task list
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        this.greeting = ui.showWelcome();
        this.storageSuccess = this.loadTaskList();
    }

    /**
     * Initialises tasks and returns a String indicating if TaskList was successfully loaded
     * from storage.
     *
     * @return a String indicating if TaskList was successfully loaded from storage
     */
    private String loadTaskList() {
        String ret;
        try {
            tasks = new TaskList(storage.load());
            ret = ui.showLoadingSuccess();
        } catch (DukeException e) {
            tasks = new TaskList();
            ret = ui.showLoadingError();
        }
        return ret;
    }

    /**
     * Returns a string containing the user's input with some response.
     *
     * @param input user's input
     * @return a String containing the user's input and some program response
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException | IllegalInstructionException e) {
            return ui.showHelper();
        } catch (IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }
}
