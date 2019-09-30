package duke;

import duke.util.UiMessage;
import duke.command.Command;
import duke.util.exception.DukeException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String filePath = "data/tasks.txt";

    /**
     * Creates a new instance of Duke, with the default filePath.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
    }

    /**
     * Runs Duke from the CLI. All output is displayed in the CLI.
     * Not used when Duke is run from the GUI.
     */
    private void run() {
        ui.showMessage(UiMessage.WELCOME);
        initializeStorage();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
        storage.save(tasks);
    }

    /**
     * Creates and runs a new instance of Duke.
     * Invoked when Duke is run from the CLI.
     * @param args Arguments supplied by the user.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui);
        } catch (DukeException e) {
            ui.showError(e);
        }

        // dummy implementation
        return "Duke heard: " + input;
        //return ui.getResponse();
    }

    /**
     * Attempts to import an existing task list.
     */
    private void initializeStorage() {
        try {
            TaskList tasksFromFile = storage.load();
            tasks = tasksFromFile;
        } catch (Exception e) {
            // temporary haxx
            e.printStackTrace();
        }
    }
}
