import java.io.IOException;
import duke.exceptions.DukeException;
import duke.managers.Parser;
import duke.managers.Storage;
import duke.managers.TaskList;
import duke.managers.Ui;
import duke.commands.Command;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises a new Duke object including new objects such as storage, ui and task list.
     * @param filePath a String containing the file path of the input file where data is stored and loaded from
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Duke() {

    }

    /**
     * Runs Duke object to start the chat bot. This method will call on other methods to load previously saved
     * information.
     */
    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e2) {
                System.out.println("Something went wrong: "  + e2.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException | IOException e) {
            System.out.println(e);
            return ui.printLine(e.getMessage());
        }
    }
}

