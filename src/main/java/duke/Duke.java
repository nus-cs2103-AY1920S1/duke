package duke;

import duke.command.Command;
import duke.helper.Parser;
import duke.helper.Storage;
import duke.helper.Ui;
import duke.task.TaskList;

import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        Parser.setFilePath(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("D:/Users/USER/Documents/0. NUS/NUS Y2S1/CS2103T/Individual Project/duke/data/duke.txt").run();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readLineInput();
                Command c = Parser.parse(fullCommand);
                if (c != null) {
                    c.execute(tasks, ui, storage);
                    isExit = c.isExit();
                }
            } catch (DukeException de) {
                ui.printError(de.getMessage());
            }
        }
        ui.printExitMessage();
    }
}
