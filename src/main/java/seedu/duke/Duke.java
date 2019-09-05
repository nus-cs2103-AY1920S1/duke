package seedu.duke;

import seedu.duke.exception.DukeException;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.UI;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Duke() {
    }

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        String command = ui.readCommand();
        new Parser().parse(command, ui, tasks, storage.path);
        ui.showGoodByeMessage();
    }

//    public static void main(String[] args) {
//        new Duke("C:\\duke\\src\\data\\tasklist.txt").run();
//    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        return "Duke heard: " + input;
    }
}