import command.Command;
import task.TaskList;
import util.DukeException;
import util.Parser;
import util.Storage;
import util.Ui;

import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
//            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        Ui.welcomeMsg();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readInput();
                Ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.executeCommand(tasks, storage);
                isExit = c.isExit();
            } catch (Exception e) {
            } finally {
                Ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt").run();
    }
}
