package duke;

import java.util.List;
import java.io.IOException;

import duke.command.Command;
import duke.parser.Comd;
import duke.ui.Ui;
import duke.parser.Parser;
import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.task.Task;

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
            taskList = new TaskList();
        }
    }

    /**
     * run() method loops the functions of Duke. Starts Duke by printing the logo then get an input. Input
     * gets passed into a Parser object which gives an Integer result. That including the original input
     * string gets passed into a TaskList object that returns the resultant List of tasks. That gets written
     * onto disk with a Storage object. It loops until input is "bye".
     */
    public void run() {
        ui.printLogo();
        String input;
        do {
            input = ui.getInput();
            Command command = new Command(parser.getCommand(input), input);
            Command result = new Command();
            try {
                result = taskList.doCommand(command);
                ui.printOutput(result.getOutput());
                storage.writeDuke(result.getTaskList());
            } catch (DukeException | IOException ex) {
                ui.printError(ex);
            }
        } while (!input.contains("bye"));
    }
    /*
    public String getResponse(String input) {

    }*/

    public static void main(String[] args) {
        new Duke("resources/data/duke.txt").run();
    }
}