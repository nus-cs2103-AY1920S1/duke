import java.io.FileNotFoundException;

/**
 * Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises a session for Duke and loads tasks, if any, from a previous session.
     *
     * @param filePath Location of the task list from the previous session.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Begins a session of Duke by initialising a Duke object with the filepath to load and store the task list.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke("../../../data/duke.txt").run();
    }

    /**
     * Enables Duke to read and parse inputs continuously.
     */
    public void run() {
        ui.showWelcome();
        while (ui.sc.hasNextLine()) {
            String command = ui.readCommand();
            Parser.parse(command, tasks, storage, ui);
        }
    }

}
