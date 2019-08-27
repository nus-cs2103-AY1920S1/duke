import java.io.IOException;

/**
 * Represents Duke the main program for users to
 * save and load tasks
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException ex) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke program
     */
    public void run() {
        ui.greet();
        ui.readInput();
        ui.goodbye();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
