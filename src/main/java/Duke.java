/**
 * The Duke class runs Duke, the personal assistant chatbot
 * that helps a person keeps track of various things.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for class Duke.
     *
     * @param filePath File path for saving tasks in the hard disk.
     * @throws IOException Throws if an unpredicted error occurs.
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            File file = new File(filePath);
            file.createNewFile();
        }
    }

    /**
     * Starts the program by calling the Ui.
     *
     * @throws IOException Throws if an unpredicted error occurs.
     */
    public void run() throws IOException {
        ui.scan(tasks, storage);
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt").run();
    }
}
