/**
 * Duke class.
 */
public class Duke {
    private String line;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Duke Constructor.
     *
     * @param filePath path of data file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException duke) {
            ui.showLoadingError(duke);
            tasks = new TaskList();
        }
    }

    /**
     * Boots the Duke program.
     */
    private void run() {
        boolean isExit = false;
        ui.printStatement("Hello! I'm Duke", "What can I do for you?");
        while (!isExit) {
            String fullCommand = ui.readCommand();
            // !sc.hasNext("bye")
            Command c = new Command(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }
    }

    public static void main(String[] args) {
        Duke d = new Duke("./data/list.txt");
        d.run();
    }
}
