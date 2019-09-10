import command.Command;
import main.DukeException;
import main.Parser;
import main.Storage;
import main.TaskList;
import main.Ui;

/**
 * Main class.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new Duke object
     */
    public Duke() {
        String filePath = "/Users/zhangxuan/Desktop/CS2103/duke/data/tasks.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        if (!storage.isValidFilePath()) {
            try {
                storage = new Storage();
            } catch (DukeException e) {
                ui.dukeEcho((e.getMessage()));
            }
        }
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.dukeEcho(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Returns the message to be displayed to the user in String format after parsing the
     * user's full command.
     *
     * @param fullCommand The full command that the user input.
     * @return Duke's response to the full command in String format.
     */
    public String run(String fullCommand) {
        if (fullCommand.equals("")) {
            return "Please enter a command. Type 'help' for a list of all valid commands.";
        }
        String response = "";
        try {
            Command c = Parser.parse(fullCommand);
            response += c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            response += ui.getErrorMessage(e.getMessage());
        }
        return response;
    }
    /**
     * Note to self:
     * At this moment in the application the text cuts off at 4 lines. Figure out how to make it dynamic
     */

}