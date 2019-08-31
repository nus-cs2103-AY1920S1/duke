import command.Command;
import exception.DukeException;
import filewriter.Storage;
import parser.Parser;
import task.TaskList;
import ui.Ui;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

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
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbye();
<<<<<<< HEAD

=======
>>>>>>> c15043ac81860360c58b39a9b432b0229f5d4d30
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}