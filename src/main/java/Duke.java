import java.io.IOException;

/**
 * The Main class
 */
public class Duke {

    private Storage storage;
    private Sheet sheet;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs the main of the app.
     *
     * @param filePath Path to file that stores the task list.
     */
    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(myPaths.TASK_LIST);
        try {
            sheet = new Sheet(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
        }
    }

    /**
     * Drives the main class.
     */
    public void run() {
        parser.start(sheet);
    }

    /**
     * Main method.
     *
     * @param args Arguments.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
