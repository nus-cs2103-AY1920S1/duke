import java.io.IOException;
import java.util.NoSuchElementException;

public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Creates an instance of Duke, a task manager.
     * @param filePath location of the text file to read/write tasks to the hard disk.
     *                 If the file exists, it will load tasks from it and write to it whenever users update the tasks.
     *                 If the file does not exist, it will create the file to write to.
     */
    public Duke(String filePath) throws IOException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.dukePrint(e.toString());
        }
    }

    /**
     * Initiates the program loop where users can key in commands to interact with Duke.
     */
    public void start() throws IOException {
        ui.showWelcome();
        String input;
        boolean isExit = false;
        while (!isExit) {
            try {
                input = ui.getInputLine();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.dukePrint(e.toString());
            } catch (NoSuchElementException e) {
                return;
            }
        }
    }
}
