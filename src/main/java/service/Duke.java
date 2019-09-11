package service;

import command.Command;
import javafx.application.Platform;
import ui.Ui;
import util.Storage;
import util.TodoList;
import util.Parser;

public class Duke {
    private TodoList tasks;
    private Storage storage;
    private Ui ui;
    private static final String FILE_PATH = "/home/dingyuchen/cs2103/duke/src/main/data/duke.ser";


    private Duke(String s) {
        storage = new Storage(s);
        ui = new Ui();
        tasks = storage.load();
    }

    public Duke() {
        this(FILE_PATH);
    }

    public String getResponse(String input) {
        Command c = Parser.parse(input);
        if(c.isExit()) {
            Platform.exit();
        }
        return ui.encase(c.run(tasks, storage));
    }

    public String welcome() {
        return this.ui.welcome();
    }
}
