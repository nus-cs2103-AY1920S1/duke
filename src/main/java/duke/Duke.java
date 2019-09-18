package duke;

import duke.command.Command;

import java.io.IOException;
import java.time.DateTimeException;
import java.util.InputMismatchException;
import java.util.ArrayList;


/**
 * The duke.Duke program implements an application that helps users to keep track of their tasks.
 *
 * @author hooncp
 */
class Duke {
    private TaskList taskList;
    private final Storage storage;
    private final Ui ui;
    private boolean isExit;

    public Duke() {
        this("./data/data.txt");
    }

    /**
     * Default constructor.
     * Initialises the duke bot and if data file is not found, creates a new file
     *
     * @param filePath String representation of where the data file is stored
     */
    private Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        isExit = false;
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            taskList = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Returns the String representation of the output.
     *
     * @param input user input
     * @return output from duke.Duke
     */
    public String getResponse(String input) {
        String output;
        //boolean isTestForAssertion = true;
        //assert isTestForAssertion = false;
        try {
            Command command = Parser.parse(input);
            assert command != null;
            output = command.execute(taskList, ui, storage);
        } catch (InputMismatchException | IllegalArgumentException
                | DateTimeException e) {
            output = ui.printErrorMessage(e.getMessage());
        }
        return output;
    }

    public String greeting() {
        return ui.greet();
    }
}