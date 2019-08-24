package duke;

import duke.command.Command;
import org.json.JSONException;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

/**
 * Main class for Duke app.
 */
public class Duke {
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Constructor for Duke when save file path is known.
     *
     * @param filePath file path
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFromSaveFile());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Constructor for Duke when save file path is unknown (will use the default location).
     *
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.loadFromSaveFile());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Method to start Duke app.
     * @throws IOException
     */
    public void run() throws IOException {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }


    /**
     * Main function where the app launches.
     * @param args
     * @throws JSONException
     * @throws IOException
     */
    public static void main(String[] args) throws JSONException, IOException {
        new Duke().run();
    }
}