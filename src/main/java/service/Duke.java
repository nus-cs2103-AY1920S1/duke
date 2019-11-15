package service;

import command.Command;
import javafx.application.Platform;
import ui.Ui;
import util.Storage;
import util.TodoList;
import util.Parser;
import util.exception.DukeException;

public class Duke {
    private TodoList tasks;
    private Storage storage;
    private Ui ui;
    private static final String FILE_PATH = "src/main/data/duke.ser";


    private Duke(String s) {
        storage = new Storage(s);
        ui = new Ui();
        tasks = storage.load();
    }

    public Duke() {
        this(FILE_PATH);
    }

    public String getResponse(String input) {
        Command c;
        try {
            c = Parser.parse(input);
            String response = c.run(tasks, storage);

            return ui.encase(response);

        } catch (DukeException e) {
            return e.getMessage();
        }

    }

    public String welcome() {
        return this.ui.welcome();
    }
}
