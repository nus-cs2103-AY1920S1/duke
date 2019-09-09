package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.commons.Messages;
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

    /**
     * Constructs a Duke object. Reads and loads the latest record of the Task List
     * from the file that stores the list.
     * @param filePath Represents the file address of the file to be read.
     */
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

    /** Executes the Duke program. */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = new Parser().parse(fullCommand);
                c.execute(tasks, ui, storage.path);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printReply(Messages.MESSAGE_UNKNOWN_COMMAND);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.printReply("The description cannot be empty :-(");
            }
        }
        ui.showGoodByeMessage();
    }

    public static void main(String[] args) {
        new Duke("C:/duke/src/data/tasklist.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        return "Duke heard: " + input;
    }
}