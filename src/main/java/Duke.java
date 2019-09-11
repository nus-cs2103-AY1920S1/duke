import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents a chatbot assistant. A <code>Duke</code> object corresponds
 * to a chatbot that has <code>Storage</code>, <code>TaskList</code>
 * and <code>Ui</code>.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a <code>Duke</code> object with a storage filepath.
     *
     * @param filePath Storage filepath.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Returns initialised <code>Ui</code> of specified chatbot.
     *
     * @return Initialised <code>Ui</code>.
     */
    public Ui getUi() {
        ui.setStorage(storage);
        ui.setList(tasks);

        return ui;
    }

    /**
     * Initiates the chatbot to take user input.
     */
    public void run() throws IOException {
        ui.initiate(storage, tasks);
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt").run();
    }
}