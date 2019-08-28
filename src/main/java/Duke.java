/**
 * Represents the Duke bot. Allows for easy and organised management of tasks.
 */

public class Duke {
    /**
     * Represents user interface of Duke.
     */
    private Ui ui;
    /**
     * Represents task list which stores all tasks given to Duke.
     */
    private TaskList tasks;

    /**
     * Represents storage function of Duke that helps to load and save data to disk.
     */
    private static Storage storage;

    /**
     * Constructor of Duke. Sets up user interface, storage and task list.
     * @param filePath String of filePath to create Storage.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadList());
        } catch (LoadingErrorDukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Initialises resources for Duke on startup and starts running Duke.
     * @param args Sets up resourcs.
     */
    public static void main(String[] args) {
        new Duke("src/data/list.txt").run();
    }

    /**
     * Starts Duke, prompting user for input.
     */
    public void run() {
        boolean running = true;
        ui.showWelcome();
        while (running) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                running = command.isRunning();
            } catch (Exception e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
    }
}
