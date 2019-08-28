import Duke.exception.DukeException;
import Duke.parser.Parser;
import Duke.storage.Storage;
import Duke.task.*;
import Duke.ui.Ui;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.toString());
            tasks = new TaskList();
        }
    }

    public void run() {
        Parser parser = new Parser(tasks);
        storage.write(parser.start());
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
