package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeIoException;
import duke.parser.Parser;
import duke.sheet.Sheet;
import duke.storage.Storage;
import duke.ui.Ui;

public class Duke {

    private Storage storage;
    private Sheet sheet;

    public Sheet getSheet() {
        return sheet;
    }

    public Ui getUi() {
        return ui;
    }

    private Ui ui;
    private MainWindow mw;

    /**
     * Constructs a Duke object.
     *
     * @param pathToFile Path to file storing the task list.
     * @param mw MainWindow.
     */
    public Duke(String pathToFile, MainWindow mw) {
        this.mw = mw;
        this.ui = new Ui(mw);
        storage = new Storage(pathToFile);
        try {
            sheet = new Sheet(storage.load());
        } catch (DukeIoException e) {
            ui.showLoadingError();
        }
    }

    /**
     * Starts the Duke programme.
     *
     */
    void start() {

        try {
            assert mw != null;
            String fullCommand = mw.getInput();
            Command c = Parser.parse(fullCommand);
            if (c.isExit()) {

                System.exit(0);
            }
            c.execute(sheet, ui, storage);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
