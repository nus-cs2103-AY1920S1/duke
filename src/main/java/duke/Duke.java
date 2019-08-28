package duke;

import duke.command.Parser;
import duke.command.Storage;
import duke.command.TaskList;
import duke.command.Ui;

import java.io.FileNotFoundException;

public class Duke {

    private static Ui ui;
    private static TaskList taskList;
    private static Storage storage;
    private static Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList  = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
        parser = new Parser(taskList, ui);
    }

    public void run() {
        ui.start(parser, storage, taskList);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
