package duke.main;

import duke.command.Command;
import duke.exception.DukeException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.displayError(e.getMessage());
            tasks = new TaskList();
        }
        parser = new Parser();
    }

    public void run() {
        ui.sayWelcome();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String[] fullCommand = ui.getCommand();
                Command c = parser.parse(fullCommand);
                c.execute(storage, ui, tasks);
                isRunning = c.isRunning();
            }
            catch (DukeException e) {
                ui.displayError(e.getMessage());
            }
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
