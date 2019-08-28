import java.io.IOException;

/**
 * Duke is a task manager programme with the following capabilities:
 * addition of tasks, listing of tasks, deletion of tasks and marking tasks as done.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * @param filePath the file path of the saved tasks
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

    public static void main(String[] args) throws DukeException {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Runs the programme continuously until the user inputs "bye"
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

}