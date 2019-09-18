package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.helpers.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;
import seedu.duke.ui.Ui;

public class Duke {

    private TaskList tasks;

    /**
     * Constructs a new Duke Object by instantiating the TaskList by loading from Storage.
     */
    public Duke() {
        // Load from file
        try {
            tasks = Storage.getInstance().loadFromDisk();
        } catch (Storage.StorageOperationException e) {
            Ui.printError(e);
            Ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Main Method.
     *
     * @param args string arguments for console.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Executes the Duke Assistant.
     */
    public void run() {
        Ui.greet();

        //noinspection InfiniteLoopStatement
        while (true) {
            String input = Ui.readNextLine();
            try {
                Parser.parseCommand(input).execute(tasks);

            } catch (DukeException e) {
                Ui.printError(e);
            }
        }
    }


}
