package duke;

import duke.command.Parser;
import duke.command.Storage;
import duke.command.TaskList;
import duke.command.Ui;

import java.io.IOException;

/**
 * Contains the main method.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Opens the task and start the task update.
     * @param filePath filePath of the task file.
     */

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Start getting user input and update the storage.
     */

    public void run() {
        try {
            ui.start(new Parser(taskList, ui));
            storage.save(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }
}