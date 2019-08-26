package duke;

/**
 * Represents a Duke object. A <code>Duke</code> object corresponds to
 * an object that stores and parses tasks for user
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Duke(String filePath) {
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
     * Operates the Duke object.
     */
    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }

        }

    }

    /**
     * Creates new Duke object.
     */
    public static void main(String[] args) {
        new Duke("/Users/Bernice/Desktop/CS2103T/duke/duke.txt").run();
    }
}
