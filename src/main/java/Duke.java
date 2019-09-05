import java.io.IOException;

/**
 * The Main class.
 */
public class Duke {

    private Storage storage;
    private Sheet sheet;
    private Ui ui;
    private MainWindow mw;
    private String pathToFile;

    public Duke(String pathToFile, MainWindow mw) {
        this.mw = mw;
        this.ui = new Ui(mw);
        this.pathToFile = pathToFile;
        storage = new Storage(MyPaths.TASK_LIST);
        try {
            sheet = new Sheet(storage.load(), ui);
        } catch (IOException e) {
            ui.showLoadingError();
        }
    }

    public void start(String input) {

        try {
            String fullCommand = mw.getInput();
            Command c = Parser.parse(fullCommand);
            if (c.isExit()) {
                // exit
            }
            c.execute(sheet, ui, storage);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
