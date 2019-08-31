/**
 * The driver class of the entire Duke Project
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    private Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath, ui);
        try {
            tasks = new TaskList(storage.getData());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    private void run() {
        ui.showWelcome();
        ui.straightLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.straightLine(); // show the divider line ("_______")
                Command c = DukeParser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.toString());
            } finally {
                ui.straightLine();
            }
        }
    }

    public static void main(String[] args) {
        String filePath = "data/dukeData.txt";
        new Duke(filePath).run();
    }
}
