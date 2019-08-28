package seedu.duke;

import java.io.IOException;
import java.text.ParseException;

/**
 * Represents a Personal Assistant Chatbot that helps a person to keep track of various things.
 *
 * @author Evon Dong
 * @version 1.0
 * @since 2019-08-28
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for class Duke.
     * Loads the data if datafile exists.
     *
     * @param filePath the path to the file where the list of tasks is stored
     * @throws IOException On input error
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Handles the user inputs and execute the command.
     * Updates the list of tasks in the datafile according to the commands.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand, ui);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printErrMsg(e.getMessage());
            } catch (IOException e) {
                ui.printErrMsg("IO Error");
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * This is the main method which makes use of the run method.
     *
     * @param args Unused
     * @throws IOException On input error
     */
    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt").run();
    }

}
