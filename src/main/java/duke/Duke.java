package duke;

import duke.command.Command;
import duke.task.Task;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.ArrayList;

/**
 * The duke.Duke program implements an application that helps users to keep track of their tasks.
 *
 * @author hooncp
 */
public class Duke {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private boolean isExit;

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
     * runs duke.
     */
    public void run() {
        ui.greet();
        while (!isExit) {
            try {
                String stringCommand = ui.readCommand();
                Command command = new Parser().parse(stringCommand);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (InputMismatchException e) {
                ui.printErrorMessage(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ui.printErrorMessage(e.getMessage());
            } catch (IllegalArgumentException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("./data/data.txt");
        duke.run();
    }
}