import duke.command.Parser;
import duke.command.Storage;
import duke.command.TaskList;
import duke.command.Ui;

import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        try {
            ui.start(new Parser(taskList, ui));
            storage.save(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }
}