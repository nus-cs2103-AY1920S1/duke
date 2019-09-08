package service;

import command.Command;
import ui.Ui;
import util.Storage;
import util.TodoList;
import util.exception.DukeException;
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

    private void run() {
        this.ui.printResponse(this.ui.welcome());

        while (true) {
            try {
                Command command = this.ui.getUserInput();
                String response = command.run(tasks, storage);
                this.ui.printResponse(response);
                if (command.isExit()) {
                    break;
                }
            } catch (DukeException e) {
                this.ui.printErrorMessage(e);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke(FILE_PATH);
        duke.run();
    }

    public String getResponse(String input) {
        Command c = Parser.parse(input.trim().split("\\s+"));
        return ui.encase(c.run(tasks, storage));
    }

    public String welcome() {
        return this.ui.welcome();
    }
}
