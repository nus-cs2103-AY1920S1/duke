import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;
import duke.task.Task;

import java.io.IOException;
import java.time.DateTimeException;
import java.util.InputMismatchException;
import java.util.ArrayList;


/**
 * The Duke program implements an application that helps users to keep track of their tasks.
 *
 * @author hooncp
 */
public class Duke {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private boolean isExit;

    public Duke() {
        this("./data/data.txt");
    }

    /**
     * Default constructor.
     * Initialises the duke bot and if data file is not found, creates a new file
     * @param filePath String representation of where the data file is stored
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        isExit = false;
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Returns the String representation of the output.
     * @param input user input
     * @return output from Duke
     */
    public String getResponse(String input) {
        String output = "";
        //boolean isTestForAssertion = true;
        //assert isTestForAssertion = false;
            try {
                Command command = new Parser().parse(input);
                assert command != null;
                output = command.execute(taskList, ui, storage);
            } catch (InputMismatchException | IllegalArgumentException |
                    IndexOutOfBoundsException | DateTimeException e) {
                output = ui.printErrorMessage(e.getMessage());
            }

        return output;
    }

    public String greeting() {
        return ui.greet();
    }
}