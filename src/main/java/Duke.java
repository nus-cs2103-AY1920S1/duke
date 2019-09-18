import duke.storage.Storage;
import duke.logic.TaskList;
import duke.ui.Ui;

import javafx.application.Application;


import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Main class.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke.
     * @param filePath String of the file path that is passed.
     * @throws FileNotFoundException when Storage finds that the File does not exist.
     */

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     *  Creates new Duke object and runs it.
     * @param args Main method.
     * @throws IOException when resources/duke.txt cannot be found.
     */

    public static void main(String[] args) throws IOException {
        Application.launch(Gui.class, args);
    }

    public Ui getUi() {
        return this.ui;
    }

    public Storage getStorage() {
        return storage;
    }

    public TaskList getTasks() {
        return tasks;
    }
}
