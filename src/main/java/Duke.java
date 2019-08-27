import java.util.ArrayList;
import java.io.FileNotFoundException;

/**
 * The Duke program implements an application that
 * acts like a digital notebook for the user.
 *
 * @author  Calvin
 * @version 1.0
 * @since   2019-08-20
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * This constructor takes in the filePath and initiates the necessary
     * classes required.
     * @param filePath the local directory of the file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * This method runs the application
     */

    public void run() throws FileNotFoundException {
        ArrayList<Task> list = tasks.getList();
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * This is the main method which makes use of run method.
     * @param args Unused.
     * @return Nothing.
     * @exception FileNotFoundException On file not found error.
     * @see FileNotFoundException
     */

    public static void main(String[] args) throws FileNotFoundException {
        new Duke("C:\\duke\\src\\main\\java\\data\\duke.txt").run();
    }
}
