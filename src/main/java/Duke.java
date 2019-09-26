import logic.Command;
import logic.CommandParser;
import model.Tasklist;
import storage.Storage;
import ui.UI;
import ui.UI_CLI;

import java.nio.file.Paths;

public class Duke {
    private Storage storage;
    private Tasklist tasks;
    private UI ui;

    private final String INPUT_DELIMITER = " ";
    private CommandParser commandParser = new CommandParser(INPUT_DELIMITER);
    private boolean isExit;

    public static void main(String[] args) {
        new Duke("duke.txt", false).run();
    }

    /**
     * Creates an instance of Duke with CLI or GUI. Duke will load and save data into the filepath.
     *
     * @param filepath the path of which data is saved and loaded from
     * @param isGUI    determines if GUI or CLI is loaded
     */
    public Duke(String filepath, boolean isGUI) {
        if (isGUI) {
            ui = new UI_GUI();
        } else {
            ui = new UI_CLI();
        }
        assert ui != null : "UI not initiated";

        storage = new Storage(Paths.get(filepath));
        tasks = storage.load();
    }

    /**
     * Runs the program with CLI interface.
     */
    public void run() {
        ui.printWelcome();
        isExit = false;

        while (!isExit) {
            String userInput = ui.getUserInput();
            String response = runCommand(userInput);
            ui.printContent(response);
        }
    }

    /**
     * Takes in input from user, parses the command, and executes it.
     *
     * @param userInput input from the user
     * @return output of the command
     */
    public String runCommand(String userInput) {
        Command command = commandParser.parseCommand(userInput);
        String response = command.execute(tasks, ui, storage);
        isExit = command.isExit();
        return response;
    }

    /**
     * Checks if the program has exited.
     *
     * @return true if exited, false otherwise
     */
    public boolean isExit() {
        return isExit;
    }
}
