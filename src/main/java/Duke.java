import java.io.File;

import command.Command;
import main.Archive;
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
    private Archive archive;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new Duke object.
     */
    public Duke() {

        createNewUi();

        String root = new File(System.getProperty("user.dir")).getPath();

        String taskListFilePath = root + "/data/tasks.txt";
        createNewStorage(taskListFilePath);

        String archiveFilePath = root + "/data/archive.txt";
        createNewArchive(archiveFilePath);
        createNewTaskList();
    }

    private void createNewArchive(String filePath) {
        archive = new Archive(filePath);
        if (!archive.isValidFilePath()) {
            try {
                archive = new Archive();
            } catch (DukeException e) {
                ui.dukeEcho((e.getMessage()));
            }
        }
    }

    private void createNewTaskList() {
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.dukeEcho(e.getMessage());
            tasks = new TaskList();
        }
    }

    private void createNewStorage(String filePath) {
        storage = new Storage(filePath);
        if (!storage.isValidFilePath()) {
            try {
                storage = new Storage();
            } catch (DukeException e) {
                ui.dukeEcho((e.getMessage()));
            }
        }
    }

    private void createNewUi() {
        ui = new Ui();
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
            response += c.execute(tasks, ui, storage, archive);
        } catch (DukeException e) {
            response += ui.getErrorMessage(e.getMessage());
        }
        return response;
    }

}