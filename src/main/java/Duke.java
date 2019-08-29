import commands.Command;
import components.Parser;
import components.Storage;
import components.TaskList;
import components.Ui;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a Duke instance.
     *
     * @param filepath refers to where items should be kept in memory.
     */
    Duke(String filepath) {
        storage = new Storage(filepath);
        taskList = new TaskList(storage.load());
        ui = new Ui();
    }

    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();
            Command c = Parser.parse(fullCommand);
            c.execute(ui, storage, taskList);
            isExit = c.isExit();
        }
    }

    /**
     * Starts a Duke instance.
     *
     * @param args takes in arguments.
     */
    public static void main(String[] args) {
        new Duke("src/data/duke.txt").run();
    }
}


