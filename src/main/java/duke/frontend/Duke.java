package duke.frontend;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Driver class that employs various components of Duke.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList list;


    public Duke() {
    }
    /**
     * Constructs a Duke and specifies its file path.
     *
     * @param filePath the location of the save data file.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            list = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            list = new TaskList();
        }
        ui = new Ui(list, storage);
    }

    /**
     * Displays welcome message when Duke starts.
     *
     * @return the opening welcome message to be displayed.
     */
    public String displayIntro() {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

         */
        return "Hello from Duke! What can I do for you? :)\n";
    }

    /**
     * Solicits response from Duke after taking in an input.
     *
     * @param input user input text/command.
     * @return Duke's response to the user input text/command.
     * @throws DukeException errors that occur during Duke's response process.
     */
    public String getResponse(String input) throws DukeException {
        return ui.start(input);
    }
}
