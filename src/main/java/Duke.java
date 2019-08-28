import command.Command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Parser;

/**
 * Main Duke class.
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
            try {
                String fullCommand = ui.readCommand();
                // !sc.hasNext("bye")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showLoadingError(e);
            }
        }
    }

    public static void main(String[] args) {
        Duke d = new Duke("list.txt");
        d.run();
    }
}
