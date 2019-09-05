package duke.main;

import duke.command.Command;
import duke.exception.DukeException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private boolean isExit = false;
    private static final String filePath = "data/tasks.txt";

    Duke() {
        storage = new Storage(filePath);
        ui = new Ui();

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    String getResponse(String input) {
        if (isExit) {
            return "";
        }

        try {
            ui.resetOutput();
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
            return ui.getOutput();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}