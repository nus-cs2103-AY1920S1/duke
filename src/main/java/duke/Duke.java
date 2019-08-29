package duke;

import duke.command.Command;

/**
 * Duke is a personal assistant that helps manage tasks in a list.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Starts a new Duke session, loading any existing tasks from previous sessions from the hard disk.
     * @param filepath Location which Storage loads/saves tasks from/to.
     */
    private Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the app's main loop until exit command is received.
     */
    private void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Main method. Application starts here.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke("\\data\\duke.txt").run();
    }
}
