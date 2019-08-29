import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private Ui ui;
    public Storage storage;
    public TaskList tasks;

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
