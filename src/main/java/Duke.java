public class Duke {

    private static final String DATA_FILE_TASKS = "./data/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command cmd = Parser.parse(fullCommand);
                cmd.execute(tasks, ui);
                isExit = cmd.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
        try {
            storage.save(tasks.dump());
        } catch (DukeException e) {
            ui.showSavingError();
        }
    }

    /**
     * This is the main method and entry point for the Duke program.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new Duke(DATA_FILE_TASKS).run();
    }
}
