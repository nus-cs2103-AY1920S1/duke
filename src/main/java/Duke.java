import java.io.FileNotFoundException;
import duke.command.*;
import duke.exception.*;
import duke.parser.*;
import duke.storage.*;
import duke.task.*;
import duke.ui.*;

public class Duke {

    private Storage storage;
    private TaskList tasklist;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasklist = new TaskList(storage.loadFile());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasklist = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isTerminated = false;
        while (!isTerminated) {
            try {
                String fullCommand = ui.readCommand();
                ui.sendLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasklist, ui, storage);
                isTerminated = c.isTerminated();
            } catch (DukeException e) {
                ui.sendMessage(e.toString());
            } finally {
                ui.sendLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
