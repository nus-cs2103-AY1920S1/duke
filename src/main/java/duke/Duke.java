package duke;

import java.io.IOException;

import duke.command.Command;
import duke.parser.Comd;
import duke.ui.Ui;
import duke.parser.Parser;
import duke.tasklist.TaskList;
import duke.storage.Storage;

/**
 * Main class for Duke.
 */
public class Duke {
    private Ui ui;
    private Parser parser;
    private TaskList taskList;
    private Storage storage;

    /**
     * Initialises all necessary objects for Duke.
     *
     * @param filePath path to duke.txt save file
     */
    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.readDuke());
        } catch (IOException e) {
            ui.showLoadingError();
            storage = new Storage();
            taskList = new TaskList();
        }
    }

    /**
     * Gets a response from user and sends back the output. Prints output in both CLI and GUI.
     *
     * @param input input String from user
     * @return output String from duke
     */
    public String getResponse(String input) {
        Command command = new Command(parser.getCommand(input), input);
        Command result = new Command();
        try {
            result = taskList.doCommand(command);
            ui.printOutput(result.getOutput());
            String reminder = taskList.getReminder();
            if (!reminder.contains("nil")) {
                result.addOutput(reminder);
                ui.printOutput(result.getOutput());
            }
            storage.writeDuke(result.getTaskList());
        } catch (DukeException | IOException ex) {
            result.setOutput(ex.getMessage());
            ui.printError(ex);
        }
        return result.getOutput();
    }
}
