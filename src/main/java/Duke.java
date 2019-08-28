import duke.command.Command;

import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.FileNotFoundException;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public void run() {
        try {
            tasks = storage.getTasks();
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        } catch (DukeException e) {
            ui.printError(e);
            tasks = new TaskList();
        }

        ui.printGreeting();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                Command c = Parser.parse(input);
                isExit = c.isExit();
                c.execute(tasks, ui, storage);
            } catch (DukeException e) {
                ui.printError(e);
            }
        }

        ui.printExit();
    }
}
