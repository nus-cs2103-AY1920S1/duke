package duke;

import java.util.List;
import java.io.IOException;

import duke.ui.Ui;
import duke.parser.Parser;
import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.task.Task;

public class Duke {
    private Ui ui;
    private Parser parser;
    private TaskList taskList;
    private Storage storage;

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
     * inside run, use ui to ask for commands, ui sends back what command it was as a string
     * parser takes that string command and read it and say is what command and send back as an integer(?)
     * tasklist takes it and does the command and send back a list
     * storage takes the list and record into drive
     */

    public void run() throws IOException {
        ui.printLogo();
        String input;
        do {
            input = ui.getInput();
            int result = parser.getCommand(input);
            List<Task> tasks = taskList.doCommand(result, input);
            storage.writeDuke(tasks);
        } while (!input.contains("bye"));
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }
}