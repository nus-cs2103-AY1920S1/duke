import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;
import duke.task.Task;

import java.io.IOException;
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
     * Runs duke.
     */
    public void run() {
        ui.greet();
        while (!isExit) {
            try {
                String stringCommand = ui.readCommand();
                Command command = new Parser().parse(stringCommand);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (InputMismatchException | IllegalArgumentException | IndexOutOfBoundsException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}