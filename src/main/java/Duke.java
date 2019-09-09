import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Sets up Storage, Ui and Parser for Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Processes duke after set up.
     */
    public void run() {
        ui.showWelcome(tasks.tasks);
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Returns a message generated based on user input.
     *
     * @param input User input.
     * @return Message in a string.
     */
    String getResponse(String input) {
        String doggoResponse;
        try {
            Command command = Parser.parse(input);
            doggoResponse = command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            doggoResponse = e.getMessage();
        }
        return "Doggo : \n" + doggoResponse;
    }
}
