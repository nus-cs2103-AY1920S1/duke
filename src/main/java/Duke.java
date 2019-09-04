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
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Initiates the chatbot to take user input.
     */
    public void run() {
        ui.initiate(storage, tasks);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}