package duke;

import duke.bin.common.DukeException;
import duke.bin.datahandling.Parser;
import duke.bin.TaskList;
import duke.bin.UI;
import duke.bin.datahandling.DataStorage;

public class Duke {
    private DataStorage storage;
    private TaskList tasks;
    private UI ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new UI();
        storage = new DataStorage(filePath);
        try {
            tasks = new TaskList();
            tasks.store(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
        parser = new Parser(tasks, storage, ui);
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                parser.parse(fullCommand);
                isExit = parser.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/save.txt").run();
    }

}
