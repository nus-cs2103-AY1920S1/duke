/**
 * Represents main running class of the Duke program.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke object with Ui, storage and taskList objects created.
     * to facilitate running of the program
     * @param filePath specifies the file to be read from and written into
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            //ui.showLoadingError();
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Executes the Duke object.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
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

    public static void main(String[] args) {
        new Duke("C:\\Users\\Lynn\\Desktop\\Y2S1\\CS2103T\\" +
                    "dukenew\\src\\main\\java\\TaskList").run();
    }
}


